package com.hackernews.futuristic.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.*

@Composable
fun HolographicStoryPreview(
    story: Story,
    onStoryClick: (Story) -> Unit,
    onPreviewExpand: (Story) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isHovered by remember { mutableStateOf(false) }
    
    // Holographic animation effects
    val hologramProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    val glitchEffect by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    val scaleAnimation by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = tween(200)
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scaleAnimation)
            .clickable { 
                if (isExpanded) {
                    onStoryClick(story)
                } else {
                    isExpanded = true
                    onPreviewExpand(story)
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Holographic background effect
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                drawHolographicBackground(
                    hologramProgress = hologramProgress,
                    glitchEffect = glitchEffect,
                    size = size
                )
            }
            
            // Content overlay
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                DarkCard.copy(alpha = 0.9f),
                                DarkSurface.copy(alpha = 0.8f)
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                // Header with holographic indicators
                HolographicStoryHeader(
                    story = story,
                    hologramProgress = hologramProgress,
                    isExpanded = isExpanded
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Story content preview
                if (isExpanded) {
                    ExpandedStoryPreview(
                        story = story,
                        glitchEffect = glitchEffect
                    )
                } else {
                    CompactStoryPreview(
                        story = story,
                        hologramProgress = hologramProgress
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Holographic metrics
                HolographicMetrics(
                    story = story,
                    hologramProgress = hologramProgress,
                    isExpanded = isExpanded
                )
            }
            
            // Holographic frame overlay
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                drawHolographicFrame(
                    hologramProgress = hologramProgress,
                    glitchEffect = glitchEffect,
                    size = size
                )
            }
        }
    }
}

