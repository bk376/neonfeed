package com.hackernews.futuristic.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {
    
    private val _bookmarkedStories = MutableStateFlow<List<Story>>(emptyList())
    val bookmarkedStories: StateFlow<List<Story>> = _bookmarkedStories.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    init {
        loadBookmarks()
    }
    
    private fun loadBookmarks() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                repository.getBookmarkedStories()
                    .catch { e ->
                        _error.value = "Failed to load bookmarks: ${e.message}"
                        _isLoading.value = false
                    }
                    .collect { stories ->
                        _bookmarkedStories.value = stories
                        _isLoading.value = false
                    }
            } catch (e: Exception) {
                _error.value = "Failed to load bookmarks: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    fun removeBookmark(story: Story) {
        viewModelScope.launch {
            try {
                repository.removeBookmark(story.id)
                // The Flow will automatically update the UI
            } catch (e: Exception) {
                _error.value = "Failed to remove bookmark: ${e.message}"
            }
        }
    }
    
    fun refreshBookmarks() {
        loadBookmarks()
    }
}