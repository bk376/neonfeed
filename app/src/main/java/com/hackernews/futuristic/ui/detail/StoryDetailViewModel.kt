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
            try {
                // Load top-level comments first (limited for fast initial display)
                val limitedIds = commentIds.take(30) // Increased limit
                
                repository.getComments(limitedIds).fold(
                    onSuccess = { topLevelComments ->
                        // Show top-level comments immediately
                        _uiState.value = _uiState.value.copy(
                            comments = topLevelComments,
                            isLoadingComments = false,
                            commentsError = null
                        )
                        
                        // Analyze comment thread for Matrix visualization
                        if (topLevelComments.isNotEmpty()) {
                            analyzeCommentThread(topLevelComments)
                        }
                        
                        // Load child comments recursively for better hierarchy
                        loadAllChildComments(topLevelComments)
                        
                        // Load remaining top-level comments in background if there are more
                        if (commentIds.size > 30) {
                            loadRemainingComments(commentIds.drop(30))
                        }
                    },
                    onFailure = { 
                        _uiState.value = _uiState.value.copy(
                            isLoadingComments = false,
                            commentsError = "Failed to load comments"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoadingComments = false,
                    commentsError = "Error loading comments: ${e.message}"
                )
            }
        }
    }
    
    private fun loadAllChildComments(parentComments: List<Comment>) {
        viewModelScope.launch {
            try {
                val allComments = mutableListOf<Comment>()
                allComments.addAll(parentComments)
                
                // Load children for each parent comment
                parentComments.forEach { parent ->
                    val childComments = loadChildCommentsRecursively(parent)
                    allComments.addAll(childComments)
                }
                
                // Update the state with all comments including children
                _uiState.value = _uiState.value.copy(
                    comments = allComments.distinctBy { it.id } // Remove duplicates
                )
            } catch (e: Exception) {
                // Silently fail for child comments to avoid blocking the UI
            }
        }
    }
    
    private fun loadRemainingComments(remainingIds: List<Long>) {
        viewModelScope.launch {
            try {
                // Load remaining comments in batches
                val batchSize = 10
                remainingIds.chunked(batchSize).forEach { batch ->
                    repository.getComments(batch).fold(
                        onSuccess = { newComments ->
                            // Add new comments to existing list
                            val currentComments = _uiState.value.comments.toMutableList()
                            currentComments.addAll(newComments)
                            
                            _uiState.value = _uiState.value.copy(
                                comments = currentComments
                            )
                            
                            // Load child comments for these new comments too
                            loadAllChildComments(newComments)
                        },
                        onFailure = { 
                            // Silently fail for remaining comments
                        }
                    )
                    
                    // Small delay between batches to avoid blocking UI
                    kotlinx.coroutines.delay(100)
                }
            } catch (e: Exception) {
                // Silently fail for background loading
            }
        }
    }
    
    private suspend fun loadChildCommentsRecursively(parentComment: Comment): List<Comment> {
        val childComments = mutableListOf<Comment>()
        
        parentComment.kids?.let { childIds ->
            if (childIds.isNotEmpty()) {
                repository.getComments(childIds).fold(
                    onSuccess = { children ->
                        childComments.addAll(children)
                        
                        // Recursively load children's children
                        children.forEach { child ->
                            val grandchildren = loadChildCommentsRecursively(child)
                            childComments.addAll(grandchildren)
                        }
                    },
                    onFailure = { 
                        // Silently fail for child comments to avoid blocking the UI
                    }
                )
            }
        }
        
        return childComments
    }
    
    // Load child comments on demand (when user expands a comment)
    fun loadChildComments(parentComment: Comment) {
        viewModelScope.launch {
            try {
                parentComment.kids?.let { childIds ->
                    if (childIds.isNotEmpty()) {
                        // Load children in small batches to avoid blocking UI
                        val batchSize = 5
                        childIds.take(10).chunked(batchSize).forEach { batch ->
                            repository.getComments(batch).fold(
                                onSuccess = { children ->
                                    // Add children to the current comments list
                                    val currentComments = _uiState.value.comments.toMutableList()
                                    
                                    // Find the correct position to insert children (after parent)
                                    val parentIndex = currentComments.indexOfFirst { it.id == parentComment.id }
                                    if (parentIndex != -1) {
                                        // Insert children after parent
                                        currentComments.addAll(parentIndex + 1, children)
                                    } else {
                                        // Fallback: add at the end
                                        currentComments.addAll(children)
                                    }
                                    
                                    _uiState.value = _uiState.value.copy(comments = currentComments)
                                },
                                onFailure = { 
                                    // Silently fail for child comments
                                }
                            )
                            
                            // Small delay between batches
                            kotlinx.coroutines.delay(50)
                        }
                    }
                }
            } catch (e: Exception) {
                // Silently fail for child comment loading
            }
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