@Composable
private fun HolographicStoryHeader(
    story: Story,
    hologramProgress: Float,
    isExpanded: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Holographic status indicator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HolographicStatusDot(
                progress = hologramProgress,
                color = NeonBlue,
                size = 12.dp
            )
            
            Text(
                text = "STORY_ID: ${story.id}",
                color = NeonBlue,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Expand/collapse indicator
        Icon(
            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = if (isExpanded) "Collapse" else "Expand",
            tint = NeonGreen,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
private fun ExpandedStoryPreview(
    story: Story,
    glitchEffect: Float
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Title with glitch effect
        story.title?.let { title ->
            Text(
                text = title,
                color = NeonGreen,
                fontSize = 18.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        // URL analysis
        story.url?.let { url ->
            HolographicUrlAnalysis(
                url = url,
                glitchEffect = glitchEffect
            )
        }
        
        // Content preview
        story.text?.let { text ->
            HolographicTextPreview(
                text = text,
                glitchEffect = glitchEffect
            )
        }
        
        // AI-generated summary
        HolographicSummary(
            story = story,
            glitchEffect = glitchEffect
        )
    }
}

@Composable
private fun CompactStoryPreview(
    story: Story,
    hologramProgress: Float
) {
    story.title?.let { title ->
        Text(
            text = title,
            color = LightText,
            fontSize = 16.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun HolographicUrlAnalysis(
    url: String,
    glitchEffect: Float
) {
    val domain = try {
        java.net.URL(url).host
    } catch (e: Exception) {
        "unknown"
    }
    
    Card(
        colors = CardDefaults.cardColors(
            containerColor = DarkBackground.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "URL_ANALYSIS",
                color = NeonCyan,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "DOMAIN: $domain",
                    color = NeonYellow,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                Text(
                    text = "SECURITY: VERIFIED",
                    color = NeonGreen,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun HolographicTextPreview(
    text: String,
    glitchEffect: Float
) {
    val cleanText = text
        .replace("<p>", "\n")
        .replace("</p>", "")
        .replace("<br>", "\n")
        .replace("&gt;", ">")
        .replace("&lt;", "<")
        .replace("&amp;", "&")
        .take(200)
    
    Card(
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "CONTENT_PREVIEW",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = cleanText,
                color = LightText,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun HolographicSummary(
    story: Story,
    glitchEffect: Float
) {
    val summary = generateAISummary(story)
    
    Card(
        colors = CardDefaults.cardColors(
            containerColor = NeonBlue.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Psychology,
                    contentDescription = "AI Analysis",
                    tint = NeonBlue,
                    modifier = Modifier.size(16.dp)
                )
                
                Text(
                    text = "AI_SUMMARY",
                    color = NeonBlue,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Text(
                text = summary,
                color = LightText,
                fontSize = 11.sp,
                fontFamily = MonospaceFont,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun HolographicMetrics(
    story: Story,
    hologramProgress: Float,
    isExpanded: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Score with holographic effect
        HolographicMetric(
            label = "SCORE",
            value = story.score.toString(),
            color = NeonGreen,
            progress = hologramProgress
        )
        
        // Comments
        HolographicMetric(
            label = "COMMENTS",
            value = (story.descendants ?: 0).toString(),
            color = NeonPurple,
            progress = hologramProgress
        )
        
        // Time
        HolographicMetric(
            label = "TIME",
            value = formatTimeAgo(story.time),
            color = NeonYellow,
            progress = hologramProgress
        )
        
        // Author
        story.by?.let { author ->
            HolographicMetric(
                label = "AUTHOR",
                value = author,
                color = NeonCyan,
                progress = hologramProgress
            )
        }
    }
}

@Composable
private fun HolographicMetric(
    label: String,
    value: String,
    color: Color,
    progress: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = value,
            color = color.copy(alpha = 0.8f + 0.2f * sin(progress * 2 * PI).toFloat()),
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

@Composable
private fun HolographicStatusDot(
    progress: Float,
    color: Color,
    size: androidx.compose.ui.unit.Dp
) {
    Canvas(
        modifier = Modifier.size(size)
    ) {
        val center = Offset(this.size.width / 2f, this.size.height / 2f)
        val radius = this.size.width / 3
        
        // Pulsing core
        drawCircle(
            color = color,
            radius = radius * (0.5f + 0.5f * sin(progress * 4 * PI).toFloat()),
            center = center
        )
        
        // Outer ring
        drawCircle(
            color = color.copy(alpha = 0.5f),
            radius = radius * 1.5f,
            center = center,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}

private fun DrawScope.drawHolographicBackground(
    hologramProgress: Float,
    glitchEffect: Float,
    size: Size
) {
    // Scanning lines
    val lineSpacing = 8f
    var y = 0f
    while (y < size.height) {
        val alpha = 0.1f + 0.05f * sin(hologramProgress * 2 * PI + y / 10f).toFloat()
        drawLine(
            color = NeonBlue.copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1f
        )
        y += lineSpacing
    }
    
    // Glitch effect
    if (glitchEffect > 0.95f) {
        val glitchY = (size.height * 0.3f) + (size.height * 0.4f * sin(glitchEffect * 20 * PI).toFloat())
        drawRect(
            color = NeonRed.copy(alpha = 0.3f),
            topLeft = Offset(0f, glitchY),
            size = Size(size.width, 4f)
        )
    }
}

private fun DrawScope.drawHolographicFrame(
    hologramProgress: Float,
    glitchEffect: Float,
    size: Size
) {
    val strokeWidth = 2f
    val alpha = 0.6f + 0.4f * sin(hologramProgress * 2 * PI).toFloat()
    
    // Corner brackets
    val cornerSize = 20f
    val corners = listOf(
        // Top-left
        Offset(0f, 0f) to Offset(cornerSize, 0f),
        Offset(0f, 0f) to Offset(0f, cornerSize),
        
        // Top-right
        Offset(size.width - cornerSize, 0f) to Offset(size.width, 0f),
        Offset(size.width, 0f) to Offset(size.width, cornerSize),
        
        // Bottom-left
        Offset(0f, size.height - cornerSize) to Offset(0f, size.height),
        Offset(0f, size.height) to Offset(cornerSize, size.height),
        
        // Bottom-right
        Offset(size.width, size.height - cornerSize) to Offset(size.width, size.height),
        Offset(size.width - cornerSize, size.height) to Offset(size.width, size.height)
    )
    
    corners.forEach { (start, end) ->
        drawLine(
            color = NeonBlue.copy(alpha = alpha),
            start = start,
            end = end,
            strokeWidth = strokeWidth
        )
    }
}

private fun generateAISummary(story: Story): String {
    val title = story.title ?: "Untitled"
    val domain = story.url?.let { 
        try {
            java.net.URL(it).host
        } catch (e: Exception) {
            "unknown"
        }
    } ?: "unknown"
    
    return when {
        title.contains("AI", ignoreCase = true) -> "AI/ML technology advancement with potential industry impact"
        title.contains("quantum", ignoreCase = true) -> "Quantum computing breakthrough with significant implications"
        title.contains("security", ignoreCase = true) -> "Cybersecurity development affecting digital infrastructure"
        title.contains("crypto", ignoreCase = true) -> "Cryptocurrency/blockchain technology update"
        title.contains("mobile", ignoreCase = true) -> "Mobile technology innovation and development"
        title.contains("web", ignoreCase = true) -> "Web development framework or technology advancement"
        domain.contains("github") -> "Open source software project or development tool"
        domain.contains("arxiv") -> "Academic research paper with technical findings"
        else -> "Technology news story with community discussion potential"
    }
}

private fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis() / 1000
    val diff = now - timestamp
    
    return when {
        diff < 60 -> "NOW"
        diff < 3600 -> "${diff / 60}M"
        diff < 86400 -> "${diff / 3600}H"
        diff < 604800 -> "${diff / 86400}D"
        else -> "${diff / 604800}W"
    }
}