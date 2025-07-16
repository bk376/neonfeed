package com.hackernews.futuristic.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.components.CommentThreadSelector
import com.hackernews.futuristic.ui.components.CommentViewMode
import com.hackernews.futuristic.ui.components.GlowingCard
import com.hackernews.futuristic.ui.components.MatrixCommentThread
import com.hackernews.futuristic.ui.components.PulsingLoadingIndicator
import com.hackernews.futuristic.ui.components.ScaleInAnimation
import com.hackernews.futuristic.ui.stories.LoadingIndicator
import com.hackernews.futuristic.ui.stories.ScoreChip
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryDetailScreen(
    storyId: Long,
    onBackClick: () -> Unit,
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current
    
    LaunchedEffect(storyId) {
        viewModel.loadStory(storyId)
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
        TopAppBar(
            title = { Text("Story Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = LightText
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkSurface,
                titleContentColor = LightText
            )
        )
        
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }
            uiState.error != null -> {
                ErrorMessage(
                    message = uiState.error ?: "Unknown error",
                    onRetry = viewModel::retry,
                    modifier = Modifier.fillMaxSize()
                )
            }
            uiState.story != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        val story = uiState.story
                        if (story != null) {
                            StoryHeader(
                                story = story,
                                onUrlClick = { url ->
                                    uriHandler.openUri(url)
                                }
                            )
                        }
                    }
                    
                    uiState.story?.text?.let { text ->
                        if (text.isNotBlank()) {
                            item {
                                StoryContent(text = text)
                            }
                        }
                    }
                    
                    item {
                        CommentsHeader(
                            commentCount = uiState.story?.descendants ?: 0,
                            isLoading = uiState.isLoadingComments
                        )
                    }
                    
                    // Comment view mode selector (temporarily disabled)
                    // item {
                    //     CommentThreadSelector(
                    //         viewMode = commentViewMode,
                    //         onViewModeChange = viewModel::setCommentViewMode
                    //     )
                    // }
                    
                    // Comments section
                    if (uiState.comments.isNotEmpty()) {
                        item {
                            Text(
                                text = "Comments (${uiState.comments.size})",
                                color = NeonBlue,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        
                        // Show individual comments using LazyColumn items
                        items(
                            items = uiState.comments,
                            key = { comment -> comment.id }
                        ) { comment ->
                            SafeCommentCard(comment = comment)
                        }
                    } else if (!uiState.isLoadingComments) {
                        item {
                            Text(
                                text = "No comments available",
                                color = TextSecondary,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoryHeader(
    story: Story,
    onUrlClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            story.title?.let { title ->
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = LightText,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ScoreChip(score = story.score)
                    story.by?.let { author ->
                        Text(
                            text = "by $author",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MediumText
                        )
                    }
                }
                
                story.url?.let { url ->
                    IconButton(
                        onClick = { onUrlClick(url) }
                    ) {
                        Icon(
                            Icons.Default.OpenInNew,
                            contentDescription = "Open URL",
                            tint = NeonBlue
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StoryContent(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Content",
                style = MaterialTheme.typography.titleMedium,
                color = NeonGreen,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            val displayText = try {
                val cleaned = text.take(2000) // Limit text length
                HtmlCompat.fromHtml(cleaned, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            } catch (e: Exception) {
                text.take(1000)
            }
            
            Text(
                text = displayText,
                style = MaterialTheme.typography.bodyMedium,
                color = LightText,
                maxLines = 20,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CommentsHeader(
    commentCount: Int,
    isLoading: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Comments ($commentCount)",
            style = MaterialTheme.typography.headlineSmall,
            color = LightText,
            fontWeight = FontWeight.Bold
        )
        
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = NeonBlue,
                strokeWidth = 2.dp
            )
        }
    }
}

@Composable
fun SafeCommentCard(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                comment.by?.let { author ->
                    Text(
                        text = author,
                        style = MaterialTheme.typography.labelMedium,
                        color = NeonPurple,
                        fontWeight = FontWeight.Bold
                    )
                } ?: Text(
                    text = "Anonymous",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )
                
                Text(
                    text = formatTimeAgo(comment.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = DimText
                )
            }
            
            comment.text?.let { text ->
                if (text.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    val displayText = text
                        .replace("<p>", "\n")
                        .replace("</p>", "")
                        .replace("<br>", "\n")
                        .replace("&gt;", ">")
                        .replace("&lt;", "<")
                        .replace("&amp;", "&")
                        .replace("&#x27;", "'")
                        .replace("&quot;", "\"")
                        .take(800)
                    
                    Text(
                        text = displayText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LightText,
                        maxLines = 8,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                comment.by?.let { author ->
                    Text(
                        text = author,
                        style = MaterialTheme.typography.labelMedium,
                        color = NeonPurple,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = formatTimeAgo(comment.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = DimText
                )
            }
            
            comment.text?.let { text ->
                if (text.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    val displayText = try {
                        HtmlCompat.fromHtml(text.take(1000), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                    } catch (e: Exception) {
                        text.take(500)
                    }
                    
                    Text(
                        text = displayText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LightText,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
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
                color = Color.Black
            )
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
        else -> "${diff / 86400}d ago"
    }
}