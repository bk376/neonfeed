package com.hackernews.futuristic.ui.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.analyzer.NeuralNetworkAnalyzer
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.model.StoryAnalysis
import com.hackernews.futuristic.data.profile.UserProfileManager
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: HackerNewsRepository,
    private val neuralNetworkAnalyzer: NeuralNetworkAnalyzer,
    private val userProfileManager: UserProfileManager
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()
    
    private val _selectedStoryType = MutableStateFlow(StoryType.TOP)
    val selectedStoryType: StateFlow<StoryType> = _selectedStoryType.asStateFlow()
    
    private val _analysisResults = MutableStateFlow<Map<Long, StoryAnalysis>>(emptyMap())
    val analysisResults: StateFlow<Map<Long, StoryAnalysis>> = _analysisResults.asStateFlow()
    
    private val _isNeuralAnalysisEnabled = MutableStateFlow(false)
    val isNeuralAnalysisEnabled: StateFlow<Boolean> = _isNeuralAnalysisEnabled.asStateFlow()
    
    private val _isHolographicModeEnabled = MutableStateFlow(false)
    val isHolographicModeEnabled: StateFlow<Boolean> = _isHolographicModeEnabled.asStateFlow()
    
    private var currentPage = 0
    private var allStoryIds: List<Long> = emptyList()
    private val pageSize = 20
    
    val userProfile = userProfileManager.userProfile
    val achievements = userProfileManager.achievements
    
    init {
        loadStories()
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
                    val idsResult = when (_selectedStoryType.value) {
                        StoryType.TOP -> repository.getTopStoryIds()
                        StoryType.NEW -> repository.getNewStoryIds()
                        StoryType.BEST -> repository.getBestStoryIds()
                    }
                    
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
                            val allStories = currentStories + newStories
                            
                            _uiState.value = _uiState.value.copy(
                                stories = allStories,
                                isLoading = false,
                                error = null
                            )
                            
                            currentPage++
                            
                            // Start neural network analysis
                            if (_isNeuralAnalysisEnabled.value) {
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
    
    fun toggleNeuralAnalysis() {
        _isNeuralAnalysisEnabled.value = !_isNeuralAnalysisEnabled.value
        if (_isNeuralAnalysisEnabled.value && _uiState.value.stories.isNotEmpty()) {
            analyzeStories(_uiState.value.stories)
        }
    }
    
    fun toggleHolographicMode() {
        _isHolographicModeEnabled.value = !_isHolographicModeEnabled.value
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
}

data class StoriesUiState(
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class StoryType {
    TOP, NEW, BEST
}