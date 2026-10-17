package com.hackernews.futuristic.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.CommentNode
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/** One row in the flattened comment list. */
data class VisibleComment(
    val node: CommentNode,
    val depth: Int,
    val isCollapsed: Boolean,
    /** Replies hidden beneath this comment while collapsed. */
    val hiddenReplies: Int
)

data class StoryDetailUiState(
    val story: Story? = null,
    val comments: List<VisibleComment> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingComments: Boolean = false,
    val error: String? = null,
    val commentsError: String? = null
)

@HiltViewModel
class StoryDetailViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoryDetailUiState())
    val uiState: StateFlow<StoryDetailUiState> = _uiState.asStateFlow()

    val bookmarkedIds: StateFlow<Set<Long>> = repository.getBookmarkedIds()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    private var commentTree: List<CommentNode> = emptyList()
    private val collapsed = mutableSetOf<Long>()
    private var loadedStoryId: Long? = null

    fun loadStory(storyId: Long) {
        if (loadedStoryId == storyId && _uiState.value.error == null) return
        loadedStoryId = storyId
        _uiState.value = StoryDetailUiState(isLoading = true)

        viewModelScope.launch {
            repository.getStory(storyId).fold(
                onSuccess = { story ->
                    _uiState.value = _uiState.value.copy(story = story, isLoading = false)
                    loadComments(story)
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load story"
                    )
                }
            )
        }
    }

    fun retry() {
        val story = _uiState.value.story
        if (story == null) {
            loadedStoryId?.let { id ->
                loadedStoryId = null
                loadStory(id)
            }
        } else {
            loadComments(story)
        }
    }

    fun toggleCollapsed(commentId: Long) {
        if (!collapsed.remove(commentId)) collapsed.add(commentId)
        publishComments()
    }

    fun toggleBookmark() {
        val story = _uiState.value.story ?: return
        viewModelScope.launch { repository.toggleBookmark(story) }
    }

    private fun loadComments(story: Story) {
        if (story.kids.isNullOrEmpty()) return
        _uiState.value = _uiState.value.copy(isLoadingComments = true, commentsError = null)

        viewModelScope.launch {
            repository.getCommentTree(story).fold(
                onSuccess = { tree ->
                    commentTree = tree
                    _uiState.value = _uiState.value.copy(isLoadingComments = false)
                    publishComments()
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoadingComments = false,
                        commentsError = error.message ?: "Failed to load comments"
                    )
                }
            )
        }
    }

    private fun publishComments() {
        val rows = mutableListOf<VisibleComment>()
        fun visit(node: CommentNode, depth: Int) {
            // Deleted comments with no replies are pure noise.
            if (node.isDeleted && node.children.isEmpty()) return
            val isCollapsed = node.id in collapsed
            rows += VisibleComment(
                node = node,
                depth = depth,
                isCollapsed = isCollapsed,
                hiddenReplies = if (isCollapsed) node.descendantCount() else 0
            )
            if (!isCollapsed) node.children.forEach { visit(it, depth + 1) }
        }
        commentTree.forEach { visit(it, 0) }
        _uiState.value = _uiState.value.copy(comments = rows)
    }
}
