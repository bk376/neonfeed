package com.hackernews.futuristic.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.components.GlowingCard
import com.hackernews.futuristic.ui.components.HologramText
import com.hackernews.futuristic.ui.components.ScaleInAnimation
import com.hackernews.futuristic.ui.stories.LoadingIndicator
import com.hackernews.futuristic.ui.stories.StoryCard
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onStoryClick: (Story) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
    
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
        SearchHeader(
            searchQuery = searchQuery,
            onQueryChange = { query ->
                searchQuery = query
                viewModel.search(query)
            },
            onClearQuery = {
                searchQuery = ""
                viewModel.clearSearch()
            }
        )
        
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }
            uiState.error != null -> {
                ErrorMessage(
                    message = uiState.error ?: "Unknown error",
                    onRetry = { viewModel.search(searchQuery) }
                )
            }
            searchQuery.isBlank() -> {
                EmptySearchState()
            }
            uiState.searchResults.isEmpty() -> {
                NoResultsState(query = searchQuery)
            }
            else -> {
                SearchResults(
                    results = uiState.searchResults,
                    onStoryClick = onStoryClick
                )
            }
        }
    }
}

@Composable
fun SearchHeader(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit
) {
    GlowingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        glowColor = NeonPurple
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                tint = NeonPurple,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onQueryChange,
                placeholder = {
                    Text(
                        text = "Search stories...",
                        color = DimText
                    )
                },
                modifier = Modifier.weight(1f),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = LightText,
                    unfocusedTextColor = LightText,
                    focusedBorderColor = NeonPurple,
                    unfocusedBorderColor = DarkBorder,
                    cursorColor = NeonPurple
                ),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = onClearQuery) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = MediumText
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SearchResults(
    results: List<Story>,
    onStoryClick: (Story) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            HologramText(
                text = "${results.size} results found",
                style = MaterialTheme.typography.titleMedium,
                color = NeonGreen
            )
        }
        
        items(items = results) { story ->
            ScaleInAnimation {
                StoryCard(
                    story = story,
                    onClick = { onStoryClick(story) }
                )
            }
        }
    }
}

@Composable
fun EmptySearchState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = DimText,
            modifier = Modifier.size(80.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        HologramText(
            text = "Search HackerNews",
            style = MaterialTheme.typography.headlineMedium,
            color = MediumText
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Enter a search term to find stories",
            style = MaterialTheme.typography.bodyMedium,
            color = DimText
        )
    }
}

@Composable
fun NoResultsState(query: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HologramText(
            text = "No results found",
            style = MaterialTheme.typography.headlineMedium,
            color = ErrorRed
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "No stories found for \"$query\"",
            style = MaterialTheme.typography.bodyMedium,
            color = MediumText
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Try searching for different keywords",
            style = MaterialTheme.typography.bodySmall,
            color = DimText
        )
    }
}

@Composable
fun ErrorMessage(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = ErrorRed
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonBlue
            )
        ) {
            Text(
                text = "Retry",
                color = androidx.compose.ui.graphics.Color.Black
            )
        }
    }
}