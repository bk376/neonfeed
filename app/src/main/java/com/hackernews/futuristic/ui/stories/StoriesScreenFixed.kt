package com.hackernews.futuristic.ui.stories

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.snapshotFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.components.UserProfileCard
import com.hackernews.futuristic.ui.components.HolographicStoryPreview
import com.hackernews.futuristic.ui.components.TimeRangeDropdown
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoriesScreenFixed(
    onStoryClick: (Story) -> Unit,
    onUrlClick: ((String, String) -> Unit)? = null,
    onAuthorClick: ((String) -> Unit)? = null,
    onScrollToTopCallback: ((suspend () -> Unit) -> Unit)? = null,
    viewModel: StoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    // val selectedStoryType by viewModel.selectedStoryType.collectAsStateWithLifecycle() // Removed - using only TOP stories now
    val isNeuralAnalysisEnabled by viewModel.isNeuralAnalysisEnabled.collectAsStateWithLifecycle()
    val isHolographicModeEnabled by viewModel.isHolographicModeEnabled.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val analysisResults by viewModel.analysisResults.collectAsStateWithLifecycle()
    val selectedTimeRange by viewModel.selectedTimeRange.collectAsStateWithLifecycle()
    
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
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkSurface
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "NEONFEED",
                    color = NeonBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Filter Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "FILTER BY TIME:",
                        color = NeonGreen,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    TimeRangeDropdown(
                        selectedTimeRange = selectedTimeRange,
                        onTimeRangeChange = viewModel::setTimeRange,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
                
            }
        }
        
        // Content
        when {
            uiState.isLoading && uiState.stories.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = NeonBlue,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            uiState.error != null && uiState.stories.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = DarkSurface
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "CONNECTION ERROR",
                                color = NeonRed,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = MonospaceFont
                            )
                            Text(
                                text = uiState.error ?: "Unknown error",
                                color = TextSecondary,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont
                            )
                            Button(
                                onClick = { viewModel.retry() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NeonBlue
                                )
                            ) {
                                Text("RETRY", color = Color.Black, fontFamily = MonospaceFont)
                            }
                        }
                    }
                }
            }
            else -> {
                val listState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                val showScrollToTop by remember {
                    derivedStateOf {
                        listState.firstVisibleItemIndex > 2
                    }
                }
                
                // Register scroll-to-top callback
                LaunchedEffect(onScrollToTopCallback) {
                    onScrollToTopCallback?.invoke {
                        listState.animateScrollToItem(0)
                    }
                }
                
                // Infinite scroll detection
                LaunchedEffect(listState) {
                    snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                        .collect { visibleItems ->
                            if (visibleItems.isNotEmpty()) {
                                val lastVisibleIndex = visibleItems.last().index
                                val totalItems = listState.layoutInfo.totalItemsCount
                                
                                // Load more when we're 5 items from the end
                                if (lastVisibleIndex >= totalItems - 5 && !uiState.isLoading) {
                                    viewModel.loadMoreStories()
                                }
                            }
                        }
                }
                
                // Pull to refresh state
                val pullToRefreshState = rememberPullToRefreshState()
                
                LaunchedEffect(pullToRefreshState.isRefreshing) {
                    if (pullToRefreshState.isRefreshing) {
                        viewModel.refreshStories()
                        pullToRefreshState.endRefresh()
                    }
                }
                
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(pullToRefreshState.nestedScrollConnection)
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                    // User Profile Card (only if neural analysis is enabled)
                    if (isNeuralAnalysisEnabled) {
                        item {
                            UserProfileCard(
                                profile = userProfile,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    
                    // Stories
                    items(
                        items = uiState.stories,
                        key = { story -> story.id }
                    ) { story ->
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (isHolographicModeEnabled) {
                                HolographicStoryPreview(
                                    story = story,
                                    onStoryClick = { 
                                        onStoryClick(story)
                                        viewModel.onStoryRead(story, readingTime = 180L, completed = true)
                                    },
                                    onPreviewExpand = { /* We can keep this empty for now */ }
                                )
                            } else {
                                EnhancedStoryCard(
                                    story = story,
                                    onStoryClick = { 
                                        onStoryClick(story)
                                        viewModel.onStoryRead(story, readingTime = 180L, completed = true)
                                    },
                                    onUrlClick = onUrlClick,
                                    isBookmarked = viewModel.isBookmarked(story.id),
                                    onBookmarkClick = { viewModel.toggleBookmark(story) },
                                    onAuthorClick = onAuthorClick
                                )
                            }
                            
                            // Analysis card if enabled and available
                            if (isNeuralAnalysisEnabled) {
                                analysisResults[story.id]?.let { analysis ->
                                    AnalysisCard(analysis = analysis)
                                }
                            }
                        }
                    }
                    
                    // Loading indicator at the bottom
                    if (uiState.isLoading && uiState.stories.isNotEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    color = NeonBlue,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }
                    }
                    
                    // Floating scroll-to-top button
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showScrollToTop,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        FloatingActionButton(
                            onClick = {
                                scope.launch {
                                    listState.animateScrollToItem(0)
                                }
                            },
                            containerColor = NeonBlue,
                            contentColor = Color.Black
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = "Scroll to top"
                            )
                        }
                    }
                    
                    // Pull-to-refresh indicator
                    PullToRefreshContainer(
                        state = pullToRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}

