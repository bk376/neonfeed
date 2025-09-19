package com.hackernews.futuristic.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.analyzer.QuantumSearchEngine
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuantumSearchViewModel @Inject constructor(
    private val repository: HackerNewsRepository,
    private val quantumSearchEngine: QuantumSearchEngine
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(QuantumSearchUiState())
    val uiState: StateFlow<QuantumSearchUiState> = _uiState.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _searchMode = MutableStateFlow(SearchMode.QUANTUM)
    val searchMode: StateFlow<SearchMode> = _searchMode.asStateFlow()
    
    private val _selectedDimensions = MutableStateFlow(
        listOf(
            SearchDimension.TITLE,
            SearchDimension.CONTENT,
            SearchDimension.POPULARITY
        )
    )
    val selectedDimensions: StateFlow<List<SearchDimension>> = _selectedDimensions.asStateFlow()
    
    private val _probabilityThreshold = MutableStateFlow(0.5f)
    val probabilityThreshold: StateFlow<Float> = _probabilityThreshold.asStateFlow()
    
    private val _searchSuggestions = MutableStateFlow<List<SearchSuggestion>>(emptyList())
    val searchSuggestions: StateFlow<List<SearchSuggestion>> = _searchSuggestions.asStateFlow()
    
    private val _isRegularMode = MutableStateFlow(true)
    val isRegularMode: StateFlow<Boolean> = _isRegularMode.asStateFlow()
    
    private val _bookmarkedStories = MutableStateFlow<Set<Long>>(emptySet())
    val bookmarkedStories: StateFlow<Set<Long>> = _bookmarkedStories.asStateFlow()
    
    private val _selectedTimeRange = MutableStateFlow(RelativeTimeRange.TODAY)
    val selectedTimeRange: StateFlow<RelativeTimeRange> = _selectedTimeRange.asStateFlow()
    
    private var allStories: List<Story> = emptyList()
    
    init {
        loadInitialData()
        generateInitialSuggestions()
        updateBookmarkedStories()
    }
    
    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                // Load more stories for better search results (combining multiple sources)
                val topStoriesResult = repository.getTopStories(100)
                val newStoriesResult = repository.getNewStories(100) 
                val bestStoriesResult = repository.getBestStories(100)
                
                val allStoriesList = mutableListOf<Story>()
                
                topStoriesResult.fold(
                    onSuccess = { stories -> allStoriesList.addAll(stories) },
                    onFailure = { /* Continue with other sources */ }
                )
                
                newStoriesResult.fold(
                    onSuccess = { stories -> allStoriesList.addAll(stories) },
                    onFailure = { /* Continue with other sources */ }
                )
                
                bestStoriesResult.fold(
                    onSuccess = { stories -> allStoriesList.addAll(stories) },
                    onFailure = { /* Continue with other sources */ }
                )
                
                // Remove duplicates and sort by score
                allStories = allStoriesList
                    .distinctBy { it.id }
                    .sortedWith(
                        compareByDescending<Story> { it.score ?: 0 }
                            .thenByDescending { it.time }
                    )
                
                if (allStories.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        error = "Failed to load stories for search"
                    )
                }
            } catch (e: Exception) {
                // If exception occurs, show error and use empty list
                allStories = emptyList()
                _uiState.value = _uiState.value.copy(
                    error = "Network error: ${e.message}"
                )
            }
        }
    }
    
    fun updateSearchQuery(query: String) {
        try {
            _searchQuery.value = query
            
            // Generate suggestions based on query
            if (query.isNotEmpty()) {
                generateSearchSuggestions(query)
            } else {
                _searchSuggestions.value = emptyList()
            }
        } catch (e: Exception) {
            // Handle any errors during query update
            _searchSuggestions.value = emptyList()
            _uiState.value = _uiState.value.copy(
                error = "Search input error: ${e.message}"
            )
        }
    }
    
    fun setSearchMode(mode: SearchMode) {
        _searchMode.value = mode
    }
    
    fun toggleDimension(dimension: SearchDimension) {
        val currentDimensions = _selectedDimensions.value
        _selectedDimensions.value = if (dimension in currentDimensions) {
            currentDimensions - dimension
        } else {
            currentDimensions + dimension
        }
    }
    
    fun setProbabilityThreshold(threshold: Float) {
        _probabilityThreshold.value = threshold
    }
    
    fun toggleSearchMode() {
        _isRegularMode.value = !_isRegularMode.value
        // Clear current search when switching modes
        clearSearch()
    }
    
    fun executeSearch() {
        if (_searchQuery.value.isEmpty()) return
        
        _uiState.value = _uiState.value.copy(
            isSearching = true,
            error = null
        )
        
        viewModelScope.launch {
            try {
                val searchResult = if (_isRegularMode.value) {
                    executeRegularSearch(_searchQuery.value)
                } else {
                    executeQuantumSearch()
                }
                
                _uiState.value = _uiState.value.copy(
                    isSearching = false,
                    searchResult = searchResult,
                    error = null
                )
                
                // Update suggestions based on search result (only for quantum mode)
                if (!_isRegularMode.value) {
                    _searchSuggestions.value = searchResult.suggestions
                }
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSearching = false,
                    error = e.message ?: "Search failed"
                )
            }
        }
    }
    
    private suspend fun executeQuantumSearch(): QuantumSearchResult {
        return try {
            val quantumQuery = QuantumSearchQuery(
                query = _searchQuery.value,
                searchDimensions = _selectedDimensions.value,
                timeRange = TimeRange(null, null, _selectedTimeRange.value),
                quantumFilters = emptyList(),
                searchMode = _searchMode.value,
                probabilityThreshold = _probabilityThreshold.value
            )
            
            quantumSearchEngine.search(quantumQuery, allStories)
        } catch (e: Exception) {
            // Fallback to regular search if quantum search fails
            executeRegularSearch(_searchQuery.value)
        }
    }
    
    private fun executeRegularSearch(query: String): QuantumSearchResult {
        val queryLower = query.lowercase()
        
        // Apply time filtering first
        val timeFilteredStories = filterStoriesByTimeRange(allStories, _selectedTimeRange.value)
        
        // Simple keyword-based search
        val filteredStories = timeFilteredStories.filter { story ->
            story.title?.lowercase()?.contains(queryLower) == true ||
            story.text?.lowercase()?.contains(queryLower) == true ||
            story.by?.lowercase()?.contains(queryLower) == true
        }
        
        // Sort by score and time
        val sortedStories = filteredStories.sortedWith(
            compareByDescending<Story> { it.score ?: 0 }
                .thenByDescending { it.time }
        )
        
        return QuantumSearchResult(
            stories = sortedStories,
            searchMetrics = SearchMetrics(
                totalResults = sortedStories.size,
                searchAccuracy = 0.8f,
                quantumCoherence = 0.5f,
                dimensionalCoverage = 0.6f,
                temporalRelevance = 0.7f,
                semanticDepth = 0.4f,
                uncertaintyLevel = 0.2f,
                searchComplexity = 1
            ),
            suggestions = emptyList(),
            searchPaths = emptyList(),
            quantumState = QuantumState(
                superpositionLevels = mapOf(),
                entanglementMatrix = arrayOf(),
                coherenceTime = System.currentTimeMillis(),
                observationCount = 1,
                quantumInterference = 0.1f
            ),
            executionTime = System.currentTimeMillis()
        )
    }
    
    private fun calculateSimpleRelevance(story: Story, query: String): Float {
        var score = 0f
        
        // Title match gets higher score
        story.title?.lowercase()?.let { title ->
            if (title.contains(query)) {
                score += if (title.startsWith(query)) 1.0f else 0.8f
            }
        }
        
        // Content match gets moderate score
        story.text?.lowercase()?.let { text ->
            if (text.contains(query)) {
                score += 0.5f
            }
        }
        
        // Author match gets lower score
        story.by?.lowercase()?.let { author ->
            if (author.contains(query)) {
                score += 0.3f
            }
        }
        
        return score.coerceAtMost(1.0f)
    }
    
    private fun generateSimpleRelatedQueries(query: String): List<String> {
        val relatedTerms = mapOf(
            "javascript" to listOf("js", "node", "react", "vue"),
            "python" to listOf("django", "flask", "pandas", "numpy"),
            "programming" to listOf("coding", "development", "software"),
            "ai" to listOf("machine learning", "neural networks", "deep learning"),
            "startup" to listOf("entrepreneur", "business", "funding")
        )
        
        val queryLower = query.lowercase()
        return relatedTerms.entries
            .filter { (key, _) -> queryLower.contains(key) }
            .flatMap { (_, values) -> values }
            .take(3)
    }
    
    private fun generateInitialSuggestions() {
        val initialSuggestions = listOf(
            SearchSuggestion(
                query = "machine learning",
                confidence = 0.9f,
                suggestionType = SuggestionType.TREND_BASED,
                reasoning = "Popular trending topic",
                estimatedResults = 45,
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.CONTENT, SearchDimension.INNOVATION)
            ),
            SearchSuggestion(
                query = "quantum computing",
                confidence = 0.8f,
                suggestionType = SuggestionType.TREND_BASED,
                reasoning = "Emerging technology trend",
                estimatedResults = 32,
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.TECHNICAL_DEPTH, SearchDimension.INNOVATION)
            ),
            SearchSuggestion(
                query = "cybersecurity",
                confidence = 0.85f,
                suggestionType = SuggestionType.TREND_BASED,
                reasoning = "High demand topic",
                estimatedResults = 67,
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.CONTENT, SearchDimension.CONTROVERSY)
            ),
            SearchSuggestion(
                query = "blockchain",
                confidence = 0.7f,
                suggestionType = SuggestionType.TREND_BASED,
                reasoning = "Cryptocurrency and web3 focus",
                estimatedResults = 38,
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.INNOVATION, SearchDimension.CONTROVERSY)
            ),
            SearchSuggestion(
                query = "web development",
                confidence = 0.9f,
                suggestionType = SuggestionType.TREND_BASED,
                reasoning = "Consistent high interest",
                estimatedResults = 89,
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.CONTENT, SearchDimension.TECHNICAL_DEPTH)
            )
        )
        
        _searchSuggestions.value = initialSuggestions
    }
    
    private fun generateSearchSuggestions(query: String) {
        viewModelScope.launch {
            try {
                val suggestions = mutableListOf<SearchSuggestion>()
                
                // Auto-complete suggestions
                if (query.length >= 2) {
                    val autoComplete = generateAutoCompleteSuggestions(query)
                    suggestions.addAll(autoComplete)
                }
                
                // Semantic expansion suggestions
                val semanticExpansions = generateSemanticExpansions(query)
                suggestions.addAll(semanticExpansions)
                
                // Cross-dimensional suggestions
                val crossDimensional = generateCrossDimensionalSuggestions(query)
                suggestions.addAll(crossDimensional)
                
                _searchSuggestions.value = suggestions.take(8)
                
            } catch (e: Exception) {
                // Handle suggestion generation error silently
                _searchSuggestions.value = emptyList()
                _uiState.value = _uiState.value.copy(
                    error = "Suggestion generation error: ${e.message}"
                )
            }
        }
    }
    
    private fun generateAutoCompleteSuggestions(query: String): List<SearchSuggestion> {
        val commonTerms = mapOf(
            "ai" to listOf("artificial intelligence", "ai models", "ai safety", "ai ethics"),
            "mac" to listOf("machine learning", "macos", "macbook"),
            "web" to listOf("web development", "web3", "web frameworks", "web security"),
            "cryp" to listOf("cryptocurrency", "cryptography", "crypto trading"),
            "bloc" to listOf("blockchain", "block storage", "blockchain development"),
            "quan" to listOf("quantum computing", "quantum physics", "quantum algorithms"),
            "sec" to listOf("security", "cybersecurity", "secure coding"),
            "mob" to listOf("mobile development", "mobile apps", "mobile security"),
            "clou" to listOf("cloud computing", "cloud storage", "cloud security"),
            "dev" to listOf("development", "developer tools", "devops")
        )
        
        val queryLower = query.lowercase()
        val matchingTerms = commonTerms.entries
            .filter { (key, _) -> key.startsWith(queryLower) }
            .flatMap { (_, values) -> values }
            .filter { it.startsWith(queryLower) }
        
        return matchingTerms.map { term ->
            SearchSuggestion(
                query = term,
                confidence = 0.8f,
                suggestionType = SuggestionType.AUTO_COMPLETE,
                reasoning = "Auto-complete suggestion",
                estimatedResults = (20..80).random(),
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.CONTENT)
            )
        }
    }
    
    private fun generateSemanticExpansions(query: String): List<SearchSuggestion> {
        val semanticMap = mapOf(
            "ai" to listOf("neural networks", "deep learning", "computer vision"),
            "crypto" to listOf("bitcoin", "ethereum", "defi", "nft"),
            "web" to listOf("react", "javascript", "html", "css", "node.js"),
            "mobile" to listOf("android", "ios", "flutter", "react native"),
            "security" to listOf("hacking", "vulnerability", "encryption", "privacy"),
            "database" to listOf("sql", "nosql", "mongodb", "postgresql"),
            "cloud" to listOf("aws", "azure", "google cloud", "kubernetes"),
            "programming" to listOf("python", "java", "javascript", "rust", "go")
        )
        
        val queryLower = query.lowercase()
        val expansions = semanticMap.entries
            .filter { (key, _) -> queryLower.contains(key) }
            .flatMap { (_, values) -> values }
            .take(3)
        
        return expansions.map { expansion ->
            SearchSuggestion(
                query = expansion,
                confidence = 0.7f,
                suggestionType = SuggestionType.SEMANTIC_EXPANSION,
                reasoning = "Semantically related to your search",
                estimatedResults = (15..60).random(),
                dimensions = listOf(SearchDimension.TITLE, SearchDimension.CONTENT, SearchDimension.TECHNICAL_DEPTH)
            )
        }
    }
    
    private fun generateCrossDimensionalSuggestions(query: String): List<SearchSuggestion> {
        val unusedDimensions = SearchDimension.values().filter { it !in _selectedDimensions.value }
        
        return unusedDimensions.take(2).map { dimension ->
            SearchSuggestion(
                query = "$query in ${dimension.displayName.lowercase()}",
                confidence = 0.6f,
                suggestionType = SuggestionType.CROSS_DIMENSIONAL,
                reasoning = "Explore in ${dimension.displayName} dimension",
                estimatedResults = (10..40).random(),
                dimensions = _selectedDimensions.value + dimension
            )
        }
    }
    
    fun clearSearch() {
        _searchQuery.value = ""
        _uiState.value = _uiState.value.copy(
            searchResult = null,
            error = null
        )
        generateInitialSuggestions()
    }
    
    fun retrySearch() {
        executeSearch()
    }
    
    fun onSuggestionClick(suggestion: SearchSuggestion) {
        // Update search query with the suggestion
        _searchQuery.value = suggestion.query
        
        // Execute search with the suggested query
        executeSearch()
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
                updateBookmarkedStories()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Bookmark error: ${e.message}"
                )
            }
        }
    }
    
    fun isBookmarked(storyId: Long): Boolean {
        return storyId in _bookmarkedStories.value
    }
    
    private fun updateBookmarkedStories() {
        viewModelScope.launch {
            try {
                repository.getBookmarkedStories().collect { bookmarks ->
                    _bookmarkedStories.value = bookmarks.map { it.id }.toSet()
                }
            } catch (e: Exception) {
                // Handle error silently for bookmarks
            }
        }
    }
    
    fun setTimeRange(timeRange: RelativeTimeRange) {
        _selectedTimeRange.value = timeRange
        // Re-execute search if there's an active query
        if (_searchQuery.value.isNotEmpty() && _uiState.value.searchResult != null) {
            executeSearch()
        }
    }
    
    private fun filterStoriesByTimeRange(stories: List<Story>, timeRange: RelativeTimeRange): List<Story> {
        if (timeRange == RelativeTimeRange.ALL_TIME) {
            return stories
        }
        
        val currentTime = System.currentTimeMillis() / 1000 // HackerNews uses Unix timestamp in seconds
        val cutoffTime = currentTime - (timeRange.hours * 3600)
        
        return stories.filter { story ->
            val storyTime = story.time ?: 0L
            storyTime >= cutoffTime
        }
    }
}

data class QuantumSearchUiState(
    val isSearching: Boolean = false,
    val searchResult: QuantumSearchResult? = null,
    val error: String? = null
)