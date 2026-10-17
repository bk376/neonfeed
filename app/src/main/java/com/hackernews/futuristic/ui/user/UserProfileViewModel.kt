package com.hackernews.futuristic.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.api.User
import com.hackernews.futuristic.data.model.CommentHit
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import com.hackernews.futuristic.data.repository.SearchPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UserTab(val label: String) { STORIES("Submissions"), COMMENTS("Comments") }

/** One paged list on the profile (submissions or comments). */
data class PagedList<T>(
    val items: List<T> = emptyList(),
    val page: Int = -1,
    val totalHits: Int = 0,
    val hasMore: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class UserProfileUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTab: UserTab = UserTab.STORIES,
    val stories: PagedList<Story> = PagedList(),
    val comments: PagedList<CommentHit> = PagedList()
)

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfileUiState())
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()

    val bookmarkedIds: StateFlow<Set<Long>> = repository.getBookmarkedIds()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    private var username: String? = null

    fun loadUserProfile(username: String) {
        if (this.username == username && _uiState.value.error == null) return
        this.username = username
        _uiState.value = UserProfileUiState(isLoading = true)

        viewModelScope.launch {
            repository.getUser(username).fold(
                onSuccess = { user -> _uiState.update { it.copy(user = user, isLoading = false) } },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message ?: "User not found") }
                }
            )
        }
        loadMoreStories()
        loadMoreComments()
    }

    fun retry() {
        val name = username ?: return
        username = null
        loadUserProfile(name)
    }

    fun selectTab(tab: UserTab) = _uiState.update { it.copy(selectedTab = tab) }

    fun loadMore() = when (_uiState.value.selectedTab) {
        UserTab.STORIES -> loadMoreStories()
        UserTab.COMMENTS -> loadMoreComments()
    }

    fun toggleBookmark(story: Story) {
        viewModelScope.launch { repository.toggleBookmark(story) }
    }

    private fun loadMoreStories() = loadNextPage(
        list = { it.stories },
        update = { state, list -> state.copy(stories = list) },
        fetch = { name, page -> repository.getUserStories(name, page) },
        itemsOf = { it.stories },
        idOf = { it.id }
    )

    private fun loadMoreComments() = loadNextPage(
        list = { it.comments },
        update = { state, list -> state.copy(comments = list) },
        fetch = { name, page -> repository.getUserComments(name, page) },
        itemsOf = { it.comments },
        idOf = { it.id }
    )

    private fun <T> loadNextPage(
        list: (UserProfileUiState) -> PagedList<T>,
        update: (UserProfileUiState, PagedList<T>) -> UserProfileUiState,
        fetch: suspend (String, Int) -> Result<SearchPage>,
        itemsOf: (SearchPage) -> List<T>,
        idOf: (T) -> Long
    ) {
        val name = username ?: return
        val current = list(_uiState.value)
        if (current.isLoading || !current.hasMore) return
        _uiState.update { update(it, current.copy(isLoading = true, error = null)) }

        viewModelScope.launch {
            fetch(name, current.page + 1).fold(
                onSuccess = { page ->
                    _uiState.update { state ->
                        val old = list(state)
                        update(
                            state,
                            old.copy(
                                items = (old.items + itemsOf(page)).distinctBy(idOf),
                                page = page.page,
                                totalHits = page.totalHits,
                                hasMore = page.hasMore,
                                isLoading = false
                            )
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update { state ->
                        update(state, list(state).copy(isLoading = false, error = error.message ?: "Failed to load"))
                    }
                }
            )
        }
    }
}
