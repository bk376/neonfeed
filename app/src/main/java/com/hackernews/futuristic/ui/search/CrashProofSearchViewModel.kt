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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CrashProofSearchViewModel @Inject constructor(
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
    
    private val _bookmarkedStories = MutableStateFlow<Set<Long>>(emptySet())
    val bookmarkedStories: StateFlow<Set<Long>> = _bookmarkedStories.asStateFlow()
    
    // Simple cache for stories - no complex data structures
    private var cachedStories: List<Story> = emptyList()
    private var cacheLoaded = false
    
    init {
        loadStoriesCache()
        updateBookmarkedStories()
    }
    
    private fun updateBookmarkedStories() {
        viewModelScope.launch {
            try {
                repository.getBookmarkedStories().collect { bookmarks ->
                    _bookmarkedStories.value = bookmarks.map { it.id }.toSet()
                }
            } catch (e: Exception) {
                // Handle error silently
            }
        }
    }
    
    fun toggleBookmark(story: Story) {
        viewModelScope.launch {
            try {
                val isCurrentlyBookmarked = repository.isBookmarked(story.id)
                if (isCurrentlyBookmarked) {
                    repository.removeBookmark(story.id)
                } else {
                    repository.bookmarkStory(story)
                }
            } catch (e: Exception) {
                // Handle error silently
            }
        }
    }
    
    fun isBookmarked(storyId: Long): Boolean {
        return storyId in _bookmarkedStories.value
    }
    
    private fun loadStoriesCache() {
        viewModelScope.launch {
            try {
                // Load optimized story collection for fast search
                val stories = mutableListOf<Story>()
                
                // Reduced initial load for faster startup - load more popular/recent stories
                // Get top stories first (most relevant and high quality)
                repository.getTopStories(200).fold(
                    onSuccess = { topStories -> stories.addAll(topStories) },
                    onFailure = { /* Continue with what we have */ }
                )
                
                // Get new stories (latest content) - reduced for faster loading
                repository.getNewStories(150).fold(
                    onSuccess = { newStories -> 
                        stories.addAll(newStories.filter { new -> 
                            stories.none { it.id == new.id } // Avoid duplicates
                        })
                    },
                    onFailure = { /* Continue with what we have */ }
                )
                
                // Get best stories (highest quality content) - reduced for faster loading
                repository.getBestStories(150).fold(
                    onSuccess = { bestStories -> 
                        stories.addAll(bestStories.filter { best -> 
                            stories.none { it.id == best.id } // Avoid duplicates
                        })
                    },
                    onFailure = { /* Continue with what we have */ }
                )
                
                cachedStories = stories
                cacheLoaded = true
                
            } catch (e: Exception) {
                // Even if loading fails, we don't crash
                _error.value = "Failed to load stories for search"
                cacheLoaded = true // Mark as loaded to avoid infinite retries
            }
        }
    }
    
    fun updateSearchQuery(query: String) {
        try {
            _searchQuery.value = query.take(100) // Limit query length
            
            // Clear results if query is empty
            if (query.isBlank()) {
                _searchResults.value = emptyList()
                _error.value = null
                return
            }
            
            // Only search if query is meaningful
            if (query.trim().length >= 2) {
                performSearch(query.trim())
            }
        } catch (e: Exception) {
            // Never crash on query update
            _error.value = "Search input error"
        }
    }
    
    fun executeSearch() {
        try {
            val query = _searchQuery.value.trim()
            if (query.isNotBlank() && query.length >= 2) {
                performSearch(query)
            }
        } catch (e: Exception) {
            _error.value = "Search execution error"
        }
    }
    
    private fun performSearch(query: String) {
        viewModelScope.launch {
            try {
                _isSearching.value = true
                _error.value = null
                
                // Switch to IO thread for heavy work
                val results = withContext(Dispatchers.IO) {
                    searchStories(query)
                }
                
                _searchResults.value = results
                
            } catch (e: Exception) {
                _error.value = "Search failed: ${e.message}"
                _searchResults.value = emptyList()
            } finally {
                _isSearching.value = false
            }
        }
    }
    
    private suspend fun searchStories(query: String): List<Story> {
        return try {
            if (!cacheLoaded) {
                return emptyList()
            }
            
            val queryLower = query.lowercase().trim()
            val queryWords = queryLower.split("\\s+".toRegex()).filter { it.isNotBlank() }
            val scoredResults = mutableListOf<Pair<Story, Double>>()
            
            // Early exit optimization: limit search to most relevant stories first
            val maxResults = 100 // Limit results for faster display
            var processedCount = 0
            
            // Sophisticated search through cached stories with optimizations
            for (story in cachedStories) {
                try {
                    var score = 0.0
                    var hasMatch = false
                    
                    // Title search (highest weight) - prioritize title matches
                    story.title?.let { title ->
                        val titleLower = title.lowercase()
                        if (titleLower.contains(queryLower)) {
                            score += 100.0 // Exact phrase match
                            hasMatch = true
                        }
                        // Word-by-word matching
                        queryWords.forEach { word ->
                            if (titleLower.contains(word)) {
                                score += 50.0 // Individual word match
                                hasMatch = true
                            }
                        }
                        // Bonus for title starting with query
                        if (titleLower.startsWith(queryLower)) {
                            score += 50.0
                            hasMatch = true
                        }
                    }
                    
                    // Author search (medium weight)
                    story.by?.let { author ->
                        val authorLower = author.lowercase()
                        if (authorLower.contains(queryLower)) {
                            score += 30.0
                            hasMatch = true
                        }
                        queryWords.forEach { word ->
                            if (authorLower.contains(word)) {
                                score += 15.0
                                hasMatch = true
                            }
                        }
                    }
                    
                    // Only search text content if we haven't found strong matches yet
                    if (score < 50.0) {
                        story.text?.let { text ->
                            if (text.length < 1000) { // Reduced limit for faster search
                                val textLower = text.lowercase()
                                if (textLower.contains(queryLower)) {
                                    score += 20.0
                                    hasMatch = true
                                }
                                queryWords.forEach { word ->
                                    if (textLower.contains(word)) {
                                        score += 10.0
                                        hasMatch = true
                                    }
                                }
                            }
                        }
                    }
                    
                    // URL/Domain search (low weight) - only if no strong matches
                    if (score < 30.0) {
                        story.url?.let { url ->
                            try {
                                val urlLower = url.lowercase()
                                val domain = url.substringAfter("://").substringBefore("/").lowercase()
                                
                                if (urlLower.contains(queryLower) || domain.contains(queryLower)) {
                                    score += 15.0
                                    hasMatch = true
                                }
                                queryWords.forEach { word ->
                                    if (urlLower.contains(word) || domain.contains(word)) {
                                        score += 5.0
                                        hasMatch = true
                                    }
                                }
                            } catch (e: Exception) {
                                // Ignore URL parsing errors
                            }
                        }
                    }
                    
                    if (hasMatch && score > 0) {
                        // Boost recent stories slightly
                        val hoursAgo = (System.currentTimeMillis() / 1000 - story.time) / 3600
                        if (hoursAgo < 24) {
                            score += 5.0
                        } else if (hoursAgo < 168) { // Last week
                            score += 2.0
                        }
                        
                        // Boost high-scored stories
                        val storyScore = story.score ?: 0
                        score += storyScore * 0.1 // Small boost based on story score
                        
                        scoredResults.add(Pair(story, score))
                        processedCount++
                        
                        // Early termination for very good matches to improve speed
                        if (score > 150.0 && scoredResults.size > 50) {
                            break
                        }
                    }
                    
                } catch (e: Exception) {
                    // Skip this story if any error occurs
                    continue
                }
            }
            
            // Sort by relevance score (descending) and limit results for faster display
            scoredResults
                .sortedByDescending { it.second }
                .take(maxResults) // Limit to first 100 results for speed
                .map { it.first }
            
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun clearSearch() {
        try {
            _searchQuery.value = ""
            _searchResults.value = emptyList()
            _error.value = null
        } catch (e: Exception) {
            // Never crash on clear
        }
    }
    
    fun retry() {
        try {
            if (!cacheLoaded) {
                loadStoriesCache()
            } else {
                val query = _searchQuery.value.trim()
                if (query.isNotBlank()) {
                    performSearch(query)
                }
            }
        } catch (e: Exception) {
            _error.value = "Retry failed"
        }
    }
}