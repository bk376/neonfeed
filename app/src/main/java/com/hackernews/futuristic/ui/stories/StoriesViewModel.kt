package com.hackernews.futuristic.ui.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.Feed
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Which list Home shows. [topRange] only applies to [Feed.TOP]. */
data class FeedSelection(
    val feed: Feed = Feed.FRONT_PAGE,
    val topRange: RelativeTimeRange = RelativeTimeRange.LAST_WEEK
)

data class StoriesUiState(
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = false,
    val hasMore: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()

    private val _selection = MutableStateFlow(FeedSelection())
    val selection: StateFlow<FeedSelection> = _selection.asStateFlow()

    val bookmarkedIds: StateFlow<Set<Long>> = repository.getBookmarkedIds()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    // Firebase feeds: the full ranked ID list, fetched in pages of items.
    private var feedIds: List<Long> = emptyList()
    private var nextIndex = 0

    // Top feed: Algolia pages.
    private var nextTopPage = 0

    private var loadJob: Job? = null

    init {
        loadStories(refresh = true)
    }

    fun selectFeed(feed: Feed) {
        if (_selection.value.feed == feed) return
        _selection.value = _selection.value.copy(feed = feed)
        loadStories(refresh = true)
    }

    fun selectTop(range: RelativeTimeRange) {
        if (_selection.value == FeedSelection(Feed.TOP, range)) return
        _selection.value = FeedSelection(Feed.TOP, range)
        loadStories(refresh = true)
    }

    fun refreshStories() = loadStories(refresh = true)

    fun loadMoreStories() {
        if (_uiState.value.hasMore) loadStories(refresh = false)
    }

    fun toggleBookmark(story: Story) {
        viewModelScope.launch { repository.toggleBookmark(story) }
    }

    private fun loadStories(refresh: Boolean) {
        if (refresh) {
            loadJob?.cancel()
        } else if (loadJob?.isActive == true) {
            return
        }

        _uiState.value = _uiState.value.copy(
            stories = if (refresh) emptyList() else _uiState.value.stories,
            isLoading = true,
            hasMore = if (refresh) true else _uiState.value.hasMore,
            error = null
        )

        val selection = _selection.value
        loadJob = viewModelScope.launch {
            val result = if (selection.feed == Feed.TOP) {
                loadTopPage(selection.topRange, refresh)
            } else {
                loadFeedPage(selection.feed, refresh)
            }
            result
                .onSuccess { (stories, hasMore) ->
                    _uiState.value = _uiState.value.copy(
                        stories = (_uiState.value.stories + stories).distinctBy { it.id },
                        isLoading = false,
                        hasMore = hasMore
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load stories"
                    )
                }
        }
    }

    private suspend fun loadFeedPage(feed: Feed, refresh: Boolean): Result<Pair<List<Story>, Boolean>> = runCatching {
        if (refresh) {
            feedIds = repository.getFeedIds(feed).getOrThrow()
            nextIndex = 0
        }
        val pageIds = feedIds.subList(nextIndex, minOf(nextIndex + PAGE_SIZE, feedIds.size))
        val stories = repository.getStoriesByIds(pageIds).getOrThrow()
        nextIndex += pageIds.size
        stories to (nextIndex < feedIds.size)
    }

    private suspend fun loadTopPage(range: RelativeTimeRange, refresh: Boolean): Result<Pair<List<Story>, Boolean>> {
        if (refresh) nextTopPage = 0
        return repository.getTopStories(range, nextTopPage).map { page ->
            nextTopPage = page.page + 1
            page.stories to page.hasMore
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}
