package com.hackernews.futuristic.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    
    private var searchJob: Job? = null
    private var allStories: List<Story> = emptyList()
    
    init {
        loadAllStories()
    }
    
    private fun loadAllStories() {
        viewModelScope.launch {
            // Load MUCH more stories for comprehensive search
            val topStories = repository.getTopStories(200).getOrNull() ?: emptyList()
            val newStories = repository.getNewStories(200).getOrNull() ?: emptyList()
            val bestStories = repository.getBestStories(200).getOrNull() ?: emptyList()
            
            allStories = (topStories + newStories + bestStories)
                .distinctBy { it.id }
                .sortedByDescending { it.score }
        }
    }
    
    fun search(query: String) {
        searchJob?.cancel()
        
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(
                searchResults = emptyList(),
                isLoading = false,
                error = null
            )
            return
        }
        
        searchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            // Add a small delay to avoid too many rapid searches
            delay(300)
            
            try {
                val results = searchStories(query)
                _uiState.value = _uiState.value.copy(
                    searchResults = results,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Search failed: ${e.message}"
                )
            }
        }
    }
    
    private fun searchStories(query: String): List<Story> {
        val searchTerms = query.lowercase().split(" ").filter { it.isNotBlank() }
        
        return allStories.mapNotNull { story ->
            var score = 0
            
            // Enhanced fuzzy matching with scoring
            story.title?.lowercase()?.let { title ->
                searchTerms.forEach { term ->
                    when {
                        title.contains(term) -> score += 10
                        title.contains(term.take(term.length - 1)) -> score += 7 // Partial match
                        levenshteinDistance(term, title) <= 2 -> score += 5 // Fuzzy match
                    }
                }
            }
            
            story.by?.lowercase()?.let { author ->
                searchTerms.forEach { term ->
                    if (author.contains(term)) score += 8
                }
            }
            
            story.url?.lowercase()?.let { url ->
                searchTerms.forEach { term ->
                    if (url.contains(term)) score += 6
                }
            }
            
            // Include story text content for broader search
            story.text?.lowercase()?.let { text ->
                searchTerms.forEach { term ->
                    if (text.contains(term)) score += 3
                }
            }
            
            if (score > 0) story to score else null
        }.sortedByDescending { it.second }
         .take(100) // More results
         .map { it.first }
    }
    
    private fun levenshteinDistance(s1: String, s2: String): Int {
        val len1 = s1.length
        val len2 = s2.length
        val dp = Array(len1 + 1) { IntArray(len2 + 1) }
        
        for (i in 0..len1) dp[i][0] = i
        for (j in 0..len2) dp[0][j] = j
        
        for (i in 1..len1) {
            for (j in 1..len2) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + cost
                )
            }
        }
        return dp[len1][len2]
    }
    
    fun clearSearch() {
        searchJob?.cancel()
        _uiState.value = _uiState.value.copy(
            searchResults = emptyList(),
            isLoading = false,
            error = null
        )
    }
}

data class SearchUiState(
    val searchResults: List<Story> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)