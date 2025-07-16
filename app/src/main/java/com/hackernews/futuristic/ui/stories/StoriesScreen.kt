package com.hackernews.futuristic.ui.stories

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.components.CyberpunkButton
import com.hackernews.futuristic.ui.components.CyberpunkCard
import com.hackernews.futuristic.ui.components.GlitchText
import com.hackernews.futuristic.ui.components.GlowingCard
import com.hackernews.futuristic.ui.components.NeuralNetworkVisualization
import com.hackernews.futuristic.ui.components.PulsingLoadingIndicator
import com.hackernews.futuristic.ui.components.ScaleInAnimation
import com.hackernews.futuristic.ui.components.StoryAnalysisCard
import com.hackernews.futuristic.ui.components.UserProfileCard
import com.hackernews.futuristic.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StoriesScreen(
    onStoryClick: (Story) -> Unit,
    viewModel: StoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedStoryType by viewModel.selectedStoryType.collectAsStateWithLifecycle()
    val analysisResults by viewModel.analysisResults.collectAsStateWithLifecycle()
    val isNeuralAnalysisEnabled by viewModel.isNeuralAnalysisEnabled.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val achievements by viewModel.achievements.collectAsStateWithLifecycle()
    
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
        StoryTypeSelector(
            selectedType = selectedStoryType,
            onTypeSelected = viewModel::selectStoryType,
            modifier = Modifier.padding(16.dp)
        )
        
        NeuralAnalysisToggle(
            isEnabled = isNeuralAnalysisEnabled,
            onToggle = viewModel::toggleNeuralAnalysis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        when {
            uiState.isLoading && uiState.stories.isEmpty() -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }
            uiState.error != null && uiState.stories.isEmpty() -> {
                ErrorMessage(
                    message = uiState.error ?: "Unknown error",
                    onRetry = viewModel::retry,
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // User Profile Card
                    item {
                        UserProfileCard(
                            profile = userProfile,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    
                    items(items = uiState.stories) { story ->
                        ScaleInAnimation {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                StoryCard(
                                    story = story,
                                    onClick = { 
                                        onStoryClick(story)
                                        viewModel.onStoryRead(story, readingTime = 180L, completed = true)
                                    }
                                )
                                
                                if (isNeuralAnalysisEnabled) {
                                    analysisResults[story.id]?.let { analysis ->
                                        StoryAnalysisCard(
                                            analysis = analysis,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoryTypeSelector(
    selectedType: StoryType,
    onTypeSelected: (StoryType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        StoryType.values().forEach { type ->
            CyberpunkButton(
                text = type.name,
                isSelected = selectedType == type,
                onClick = { onTypeSelected(type) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StoryCard(
    story: Story,
    onClick: () -> Unit
) {
    CyberpunkCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            story.title?.let { title ->
                GlitchText(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = LightText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CyberpunkScoreChip(score = story.score)
                    story.by?.let { author ->
                        Text(
                            text = "by $author",
                            style = MaterialTheme.typography.bodySmall,
                            color = NeonYellow
                        )
                    }
                }
                
                Text(
                    text = formatTimeAgo(story.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = DimText
                )
            }
            
            story.descendants?.let { commentCount ->
                if (commentCount > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$commentCount comments",
                        style = MaterialTheme.typography.bodySmall,
                        color = NeonGreen
                    )
                }
            }
        }
    }
}

@Composable
fun CyberpunkScoreChip(score: Int) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        NeonYellow.copy(alpha = 0.3f),
                        CyberpunkOrange.copy(alpha = 0.2f)
                    )
                ),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = NeonYellow,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = NeonYellow,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ScoreChip(score: Int) {
    CyberpunkScoreChip(score = score)
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        PulsingLoadingIndicator(
            color = NeonBlue
        )
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

@Composable
fun NeuralAnalysisToggle(
    isEnabled: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val toggleColor by animateColorAsState(
        targetValue = if (isEnabled) NeonGreen else TextSecondary,
        animationSpec = tween(300)
    )
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        DarkSurface.copy(alpha = 0.7f),
                        DarkBackground.copy(alpha = 0.9f)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isEnabled) NeonGreen else DarkBorder,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Memory,
            contentDescription = "Neural Analysis",
            tint = toggleColor,
            modifier = Modifier.size(20.dp)
        )
        
        Text(
            text = "NEURAL ANALYSIS",
            color = toggleColor,
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Text(
            text = if (isEnabled) "ON" else "OFF",
            color = toggleColor,
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        )
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