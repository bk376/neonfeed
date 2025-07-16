package com.hackernews.futuristic.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
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
        // Header with mode toggle
        QuantumSearchHeader(
            isRegularMode = isRegularMode,
            onModeToggle = viewModel::toggleSearchMode,
            modifier = Modifier.padding(16.dp)
        )
        
        // Search Bar
        QuantumSearchBar(
            query = searchQuery,
            onQueryChange = viewModel::updateSearchQuery,
            onSearch = viewModel::executeSearch,
            searchSuggestions = if (isRegularMode) emptyList() else searchSuggestions,
            onSuggestionClick = { suggestion ->
                viewModel.updateSearchQuery(suggestion.query)
                viewModel.executeSearch()
            },
            isSearching = uiState.isSearching,
            isRegularMode = isRegularMode,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search Controls (only show in quantum mode)
        if (!isRegularMode) {
            QuantumSearchControls(
                searchMode = searchMode,
                onSearchModeChange = viewModel::setSearchMode,
                selectedDimensions = selectedDimensions,
                onDimensionToggle = viewModel::toggleDimension,
                probabilityThreshold = probabilityThreshold,
                onProbabilityChange = viewModel::setProbabilityThreshold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
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
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                if (isRegularMode) {
                    RegularSearchWelcome(
                        onQuickSearch = { query ->
                            viewModel.updateSearchQuery(query)
                            viewModel.executeSearch()
                        },
                        scrollState = scrollState,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    QuantumSearchWelcome(
                        onQuickSearch = { query ->
                            viewModel.updateSearchQuery(query)
                            viewModel.executeSearch()
                        },
                        scrollState = scrollState,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun QuantumSearchHeader(
    isRegularMode: Boolean,
    onModeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = if (isRegularMode) "REGULAR SEARCH" else "QUANTUM SEARCH ENGINE",
                    color = if (isRegularMode) NeonGreen else NeonBlue,
                    fontSize = 24.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = if (isRegularMode) "Simple keyword-based search" else "Multi-dimensional content discovery system",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Mode toggle button
            Button(
                onClick = onModeToggle,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRegularMode) NeonBlue else NeonGreen,
                    contentColor = androidx.compose.ui.graphics.Color.Black
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = if (isRegularMode) "QUANTUM" else "REGULAR",
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
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
                text = "QUANTUM PROCESSING...",
                color = NeonBlue,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Analyzing dimensional probabilities",
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
private fun QuantumSearchWelcome(
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
                        text = "WELCOME TO QUANTUM SEARCH",
                        color = NeonGreen,
                        fontSize = 18.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "Experience next-generation content discovery with multi-dimensional search capabilities:",
                        color = LightText,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    QuantumFeaturesList()
                }
            }
        }
        
        item {
            QuickSearchSuggestions(onQuickSearch = onQuickSearch)
        }
        
        item {
            QuantumSearchTips()
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
private fun QuickSearchSuggestions(
    onQuickSearch: (String) -> Unit
) {
    val suggestions = listOf(
        "artificial intelligence",
        "quantum computing",
        "blockchain technology",
        "cybersecurity",
        "machine learning",
        "web development"
    )
    
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "QUICK SEARCH",
            color = NeonYellow,
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
                            contentColor = NeonYellow
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