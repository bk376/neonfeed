package com.hackernews.futuristic.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrashProofSearchScreen(
    onBackClick: () -> Unit,
    onStoryClick: (Story) -> Unit,
    onAuthorClick: ((String) -> Unit)? = null,
    viewModel: CrashProofSearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val bookmarkedStories by viewModel.bookmarkedStories.collectAsStateWithLifecycle()
    
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
        // Simple header
        TopAppBar(
            title = {
                Text(
                    text = "SEARCH",
                    color = NeonBlue,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = NeonBlue
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkSurface
            )
        )
        
        // Simple search bar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            shape = RoundedCornerShape(8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query ->
                    viewModel.updateSearchQuery(query)
                },
                placeholder = {
                    Text(
                        text = "Search stories...",
                        color = TextSecondary,
                        fontFamily = MonospaceFont
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = NeonBlue
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = { viewModel.clearSearch() }
                        ) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = TextSecondary
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonBlue,
                    unfocusedBorderColor = DarkBorder,
                    cursorColor = NeonBlue,
                    focusedTextColor = LightText,
                    unfocusedTextColor = LightText
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                singleLine = true,
                maxLines = 1
            )
        }
        
        // Content area
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                isSearching -> {
                    // Loading state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularProgressIndicator(
                                color = NeonBlue,
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = "Searching...",
                                color = NeonBlue,
                                fontFamily = MonospaceFont,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                
                error != null -> {
                    // Error state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Search Error",
                                color = NeonRed,
                                fontSize = 16.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = error ?: "Unknown error",
                                color = TextSecondary,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont
                            )
                            Button(
                                onClick = { viewModel.retry() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NeonBlue,
                                    contentColor = Color.Black
                                )
                            ) {
                                Text("Retry", fontFamily = MonospaceFont)
                            }
                        }
                    }
                }
                
                searchQuery.isBlank() -> {
                    // Empty state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = NeonBlue.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = "Enter search terms",
                                color = TextSecondary,
                                fontFamily = MonospaceFont,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                
                searchResults.isEmpty() -> {
                    // No results state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "No Results",
                                color = NeonYellow,
                                fontSize = 16.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "No stories found for \"$searchQuery\"",
                                color = TextSecondary,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont
                            )
                        }
                    }
                }
                
                else -> {
                    // Results state
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Text(
                                text = "${searchResults.size} results",
                                color = NeonGreen,
                                fontSize = 10.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        items(searchResults) { story ->
                            SimpleSearchResultCard(
                                story = story,
                                onClick = { onStoryClick(story) },
                                onAuthorClick = { author ->
                                    onAuthorClick?.invoke(author)
                                },
                                isBookmarked = viewModel.isBookmarked(story.id),
                                onBookmarkToggle = { viewModel.toggleBookmark(story) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SimpleSearchResultCard(
    story: Story,
    onClick: () -> Unit,
    onAuthorClick: ((String) -> Unit)? = null,
    isBookmarked: Boolean = false,
    onBookmarkToggle: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Title
            story.title?.let { title ->
                Text(
                    text = title,
                    color = NeonBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Spacer(modifier = Modifier.height(6.dp))
            
            // Info row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Score
                Text(
                    text = "▲ ${story.score ?: 0}",
                    color = NeonGreen,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                // Comments
                Text(
                    text = "💬 ${story.descendants ?: 0}",
                    color = NeonPurple,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                // Date
                Text(
                    text = formatTimeAgo(story.time),
                    color = NeonYellow,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Author
                story.by?.let { author ->
                    Text(
                        text = author,
                        color = if (onAuthorClick != null) NeonCyan else TextSecondary,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = if (onAuthorClick != null) {
                            Modifier.clickable { onAuthorClick(author) }
                        } else {
                            Modifier
                        }
                    )
                }
                
                // Bookmark button
                if (onBookmarkToggle != null) {
                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                            tint = if (isBookmarked) NeonGold else NeonYellow.copy(alpha = 0.7f),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis() / 1000
    val diff = now - timestamp
    
    return when {
        diff < 60 -> "now"
        diff < 3600 -> "${diff / 60}m ago"
        diff < 86400 -> "${diff / 3600}h ago"
        diff < 604800 -> "${diff / 86400}d ago"
        else -> "${diff / 604800}w ago"
    }
}