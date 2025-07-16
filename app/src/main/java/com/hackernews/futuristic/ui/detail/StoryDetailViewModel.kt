package com.hackernews.futuristic.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.analyzer.CommentThreadAnalyzer
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.CommentThread
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import com.hackernews.futuristic.ui.components.CommentViewMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailViewModel @Inject constructor(
    private val repository: HackerNewsRepository,
    private val commentThreadAnalyzer: CommentThreadAnalyzer
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(StoryDetailUiState())
    val uiState: StateFlow<StoryDetailUiState> = _uiState.asStateFlow()
    
    private val _commentViewMode = MutableStateFlow(CommentViewMode.MATRIX)
    val commentViewMode: StateFlow<CommentViewMode> = _commentViewMode.asStateFlow()
    
    fun loadStory(storyId: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        
        viewModelScope.launch {
            repository.getStory(storyId).fold(
                onSuccess = { story ->
                    _uiState.value = _uiState.value.copy(
                        story = story,
                        isLoading = false,
                        error = null
                    )
                    
                    // Load comments if they exist
                    story.kids?.let { commentIds ->
                        loadComments(commentIds)
                    }
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Unknown error"
                    )
                }
            )
        }
    }
    
    private fun loadComments(commentIds: List<Long>) {
        _uiState.value = _uiState.value.copy(isLoadingComments = true)
        
        viewModelScope.launch {
            // Use fast loading for better performance - only load top-level comments initially
            repository.getTopLevelComments(commentIds, limit = 30).fold(
                onSuccess = { comments ->
                    _uiState.value = _uiState.value.copy(
                        comments = comments,
                        isLoadingComments = false,
                        commentsError = null
                    )
                    
                    // Show loading message if there are more comments
                    if (commentIds.size > 30) {
                        _uiState.value = _uiState.value.copy(
                            commentsError = "Showing first 30 comments for faster loading. Tap comments to expand replies."
                        )
                    }
                    
                    // Analyze comment thread for Matrix visualization
                    if (comments.isNotEmpty()) {
                        analyzeCommentThread(comments)
                    }
                },
                onFailure = { 
                    _uiState.value = _uiState.value.copy(
                        isLoadingComments = false,
                        commentsError = "Failed to load comments"
                    )
                }
            )
        }
    }
    
    // New method to load child comments on demand
    fun loadChildComments(parentComment: Comment) {
        viewModelScope.launch {
            repository.getChildComments(parentComment).fold(
                onSuccess = { children ->
                    // Add children to the current comments list
                    val currentComments = _uiState.value.comments.toMutableList()
                    currentComments.addAll(children)
                    _uiState.value = _uiState.value.copy(comments = currentComments)
                },
                onFailure = { 
                    // Silently fail for child comments
                }
            )
        }
    }
    
    fun retry() {
        _uiState.value.story?.let { story ->
            loadStory(story.id)
        }
    }
    
    fun setCommentViewMode(mode: CommentViewMode) {
        _commentViewMode.value = mode
    }
    
    private fun analyzeCommentThread(comments: List<Comment>) {
        viewModelScope.launch {
            try {
                val storyId = _uiState.value.story?.id ?: return@launch
                val commentThread = commentThreadAnalyzer.analyzeCommentThread(comments, storyId)
                
                _uiState.value = _uiState.value.copy(
                    commentThread = commentThread
                )
            } catch (e: Exception) {
                // Handle analysis errors gracefully
            }
        }
    }
}

data class StoryDetailUiState(
    val story: Story? = null,
    val comments: List<Comment> = emptyList(),
    val commentThread: CommentThread? = null,
    val isLoading: Boolean = false,
    val isLoadingComments: Boolean = false,
    val error: String? = null,
    val commentsError: String? = null
)