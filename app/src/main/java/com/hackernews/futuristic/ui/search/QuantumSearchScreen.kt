package com.hackernews.futuristic.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.components.*
import com.hackernews.futuristic.ui.theme.*

@Composable
fun QuantumSearchScreen(
    onStoryClick: (Story) -> Unit,
    onScrollToTopCallback: ((suspend () -> Unit) -> Unit)? = null,
    viewModel: QuantumSearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchMode by viewModel.searchMode.collectAsStateWithLifecycle()
    val selectedDimensions by viewModel.selectedDimensions.collectAsStateWithLifecycle()
    val probabilityThreshold by viewModel.probabilityThreshold.collectAsStateWithLifecycle()
    val searchSuggestions by viewModel.searchSuggestions.collectAsStateWithLifecycle()
    val isRegularMode by viewModel.isRegularMode.collectAsStateWithLifecycle()
    val bookmarkedStories by viewModel.bookmarkedStories.collectAsStateWithLifecycle()
    val selectedTimeRange by viewModel.selectedTimeRange.collectAsStateWithLifecycle()
    
    val scrollState = rememberScrollState()
    
    // Register scroll-to-top callback
    LaunchedEffect(onScrollToTopCallback) {
        onScrollToTopCallback?.invoke {
            scrollState.animateScrollTo(0)
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBackground,
                        DarkSurface
                    )
                )
            )
    ) {
        // Simplified Header
        SimpleSearchHeader(
            isRegularMode = isRegularMode,
            onModeToggle = viewModel::toggleSearchMode,
            modifier = Modifier.padding(16.dp)
        )
        
        // Search Bar
        QuantumSearchBar(
            query = searchQuery,
            onQueryChange = viewModel::updateSearchQuery,
            onSearch = viewModel::executeSearch,
            searchSuggestions = searchSuggestions,
            onSuggestionClick = viewModel::onSuggestionClick,
            isSearching = uiState.isSearching,
            isRegularMode = isRegularMode,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        // Time Range Filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TIME FILTER:",
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            TimeRangeDropdown(
                selectedTimeRange = selectedTimeRange,
                onTimeRangeChange = viewModel::setTimeRange,
                modifier = Modifier.wrapContentWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Search Results
        when {
            uiState.isSearching -> {
                QuantumSearchLoadingIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }
            uiState.error != null -> {
                QuantumSearchError(
                    message = uiState.error ?: "Unknown error",
                    onRetry = viewModel::executeSearch,
                    modifier = Modifier.fillMaxSize()
                )
            }
            uiState.searchResult != null -> {
                QuantumSearchResults(
                    searchResult = uiState.searchResult!!,
                    onStoryClick = onStoryClick,
                    isBookmarked = { storyId -> viewModel.isBookmarked(storyId) },
                    onBookmarkClick = { story -> viewModel.toggleBookmark(story) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                SimpleSearchWelcome(
                    isRegularMode = isRegularMode,
                    onQuickSearch = { query ->
                        viewModel.updateSearchQuery(query)
                        viewModel.executeSearch()
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun SimpleSearchHeader(
    isRegularMode: Boolean,
    onModeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "SEARCH",
            color = NeonBlue,
            fontSize = 24.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        // Small mode toggle
        TextButton(
            onClick = onModeToggle,
            colors = ButtonDefaults.textButtonColors(
                contentColor = if (isRegularMode) NeonGreen else NeonPurple
            )
        ) {
            Text(
                text = if (isRegularMode) "⚡ REGULAR" else "🔮 QUANTUM",
                fontFamily = MonospaceFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun QuantumSearchLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PulsingLoadingIndicator(
                color = NeonBlue
            )
            
            Text(
                text = "SEARCHING...",
                color = NeonBlue,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Processing search query...",
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun QuantumSearchError(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "QUANTUM SEARCH ERROR",
                color = NeonRed,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = message,
                color = TextSecondary,
                fontSize = 12.sp,
                fontFamily = MonospaceFont
            )
            
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonBlue,
                    contentColor = androidx.compose.ui.graphics.Color.Black
                )
            ) {
                Text(
                    text = "RETRY SEARCH",
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SimpleSearchWelcome(
    isRegularMode: Boolean,
    onQuickSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = if (isRegularMode) "SEARCH STORIES" else "QUANTUM SEARCH",
                        color = if (isRegularMode) NeonGreen else NeonBlue,
                        fontSize = 18.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = if (isRegularMode) 
                            "Search Hacker News stories by keyword, author, or content." 
                        else 
                            "Advanced AI-powered search with multi-dimensional analysis.",
                        color = LightText,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }
        }
        
        item {
            SimpleQuickSearchSuggestions(
                isRegularMode = isRegularMode,
                onQuickSearch = onQuickSearch
            )
        }
    }
}

@Composable
private fun QuantumFeaturesList() {
    val features = listOf(
        "🔮 Quantum probability-based relevance",
        "🌐 Multi-dimensional content analysis",
        "🧠 Neural semantic understanding",
        "⚡ Predictive search suggestions",
        "🔗 Cross-dimensional link discovery",
        "📊 Real-time search metrics"
    )
    
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        features.forEach { feature ->
            Text(
                text = feature,
                color = TextSecondary,
                fontSize = 12.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun SimpleQuickSearchSuggestions(
    isRegularMode: Boolean,
    onQuickSearch: (String) -> Unit
) {
    val suggestions = if (isRegularMode) {
        listOf("AI", "JavaScript", "Python", "Startup", "Security", "Crypto")
    } else {
        listOf("AI", "Quantum", "Blockchain", "Security", "Web3", "ML")
    }
    
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "POPULAR SEARCHES",
            color = if (isRegularMode) NeonGreen else NeonBlue,
            fontSize = 14.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        // Simple grid of suggestion chips
        suggestions.chunked(3).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { suggestion ->
                    AssistChip(
                        onClick = { onQuickSearch(suggestion) },
                        label = {
                            Text(
                                text = suggestion,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = DarkCard,
                            labelColor = if (isRegularMode) NeonGreen else NeonBlue
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun QuantumSearchTips() {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "SEARCH TIPS",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            val tips = listOf(
                "Use Quantum mode for probability-based results",
                "Select multiple dimensions for comprehensive search",
                "Adjust threshold for precision vs. recall",
                "Try Neural mode for semantic understanding"
            )
            
            tips.forEach { tip ->
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "•",
                        color = NeonPurple,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                    Text(
                        text = tip,
                        color = TextSecondary,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }
        }
    }
}

@Composable
private fun RegularSearchWelcome(
    onQuickSearch: (String) -> Unit,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    
    // Sync lazy list with scroll state
    LaunchedEffect(scrollState.value) {
        if (scrollState.value == 0) {
            lazyListState.animateScrollToItem(0)
        }
    }
    
    LazyColumn(
        state = lazyListState,
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "REGULAR SEARCH MODE",
                        color = NeonGreen,
                        fontSize = 18.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "Simple and fast keyword-based search for Hacker News stories:",
                        color = LightText,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    RegularSearchFeaturesList()
                }
            }
        }
        
        item {
            RegularQuickSearchSuggestions(onQuickSearch = onQuickSearch)
        }
        
        item {
            RegularSearchTips()
        }
    }
}

@Composable
private fun RegularSearchFeaturesList() {
    val features = listOf(
        "🔍 Fast keyword matching",
        "📝 Title and content search", 
        "⚡ Instant results",
        "🎯 Simple and intuitive",
        "📊 Clean result display",
        "🔄 Real-time search"
    )
    
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        features.forEach { feature ->
            Text(
                text = feature,
                color = TextSecondary,
                fontSize = 12.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun RegularQuickSearchSuggestions(
    onQuickSearch: (String) -> Unit
) {
    val suggestions = listOf(
        "programming",
        "javascript",
        "python",
        "react",
        "startup",
        "AI"
    )
    
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "POPULAR SEARCHES",
            color = NeonGreen,
            fontSize = 14.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        suggestions.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { suggestion ->
                    Button(
                        onClick = { onQuickSearch(suggestion) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkCard,
                            contentColor = NeonGreen
                        ),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = suggestion,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RegularSearchTips() {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "SEARCH TIPS",
                color = NeonGreen,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            val tips = listOf(
                "Search by keywords in title or content",
                "Use specific terms for better results",
                "Try popular programming languages",
                "Search for technology trends"
            )
            
            tips.forEach { tip ->
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "•",
                        color = NeonGreen,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                    Text(
                        text = tip,
                        color = TextSecondary,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }
        }
    }
}