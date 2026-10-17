package com.hackernews.futuristic.ui.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.model.Story
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

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()

    private val _selectedTimeRange = MutableStateFlow(RelativeTimeRange.TODAY)
    val selectedTimeRange: StateFlow<RelativeTimeRange> = _selectedTimeRange.asStateFlow()

    val bookmarkedIds: StateFlow<Set<Long>> = repository.getBookmarkedIds()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    private var allStoryIds: List<Long> = emptyList()
    private var nextIndex = 0
    private var loadJob: Job? = null

    init {
        loadStories(refresh = true)
    }

    fun refreshStories() = loadStories(refresh = true)

    fun loadMoreStories() {
        if (allStoryIds.isNotEmpty() && nextIndex < allStoryIds.size) {
            loadStories(refresh = false)
        }
    }

    fun setTimeRange(timeRange: RelativeTimeRange) {
        if (_selectedTimeRange.value == timeRange) return
        _selectedTimeRange.value = timeRange
        loadStories(refresh = true)
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
            error = null
        )

        loadJob = viewModelScope.launch {
            if (refresh) {
                repository.getTopStoryIds()
                    .onSuccess { ids ->
                        allStoryIds = ids
                        nextIndex = 0
                    }
                    .onFailure { error ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = error.message ?: "Failed to load stories"
                        )
                        return@launch
                    }
            }

            // A strict time filter can drop a whole page; keep going so the list
            // grows and infinite scroll doesn't stall on an unchanged layout.
            val newStories = mutableListOf<Story>()
            var pagesLoaded = 0
            while (newStories.size < MIN_NEW_STORIES && nextIndex < allStoryIds.size && pagesLoaded < MAX_PAGES_PER_LOAD) {
                val pageIds = allStoryIds.subList(nextIndex, minOf(nextIndex + PAGE_SIZE, allStoryIds.size))
                val page = repository.getStoriesByIds(pageIds).getOrElse { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load stories"
                    )
                    return@launch
                }
                nextIndex += pageIds.size
                pagesLoaded++
                newStories += page.filter { it.isWithin(_selectedTimeRange.value) }
            }

            _uiState.value = _uiState.value.copy(
                stories = (_uiState.value.stories + newStories).distinctBy { it.id },
                isLoading = false,
                hasMore = nextIndex < allStoryIds.size
            )
        }
    }

    private fun Story.isWithin(range: RelativeTimeRange): Boolean {
        if (range == RelativeTimeRange.ALL_TIME) return true
        val cutoff = System.currentTimeMillis() / 1000 - range.hours * 3600
        return time >= cutoff
    }

    private companion object {
        const val PAGE_SIZE = 20
        const val MIN_NEW_STORIES = 10
        const val MAX_PAGES_PER_LOAD = 5
    }
}

data class StoriesUiState(
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = false,
    val hasMore: Boolean = true,
    val error: String? = null
)
