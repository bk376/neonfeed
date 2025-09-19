package com.hackernews.futuristic.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleSearchViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<Story>>(emptyList())
    val searchResults: StateFlow<List<Story>> = _searchResults.asStateFlow()
    
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    // Cache for stories
    private var allStories: List<Story> = emptyList()
    private var storiesLoaded = false
    
    init {
        loadStoriesForSearch()
    }
    
    private fun loadStoriesForSearch() {
        viewModelScope.launch {
            try {
                // Load stories from all categories for comprehensive search
                val topStories = repository.getTopStories(100).getOrNull() ?: emptyList()
                val newStories = repository.getNewStories(100).getOrNull() ?: emptyList()
                val bestStories = repository.getBestStories(100).getOrNull() ?: emptyList()
                
                // Combine and deduplicate
                val combinedStories = (topStories + newStories + bestStories)
                    .distinctBy { it.id }
                    .sortedByDescending { it.score ?: 0 }
                
                allStories = combinedStories
                storiesLoaded = true
                
                // If there's already a query, re-search with new data
                if (_searchQuery.value.isNotBlank()) {
                    performSearch(_searchQuery.value)
                }
                
            } catch (e: Exception) {
                _error.value = "Failed to load stories: ${e.message}"
            }
        }
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            _error.value = null
            return
        }
        
        if (query.length >= 2) { // Start searching after 2 characters
            performSearch(query)
        }
    }
    
    fun executeSearch() {
        val query = _searchQuery.value.trim()
        if (query.isNotBlank()) {
            performSearch(query)
        }
    }
    
    private fun performSearch(query: String) {
        viewModelScope.launch {
            try {
                _isSearching.value = true
                _error.value = null
                
                if (!storiesLoaded) {
                    _error.value = "Loading stories... Please wait."
                    _isSearching.value = false
                    return@launch
                }
                
                val queryLower = query.lowercase().trim()
                
                // Simple but effective search
                val results = allStories.filter { story ->
                    // Search in title
                    val titleMatch = story.title?.lowercase()?.contains(queryLower) == true
                    
                    // Search in author
                    val authorMatch = story.by?.lowercase()?.contains(queryLower) == true
                    
                    // Search in story text content
                    val textMatch = story.text?.lowercase()?.contains(queryLower) == true
                    
                    // Search in URL domain
                    val urlMatch = try {
                        story.url?.let { url ->
                            val domain = java.net.URL(url).host.lowercase()
                            domain.contains(queryLower)
                        } ?: false
                    } catch (e: Exception) {
                        false
                    }
                    
                    titleMatch || authorMatch || textMatch || urlMatch
                }.take(50) // Limit results for performance
                
                _searchResults.value = results
                
            } catch (e: Exception) {
                _error.value = "Search failed: ${e.message}"
                _searchResults.value = emptyList()
            } finally {
                _isSearching.value = false
            }
        }
    }
    
    fun clearSearch() {
        _searchQuery.value = ""
        _searchResults.value = emptyList()
        _error.value = null
    }
    
    fun retry() {
        if (_searchQuery.value.isNotBlank()) {
            performSearch(_searchQuery.value)
        } else {
            loadStoriesForSearch()
        }
    }
}