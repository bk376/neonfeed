package com.hackernews.futuristic.ui.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
fun BookmarksScreen(
    onBackClick: () -> Unit,
    onStoryClick: (Story) -> Unit,
    onAuthorClick: ((String) -> Unit)? = null,
    viewModel: BookmarksViewModel = hiltViewModel()
) {
    val bookmarkedStories by viewModel.bookmarkedStories.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    
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
        TopAppBar(
            title = {
                Text(
                    text = "BOOKMARKS",
                    color = NeonCyan,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = NeonCyan
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkSurface,
                titleContentColor = LightText
            )
        )
        
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularProgressIndicator(
                                color = NeonCyan,
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = "Loading bookmarks...",
                                color = NeonCyan,
                                fontFamily = MonospaceFont,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                
                error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Error",
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
                                onClick = { viewModel.refreshBookmarks() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NeonCyan,
                                    contentColor = DarkBackground
                                )
                            ) {
                                Text("Retry", fontFamily = MonospaceFont)
                            }
                        }
                    }
                }
                
                bookmarkedStories.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                Icons.Default.BookmarkBorder,
                                contentDescription = "No bookmarks",
                                tint = NeonCyan.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = "No bookmarks yet",
                                color = TextSecondary,
                                fontFamily = MonospaceFont,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Bookmark stories to save them here",
                                color = TextSecondary,
                                fontFamily = MonospaceFont,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Text(
                                text = "${bookmarkedStories.size} bookmarked stories",
                                color = NeonCyan,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        items(bookmarkedStories) { story ->
                            BookmarkCard(
                                story = story,
                                onClick = { onStoryClick(story) },
                                onRemoveBookmark = { viewModel.removeBookmark(story) },
                                onAuthorClick = onAuthorClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BookmarkCard(
    story: Story,
    onClick: () -> Unit,
    onRemoveBookmark: () -> Unit,
    onAuthorClick: ((String) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Title
                    story.title?.let { title ->
                        Text(
                            text = title,
                            color = NeonCyan,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MonospaceFont,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Meta information
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Score
                        Text(
                            text = "▲ ${story.score ?: 0}",
                            color = NeonGreen,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // Comments
                        Text(
                            text = "💬 ${story.descendants ?: 0}",
                            color = NeonPurple,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // Date
                        Text(
                            text = formatTimeAgo(story.time),
                            color = NeonYellow,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont
                        )
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        // Author
                        story.by?.let { author ->
                            Text(
                                text = author,
                                color = if (onAuthorClick != null) NeonCyan else TextSecondary,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont,
                                modifier = if (onAuthorClick != null) {
                                    Modifier.clickable { onAuthorClick(author) }
                                } else {
                                    Modifier
                                }
                            )
                        }
                    }
                    
                    // URL if available
                    story.url?.let { url ->
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = try {
                                java.net.URL(url).host
                            } catch (e: Exception) {
                                "external link"
                            },
                            color = NeonCyan.copy(alpha = 0.7f),
                            fontSize = 11.sp,
                            fontFamily = MonospaceFont,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                
                // Remove bookmark button
                IconButton(
                    onClick = onRemoveBookmark,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Remove bookmark",
                        tint = NeonRed.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
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