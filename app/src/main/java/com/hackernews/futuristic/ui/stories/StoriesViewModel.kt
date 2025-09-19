package com.hackernews.futuristic.ui.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.analyzer.NeuralNetworkAnalyzer
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.model.StoryAnalysis
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.profile.UserProfileManager
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import com.hackernews.futuristic.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: HackerNewsRepository,
    private val neuralNetworkAnalyzer: NeuralNetworkAnalyzer,
    private val userProfileManager: UserProfileManager,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()
    
    private val _selectedStoryType = MutableStateFlow(StoryType.TOP)
    val selectedStoryType: StateFlow<StoryType> = _selectedStoryType.asStateFlow()
    
    private val _analysisResults = MutableStateFlow<Map<Long, StoryAnalysis>>(emptyMap())
    val analysisResults: StateFlow<Map<Long, StoryAnalysis>> = _analysisResults.asStateFlow()
    
    val isNeuralAnalysisEnabled: StateFlow<Boolean> = settingsRepository.settingsState.map { it.isNeuralAnalysisEnabled }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )
    
    val isHolographicModeEnabled: StateFlow<Boolean> = settingsRepository.settingsState.map { it.isHolographicEnabled }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )
    
    private val _bookmarkedStories = MutableStateFlow<Set<Long>>(emptySet())
    val bookmarkedStories: StateFlow<Set<Long>> = _bookmarkedStories.asStateFlow()
    
    private val _selectedTimeRange = MutableStateFlow(RelativeTimeRange.TODAY)
    val selectedTimeRange: StateFlow<RelativeTimeRange> = _selectedTimeRange.asStateFlow()
    
    private var currentPage = 0
    private var allStoryIds: List<Long> = emptyList()
    private val pageSize = 20
    
    val userProfile = userProfileManager.userProfile
    val achievements = userProfileManager.achievements
    
    init {
        loadStories()
        updateBookmarkedStories()
    }
    
    fun loadStories(refresh: Boolean = false) {
        if (refresh) {
            currentPage = 0
            allStoryIds = emptyList()
            _uiState.value = _uiState.value.copy(
                stories = emptyList(),
                isLoading = true,
                error = null
            )
        } else if (_uiState.value.isLoading) {
            return
        }
        
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        
        viewModelScope.launch {
            try {
                // First load all story IDs if we don't have them
                if (allStoryIds.isEmpty()) {
                    // Always load TOP stories since we removed the type selector
                    val idsResult = repository.getTopStoryIds()
                    
                    idsResult.fold(
                        onSuccess = { ids -> allStoryIds = ids },
                        onFailure = { error ->
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = error.message ?: "Failed to load story IDs"
                            )
                            return@launch
                        }
                    )
                }
                
                // Load stories for current page
                val startIndex = currentPage * pageSize
                val endIndex = minOf(startIndex + pageSize, allStoryIds.size)
                val pageStoryIds = allStoryIds.subList(startIndex, endIndex)
                
                if (pageStoryIds.isNotEmpty()) {
                    val storiesResult = repository.getStoriesByIds(pageStoryIds)
                    
                    storiesResult.fold(
                        onSuccess = { newStories ->
                            val currentStories = if (refresh) emptyList() else _uiState.value.stories
                            
                            // Apply time filtering to new stories
                            val filteredNewStories = filterStoriesByTimeRange(newStories, _selectedTimeRange.value)
                            val allStories = currentStories + filteredNewStories
                            
                            // Debug: log story info
                            println("StoriesDebug: Loaded ${newStories.size} new stories, filtered to ${filteredNewStories.size}")
                            if (newStories.isNotEmpty()) {
                                val sampleStory = newStories.first()
                                println("StoriesDebug: Sample story time=${sampleStory.time}, title=${sampleStory.title?.take(50)}")
                            }
                            
                            _uiState.value = _uiState.value.copy(
                                stories = allStories,
                                isLoading = false,
                                error = null
                            )
                            
                            currentPage++
                            
                            // Start neural network analysis
                            if (isNeuralAnalysisEnabled.value) {
                                analyzeStories(newStories)
                            }
                        },
                        onFailure = { error ->
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = error.message ?: "Failed to load stories"
                            )
                        }
                    )
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
    
    fun loadMoreStories() {
        if (!_uiState.value.isLoading && allStoryIds.isNotEmpty()) {
            val startIndex = currentPage * pageSize
            if (startIndex < allStoryIds.size) {
                loadStories(refresh = false)
            }
        }
    }
    
    fun selectStoryType(type: StoryType) {
        if (_selectedStoryType.value != type) {
            _selectedStoryType.value = type
            loadStories(refresh = true)
        }
    }
    
    fun retry() {
        loadStories(refresh = true)
    }
    
    fun refreshStories() {
        loadStories(refresh = true)
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
                
                // Update local state
                updateBookmarkedStories()
                
            } catch (e: Exception) {
                // Handle error silently or show a toast
            }
        }
    }
    
    private fun updateBookmarkedStories() {
        viewModelScope.launch {
            try {
                repository.getBookmarkedStories().collect { bookmarks ->
                    _bookmarkedStories.value = bookmarks.map { it.id }.toSet()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun isBookmarked(storyId: Long): Boolean {
        return storyId in _bookmarkedStories.value
    }
    
    fun toggleNeuralAnalysis() {
        // Neural analysis is now controlled by settings
        // This method is kept for compatibility but doesn't do anything
        // Settings changes will automatically trigger analysis through the StateFlow
    }
    
    fun toggleHolographicMode() {
        // Holographic mode is now controlled by settings
        // This method is kept for compatibility but doesn't do anything
        // Settings changes will automatically trigger mode through the StateFlow
    }
    
    fun getStoryAnalysis(storyId: Long): StoryAnalysis? {
        return _analysisResults.value[storyId]
    }
    
    private fun analyzeStories(stories: List<Story>) {
        viewModelScope.launch {
            val newAnalysis = mutableMapOf<Long, StoryAnalysis>()
            
            stories.forEach { story ->
                try {
                    val analysis = neuralNetworkAnalyzer.analyzeStory(story)
                    newAnalysis[story.id] = analysis
                    
                    // Update user profile with story analysis
                    userProfileManager.updateStoryAnalysis(analysis)
                } catch (e: Exception) {
                    // Handle analysis errors gracefully
                }
            }
            
            _analysisResults.value = newAnalysis
        }
    }
    
    fun onStoryRead(story: Story, readingTime: Long, completed: Boolean) {
        userProfileManager.updateReadingStats(story, readingTime, completed)
    }
    
    fun setTimeRange(timeRange: RelativeTimeRange) {
        _selectedTimeRange.value = timeRange
        // Reload stories with new time filter
        loadStories(refresh = true)
    }
    
    private fun filterStoriesByTimeRange(stories: List<Story>, timeRange: RelativeTimeRange): List<Story> {
        if (timeRange == RelativeTimeRange.ALL_TIME) {
            return stories
        }
        
        val currentTime = System.currentTimeMillis() / 1000 // HackerNews uses Unix timestamp in seconds
        val cutoffTime = currentTime - (timeRange.hours * 3600)
        
        val filtered = stories.filter { story ->
            val storyTime = story.time ?: 0L
            storyTime >= cutoffTime
        }
        
        // Debug logging to verify filtering
        println("FilterDebug: TimeRange=${timeRange.displayName}, Stories before=${stories.size}, after=${filtered.size}")
        println("FilterDebug: Current time=$currentTime, Cutoff=$cutoffTime")
        println("FilterDebug: Hours filter=${timeRange.hours}")
        
        if (stories.isNotEmpty()) {
            val newestOriginal = stories.maxByOrNull { it.time ?: 0L }
            val oldestOriginal = stories.minByOrNull { it.time ?: 0L }
            println("FilterDebug: Original - Newest=${newestOriginal?.time}, Oldest=${oldestOriginal?.time}")
        }
        
        if (filtered.isNotEmpty()) {
            val newestFiltered = filtered.maxByOrNull { it.time ?: 0L }
            val oldestFiltered = filtered.minByOrNull { it.time ?: 0L }
            println("FilterDebug: Filtered - Newest=${newestFiltered?.time}, Oldest=${oldestFiltered?.time}")
        }
        
        // If filtered results are too few (less than 5), show more recent stories as fallback
        return if (filtered.size < 5 && timeRange != RelativeTimeRange.ALL_TIME) {
            println("FilterDebug: Too few results (${filtered.size}), expanding time range")
            val expandedCutoff = when (timeRange) {
                RelativeTimeRange.TODAY -> currentTime - (48 * 3600) // 2 days
                RelativeTimeRange.LAST_WEEK -> currentTime - (14 * 24 * 3600) // 2 weeks
                RelativeTimeRange.LAST_MONTH -> currentTime - (60 * 24 * 3600) // 2 months
                RelativeTimeRange.LAST_YEAR -> currentTime - (2 * 365 * 24 * 3600) // 2 years
                else -> cutoffTime
            }
            stories.filter { story ->
                val storyTime = story.time ?: 0L
                storyTime >= expandedCutoff
            }.take(20) // Limit to reasonable number
        } else {
            filtered
        }
    }
}

data class StoriesUiState(
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class StoryType {
    TOP, NEW, BEST
}