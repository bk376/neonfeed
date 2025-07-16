package com.hackernews.futuristic.ui.stories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.snapshotFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.hackernews.futuristic.ui.theme.*

@Composable
fun StoriesScreenFixed(
    onStoryClick: (Story) -> Unit,
    onUrlClick: ((String, String) -> Unit)? = null,
    onScrollToTopCallback: ((suspend () -> Unit) -> Unit)? = null,
    viewModel: StoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedStoryType by viewModel.selectedStoryType.collectAsStateWithLifecycle()
    val isNeuralAnalysisEnabled by viewModel.isNeuralAnalysisEnabled.collectAsStateWithLifecycle()
    val isHolographicModeEnabled by viewModel.isHolographicModeEnabled.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val analysisResults by viewModel.analysisResults.collectAsStateWithLifecycle()
    
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
                
                // Story type selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StoryType.values().forEach { type ->
                        FixedButton(
                            text = type.name,
                            isSelected = selectedStoryType == type,
                            onClick = { viewModel.selectStoryType(type) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Neural analysis toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleNeuralAnalysis() }
                        .background(
                            if (isNeuralAnalysisEnabled) NeonGreen.copy(alpha = 0.1f) else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                        .border(
                            1.dp,
                            if (isNeuralAnalysisEnabled) NeonGreen else DarkBorder,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Memory,
                        contentDescription = "Neural Analysis",
                        tint = if (isNeuralAnalysisEnabled) NeonGreen else TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Text(
                        text = "NEURAL ANALYSIS",
                        color = if (isNeuralAnalysisEnabled) NeonGreen else TextSecondary,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text(
                        text = if (isNeuralAnalysisEnabled) "ON" else "OFF",
                        color = if (isNeuralAnalysisEnabled) NeonGreen else TextSecondary,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Holographic mode toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleHolographicMode() }
                        .background(
                            if (isHolographicModeEnabled) NeonPurple.copy(alpha = 0.1f) else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                        .border(
                            1.dp,
                            if (isHolographicModeEnabled) NeonPurple else DarkBorder,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ViewInAr,
                        contentDescription = "Holographic Mode",
                        tint = if (isHolographicModeEnabled) NeonPurple else TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Text(
                        text = "HOLOGRAPHIC MODE",
                        color = if (isHolographicModeEnabled) NeonPurple else TextSecondary,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text(
                        text = if (isHolographicModeEnabled) "ON" else "OFF",
                        color = if (isHolographicModeEnabled) NeonPurple else TextSecondary,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
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
                
                Box(modifier = Modifier.fillMaxSize()) {
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
                                    onClick = { 
                                        onStoryClick(story)
                                        viewModel.onStoryRead(story, readingTime = 180L, completed = true)
                                    },
                                    onUrlClick = onUrlClick
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
                }
            }
        }
    }
}

@Composable
private fun FixedButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(36.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) NeonBlue else DarkCard,
            contentColor = if (isSelected) Color.Black else NeonBlue
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun EnhancedStoryCard(
    story: Story,
    onStoryClick: () -> Unit,
    onUrlClick: ((String, String) -> Unit)? = null
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
                    text = formatTimeAgo(story.time),
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
                        color = TextSecondary,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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

private fun formatTimeAgo(timestamp: Long): String {
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
private fun AnalysisCard(
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
private fun AnalysisItem(
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