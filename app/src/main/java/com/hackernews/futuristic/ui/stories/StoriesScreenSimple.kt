package com.hackernews.futuristic.ui.stories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.theme.*

@Composable
fun StoriesScreenSimple(
    onStoryClick: (Story) -> Unit,
    viewModel: StoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedStoryType by viewModel.selectedStoryType.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        // Simple header
        Text(
            text = "HACKER NEWS",
            color = NeonBlue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MonospaceFont,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Simple story type selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StoryType.values().forEach { type ->
                SimpleButton(
                    text = type.name,
                    isSelected = selectedStoryType == type,
                    onClick = { viewModel.selectStoryType(type) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Content
        when {
            uiState.isLoading && uiState.stories.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = NeonBlue
                    )
                }
            }
            uiState.error != null && uiState.stories.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Error: ${uiState.error}",
                            color = NeonRed,
                            fontFamily = MonospaceFont
                        )
                        Button(
                            onClick = { viewModel.retry() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NeonBlue
                            )
                        ) {
                            Text("Retry", color = Color.Black)
                        }
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.stories) { story ->
                        SimpleStoryCard(
                            story = story,
                            onClick = { onStoryClick(story) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SimpleButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) NeonBlue else DarkSurface,
            contentColor = if (isSelected) Color.Black else NeonBlue
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun SimpleStoryCard(
    story: Story,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        ),
        shape = RoundedCornerShape(4.dp)
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
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Info row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Score
                Text(
                    text = "▲ ${story.score}",
                    color = NeonGreen,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
                
                // Author
                story.by?.let { author ->
                    Text(
                        text = "by $author",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont
                    )
                }
                
                // Comments
                Text(
                    text = "${story.descendants ?: 0} comments",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
            }
        }
    }
}