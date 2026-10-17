package com.hackernews.futuristic.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.CommentHit
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import com.hackernews.futuristic.data.repository.SearchSort
import com.hackernews.futuristic.data.repository.SearchType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchFilters(
    val type: SearchType = SearchType.STORIES,
    val sort: SearchSort = SearchSort.RELEVANCE,
    val timeRange: RelativeTimeRange = RelativeTimeRange.ALL_TIME
)

data class SearchUiState(
    val stories: List<Story> = emptyList(),
    val comments: List<CommentHit> = emptyList(),
    val totalHits: Int = 0,
    val isSearching: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = false,
    val hasSearched: Boolean = false,
    val error: String? = null
)

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _filters = MutableStateFlow(SearchFilters())
    val filters: StateFlow<SearchFilters> = _filters.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    val bookmarkedIds: StateFlow<Set<Long>> = repository.getBookmarkedIds()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    private var searchJob: Job? = null
    private var currentPage = 0

    init {
        combine(_query.debounce(350), _filters) { query, filters -> query.trim() to filters }
            .distinctUntilChanged()
            .onEach { (query, filters) -> search(query, filters) }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun clearQuery() {
        _query.value = ""
    }

    fun setType(type: SearchType) = _filters.update { it.copy(type = type) }

    fun setSort(sort: SearchSort) = _filters.update { it.copy(sort = sort) }

    fun setTimeRange(range: RelativeTimeRange) = _filters.update { it.copy(timeRange = range) }

    fun retry() = search(_query.value.trim(), _filters.value)

    fun toggleBookmark(story: Story) {
        viewModelScope.launch { repository.toggleBookmark(story) }
    }

    fun loadMore() {
        val state = _uiState.value
        if (!state.hasMore || state.isSearching || state.isLoadingMore) return
        val query = _query.value.trim()
        val filters = _filters.value

        _uiState.value = state.copy(isLoadingMore = true)
        searchJob = viewModelScope.launch {
            repository.search(query, filters.type, filters.sort, filters.timeRange, currentPage + 1)
                .onSuccess { page ->
                    currentPage = page.page
                    _uiState.value = _uiState.value.copy(
                        stories = (_uiState.value.stories + page.stories).distinctBy { it.id },
                        comments = (_uiState.value.comments + page.comments).distinctBy { it.id },
                        hasMore = page.hasMore,
                        isLoadingMore = false
                    )
                }
                .onFailure { _uiState.value = _uiState.value.copy(isLoadingMore = false) }
        }
    }

    private fun search(query: String, filters: SearchFilters) {
        searchJob?.cancel()
        if (query.isEmpty()) {
            _uiState.value = SearchUiState()
            return
        }

        _uiState.value = _uiState.value.copy(isSearching = true, error = null)
        searchJob = viewModelScope.launch {
            repository.search(query, filters.type, filters.sort, filters.timeRange, page = 0)
                .onSuccess { page ->
                    currentPage = 0
                    _uiState.value = SearchUiState(
                        stories = page.stories,
                        comments = page.comments,
                        totalHits = page.totalHits,
                        hasMore = page.hasMore,
                        hasSearched = true
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isSearching = false,
                        isLoadingMore = false,
                        hasSearched = true,
                        error = error.message ?: "Search failed"
                    )
                }
        }
    }
}