@Composable
fun CyberpunkButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.7f,
        animationSpec = tween(300)
    )
    
    val glowColor = when (text.uppercase()) {
        "TOP" -> NeonBlue
        "NEW" -> NeonGreen  
        "BEST" -> NeonGold
        else -> NeonBlue
    }
    
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) {
                    Brush.linearGradient(
                        colors = listOf(
                            glowColor.copy(alpha = 0.3f),
                            glowColor.copy(alpha = 0.1f),
                            glowColor.copy(alpha = 0.3f)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            DarkCard,
                            DarkSurface,
                            DarkCard
                        )
                    )
                }
            )
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                brush = Brush.linearGradient(
                    colors = if (isSelected) {
                        listOf(glowColor, glowColor.copy(alpha = 0.5f), glowColor)
                    } else {
                        listOf(DarkBorder, DarkBorder.copy(alpha = 0.3f), DarkBorder)
                    }
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .alpha(animatedAlpha),
        contentAlignment = Alignment.Center
    ) {
        // Glowing text effect
        Text(
            text = text.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MonospaceFont,
            color = if (isSelected) glowColor else TextSecondary,
            style = if (isSelected) {
                LocalTextStyle.current.copy(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = glowColor,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f),
                        blurRadius = 8f
                    )
                )
            } else {
                LocalTextStyle.current
            }
        )
        
        // Cyberpunk accent lines
        if (isSelected) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 1.dp.toPx()
                
                // Top accent line
                drawLine(
                    color = glowColor,
                    start = androidx.compose.ui.geometry.Offset(size.width * 0.2f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width * 0.8f, 0f),
                    strokeWidth = strokeWidth
                )
                
                // Bottom accent line  
                drawLine(
                    color = glowColor,
                    start = androidx.compose.ui.geometry.Offset(size.width * 0.2f, size.height),
                    end = androidx.compose.ui.geometry.Offset(size.width * 0.8f, size.height),
                    strokeWidth = strokeWidth
                )
            }
        }
    }
}

@Composable
fun EnhancedStoryCard(
    story: Story,
    onStoryClick: () -> Unit,
    onUrlClick: ((String, String) -> Unit)? = null,
    isBookmarked: Boolean = false,
    onBookmarkClick: (() -> Unit)? = null,
    onAuthorClick: ((String) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onStoryClick() },
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title
            story.title?.let { title ->
                Text(
                    text = title,
                    color = NeonBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Info row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Score
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "▲",
                        color = NeonGreen,
                        fontSize = 12.sp
                    )
                    Text(
                        text = story.score.toString(),
                        color = NeonGreen,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Comments
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "💬",
                        fontSize = 12.sp
                    )
                    Text(
                        text = (story.descendants ?: 0).toString(),
                        color = NeonPurple,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Date
                Text(
                    text = formatTimeAgoFixed(story.time),
                    color = NeonYellow,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Author
                story.by?.let { author ->
                    Text(
                        text = author,
                        color = if (onAuthorClick != null) NeonCyan else TextSecondary,
                        fontSize = 12.sp,
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
                onBookmarkClick?.let { onClick ->
                    IconButton(
                        onClick = onClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                            tint = if (isBookmarked) NeonGold else TextSecondary.copy(alpha = 0.7f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            
            // Subtle URL indicator
            story.url?.let { url ->
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { 
                            onUrlClick?.invoke(url, story.title ?: "Untitled")
                        }
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = "External Link",
                        tint = NeonCyan.copy(alpha = 0.7f),
                        modifier = Modifier.size(12.dp)
                    )
                    
                    Text(
                        text = try {
                            java.net.URL(url).host
                        } catch (e: Exception) {
                            "external link"
                        },
                        color = NeonCyan.copy(alpha = 0.8f),
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

fun formatTimeAgoFixed(timestamp: Long): String {
    val now = System.currentTimeMillis() / 1000
    val diff = now - timestamp
    
    return when {
        diff < 60 -> "now"
        diff < 3600 -> "${diff / 60}m"
        diff < 86400 -> "${diff / 3600}h"
        diff < 604800 -> "${diff / 86400}d"
        diff < 2592000 -> "${diff / 604800}w"
        diff < 31536000 -> "${diff / 2592000}mo"
        else -> "${diff / 31536000}y"
    }
}

@Composable
fun AnalysisCard(
    analysis: com.hackernews.futuristic.data.model.StoryAnalysis
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = analysis.threatLevel.color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NEURAL ANALYSIS",
                    color = NeonBlue,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = analysis.threatLevel.displayName,
                    color = analysis.threatLevel.color,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Quick stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AnalysisItem(
                    label = "SENTIMENT",
                    value = String.format("%.2f", analysis.sentimentScore),
                    color = when {
                        analysis.sentimentScore > 0.3f -> NeonGreen
                        analysis.sentimentScore < -0.3f -> NeonRed
                        else -> NeonYellow
                    }
                )
                
                AnalysisItem(
                    label = "TOPIC",
                    value = analysis.topicClassification.primaryTopic.displayName,
                    color = analysis.topicClassification.primaryTopic.color
                )
                
                AnalysisItem(
                    label = "CONTROVERSY",
                    value = "${(analysis.controversyLevel * 100).toInt()}%",
                    color = when {
                        analysis.controversyLevel > 0.7f -> NeonRed
                        analysis.controversyLevel > 0.4f -> NeonYellow
                        else -> NeonGreen
                    }
                )
            }
        }
    }
}

@Composable
fun AnalysisItem(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = color,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 8.sp,
            fontFamily = MonospaceFont
        )
    }
}
