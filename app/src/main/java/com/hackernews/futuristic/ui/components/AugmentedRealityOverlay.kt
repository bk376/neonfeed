package com.hackernews.futuristic.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.model.AROverlayData
import com.hackernews.futuristic.data.model.ARMarker
import com.hackernews.futuristic.data.model.ARInfoPanel
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.*

@Composable
fun AugmentedRealityOverlay(
    story: Story,
    isAREnabled: Boolean,
    onARToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    var arData by remember { mutableStateOf(generateARData(story)) }
    var isDragging by remember { mutableStateOf(false) }
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    
    val scanlineProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    val hologramGlow by rememberInfiniteTransition().animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (isAREnabled) {
            // AR Grid Background
            ARGridBackground(
                scanlineProgress = scanlineProgress,
                modifier = Modifier.fillMaxSize()
            )
            
            // AR Markers
            arData.markers.forEach { marker ->
                ARMarkerComponent(
                    marker = marker,
                    hologramGlow = hologramGlow,
                    modifier = Modifier.offset(
                        x = marker.position.x.dp + dragOffset.x.dp,
                        y = marker.position.y.dp + dragOffset.y.dp
                    )
                )
            }
            
            // AR Info Panels
            arData.infoPanels.forEach { panel ->
                ARInfoPanelComponent(
                    panel = panel,
                    scanlineProgress = scanlineProgress,
                    modifier = Modifier.offset(
                        x = panel.position.x.dp + dragOffset.x.dp,
                        y = panel.position.y.dp + dragOffset.y.dp
                    )
                )
            }
            
            // AR HUD
            ARHUDComponent(
                story = story,
                scanlineProgress = scanlineProgress,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            
            // AR Targeting System
            ARTargetingSystem(
                hologramGlow = hologramGlow,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        
        // AR Control Panel
        ARControlPanel(
            isAREnabled = isAREnabled,
            onARToggle = onARToggle,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun ARGridBackground(
    scanlineProgress: Float,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        drawARGrid(
            scanlineProgress = scanlineProgress,
            size = size
        )
    }
}

@Composable
private fun ARMarkerComponent(
    marker: ARMarker,
    hologramGlow: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .zIndex(1f)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawARMarker(
                marker = marker,
                hologramGlow = hologramGlow,
                size = size
            )
        }
        
        // Marker label
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 8.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = marker.color.copy(alpha = 0.9f)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = marker.label,
                    color = Color.Black,
                    fontSize = 8.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun ARInfoPanelComponent(
    panel: ARInfoPanel,
    scanlineProgress: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .zIndex(2f),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            // Holographic scanlines
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                drawHolographicScanlines(
                    scanlineProgress = scanlineProgress,
                    size = size
                )
            }
            
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Panel header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = panel.icon,
                        contentDescription = panel.title,
                        tint = panel.color,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Text(
                        text = panel.title,
                        color = panel.color,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Panel content
                panel.content.forEach { content ->
                    Text(
                        text = content,
                        color = LightText,
                        fontSize = 9.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1
                    )
                }
                
                // Panel metrics
                if (panel.metrics.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        panel.metrics.forEach { (key, value) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = value,
                                    color = NeonGreen,
                                    fontSize = 10.sp,
                                    fontFamily = MonospaceFont,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = key,
                                    color = TextSecondary,
                                    fontSize = 7.sp,
                                    fontFamily = MonospaceFont
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ARHUDComponent(
    story: Story,
    scanlineProgress: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // HUD Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "AR_HUD",
                    color = NeonCyan,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                // Status indicator
                Canvas(
                    modifier = Modifier.size(8.dp)
                ) {
                    val alpha = 0.5f + 0.5f * sin(scanlineProgress * 4 * PI).toFloat()
                    drawCircle(
                        color = NeonGreen.copy(alpha = alpha),
                        radius = size.width / 2
                    )
                }
            }
            
            // Story metadata
            ARMetadataDisplay(
                story = story,
                scanlineProgress = scanlineProgress
            )
            
            // AR Controls
            ARControlsDisplay(
                scanlineProgress = scanlineProgress
            )
        }
    }
}

@Composable
private fun ARMetadataDisplay(
    story: Story,
    scanlineProgress: Float
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "STORY_${story.id}",
            color = NeonBlue,
            fontSize = 8.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "SCORE: ${story.score}",
            color = NeonGreen,
            fontSize = 8.sp,
            fontFamily = MonospaceFont
        )
        
        Text(
            text = "COMMENTS: ${story.descendants ?: 0}",
            color = NeonPurple,
            fontSize = 8.sp,
            fontFamily = MonospaceFont
        )
        
        story.by?.let { author ->
            Text(
                text = "BY: $author",
                color = NeonYellow,
                fontSize = 8.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun ARControlsDisplay(
    scanlineProgress: Float
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "CONTROLS",
            color = NeonCyan,
            fontSize = 8.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "• TAP: SELECT",
            color = TextSecondary,
            fontSize = 7.sp,
            fontFamily = MonospaceFont
        )
        
        Text(
            text = "• DRAG: MOVE",
            color = TextSecondary,
            fontSize = 7.sp,
            fontFamily = MonospaceFont
        )
        
        Text(
            text = "• LONG: DETAILS",
            color = TextSecondary,
            fontSize = 7.sp,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun ARTargetingSystem(
    hologramGlow: Float,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier.size(100.dp)
    ) {
        drawARTargetingReticle(
            hologramGlow = hologramGlow,
            size = size
        )
    }
}

@Composable
private fun ARControlPanel(
    isAREnabled: Boolean,
    onARToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isAREnabled) NeonBlue.copy(alpha = 0.2f) else DarkCard
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        IconButton(
            onClick = onARToggle,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = if (isAREnabled) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = if (isAREnabled) "Disable AR" else "Enable AR",
                tint = if (isAREnabled) NeonBlue else TextSecondary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

private fun DrawScope.drawARGrid(
    scanlineProgress: Float,
    size: Size
) {
    val gridSize = 40f
    val alpha = 0.1f + 0.05f * sin(scanlineProgress * 2 * PI).toFloat()
    
    // Vertical lines
    var x = 0f
    while (x <= size.width) {
        drawLine(
            color = NeonBlue.copy(alpha = alpha),
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1.dp.toPx()
        )
        x += gridSize
    }
    
    // Horizontal lines
    var y = 0f
    while (y <= size.height) {
        drawLine(
            color = NeonBlue.copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx()
        )
        y += gridSize
    }
    
    // Scanning line
    val scanY = size.height * scanlineProgress
    drawLine(
        color = NeonCyan.copy(alpha = 0.8f),
        start = Offset(0f, scanY),
        end = Offset(size.width, scanY),
        strokeWidth = 2.dp.toPx()
    )
}

private fun DrawScope.drawARMarker(
    marker: ARMarker,
    hologramGlow: Float,
    size: Size
) {
    val center = Offset(size.width / 2, size.height / 2)
    val radius = size.width / 3
    
    // Outer ring
    drawCircle(
        color = marker.color.copy(alpha = hologramGlow),
        radius = radius,
        center = center,
        style = Stroke(width = 2.dp.toPx())
    )
    
    // Inner core
    drawCircle(
        color = marker.color,
        radius = radius / 3,
        center = center
    )
    
    // Crosshair
    drawLine(
        color = marker.color,
        start = Offset(center.x - radius / 2, center.y),
        end = Offset(center.x + radius / 2, center.y),
        strokeWidth = 2.dp.toPx()
    )
    
    drawLine(
        color = marker.color,
        start = Offset(center.x, center.y - radius / 2),
        end = Offset(center.x, center.y + radius / 2),
        strokeWidth = 2.dp.toPx()
    )
}

private fun DrawScope.drawHolographicScanlines(
    scanlineProgress: Float,
    size: Size
) {
    val lineSpacing = 4f
    var y = 0f
    
    while (y <= size.height) {
        val alpha = 0.1f + 0.05f * sin(scanlineProgress * 2 * PI + y / 10f).toFloat()
        drawLine(
            color = NeonCyan.copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx()
        )
        y += lineSpacing
    }
}

private fun DrawScope.drawARTargetingReticle(
    hologramGlow: Float,
    size: Size
) {
    val center = Offset(size.width / 2, size.height / 2)
    val radius = size.width / 3
    
    // Outer targeting ring
    drawCircle(
        color = NeonGreen.copy(alpha = hologramGlow),
        radius = radius,
        center = center,
        style = Stroke(width = 2.dp.toPx())
    )
    
    // Corner brackets
    val cornerSize = 10f
    val corners = listOf(
        // Top-left
        Offset(center.x - radius, center.y - radius),
        // Top-right
        Offset(center.x + radius, center.y - radius),
        // Bottom-left
        Offset(center.x - radius, center.y + radius),
        // Bottom-right
        Offset(center.x + radius, center.y + radius)
    )
    
    corners.forEach { corner ->
        // Horizontal lines
        drawLine(
            color = NeonGreen.copy(alpha = hologramGlow),
            start = Offset(corner.x - cornerSize, corner.y),
            end = Offset(corner.x + cornerSize, corner.y),
            strokeWidth = 2.dp.toPx()
        )
        
        // Vertical lines
        drawLine(
            color = NeonGreen.copy(alpha = hologramGlow),
            start = Offset(corner.x, corner.y - cornerSize),
            end = Offset(corner.x, corner.y + cornerSize),
            strokeWidth = 2.dp.toPx()
        )
    }
    
    // Center dot
    drawCircle(
        color = NeonGreen,
        radius = 3.dp.toPx(),
        center = center
    )
}

private fun generateARData(story: Story): AROverlayData {
    val markers = listOf(
        ARMarker(
            id = "score",
            label = "SCORE",
            position = Offset(50f, 100f),
            color = NeonGreen,
            data = mapOf("value" to story.score.toString())
        ),
        ARMarker(
            id = "comments",
            label = "COMMENTS",
            position = Offset(200f, 150f),
            color = NeonPurple,
            data = mapOf("value" to (story.descendants ?: 0).toString())
        ),
        ARMarker(
            id = "author",
            label = "AUTHOR",
            position = Offset(100f, 250f),
            color = NeonYellow,
            data = mapOf("value" to (story.by ?: "Unknown"))
        )
    )
    
    val infoPanels = listOf(
        ARInfoPanel(
            id = "story_info",
            title = "STORY_ANALYSIS",
            position = Offset(50f, 300f),
            color = NeonBlue,
            icon = Icons.Default.Info,
            content = listOf(
                "TITLE: ${story.title?.take(30) ?: "Untitled"}...",
                "TIME: ${formatTimeAgo(story.time)}",
                "TYPE: ${story.type?.uppercase() ?: "STORY"}"
            ),
            metrics = mapOf(
                "SCORE" to story.score.toString(),
                "REPLIES" to (story.descendants ?: 0).toString()
            )
        ),
        ARInfoPanel(
            id = "engagement",
            title = "ENGAGEMENT_METRICS",
            position = Offset(250f, 200f),
            color = NeonCyan,
            icon = Icons.Default.TrendingUp,
            content = listOf(
                "ACTIVITY: ${if (story.score > 100) "HIGH" else "MODERATE"}",
                "DISCUSSION: ${if ((story.descendants ?: 0) > 50) "ACTIVE" else "QUIET"}",
                "VELOCITY: ${calculateVelocity(story)}"
            ),
            metrics = mapOf(
                "RANK" to "#${(1..500).random()}",
                "TREND" to "↑${(1..20).random()}%"
            )
        )
    )
    
    return AROverlayData(
        markers = markers,
        infoPanels = infoPanels
    )
}

private fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis() / 1000
    val diff = now - timestamp
    
    return when {
        diff < 3600 -> "${diff / 60}MIN"
        diff < 86400 -> "${diff / 3600}HRS"
        else -> "${diff / 86400}DAYS"
    }
}

private fun calculateVelocity(story: Story): String {
    val score = story.score
    val comments = story.descendants ?: 0
    val velocity = (score + comments * 2) / 10
    
    return when {
        velocity > 50 -> "VIRAL"
        velocity > 20 -> "RISING"
        velocity > 5 -> "STEADY"
        else -> "SLOW"
    }
}