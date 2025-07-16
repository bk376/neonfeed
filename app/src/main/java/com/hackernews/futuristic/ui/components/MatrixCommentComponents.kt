package com.hackernews.futuristic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.*

@Composable
fun MatrixCommentThread(
    commentThread: CommentThread,
    modifier: Modifier = Modifier,
    onCommentClick: (ThreadComment) -> Unit = {}
) {
    val animationProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.95f),
                        DarkBackground.copy(alpha = 0.9f)
                    )
                )
            )
    ) {
        // Thread header with metrics
        ThreadMetricsHeader(
            threadMetrics = commentThread.threadMetrics,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        
        // Matrix visualization
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp)
        ) {
            MatrixThreadVisualization(
                commentThread = commentThread,
                animationProgress = animationProgress,
                onCommentClick = onCommentClick
            )
        }
        
        // Comment list with enhanced styling
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(commentThread.comments) { comment ->
                MatrixCommentCard(
                    comment = comment,
                    onClick = { onCommentClick(comment) }
                )
            }
        }
    }
}

@Composable
private fun ThreadMetricsHeader(
    threadMetrics: ThreadMetrics,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "THREAD ANALYSIS",
                color = NeonBlue,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricItem(
                    label = "COMMENTS",
                    value = threadMetrics.totalComments.toString(),
                    color = NeonGreen
                )
                
                MetricItem(
                    label = "DEPTH",
                    value = threadMetrics.maxDepth.toString(),
                    color = NeonPurple
                )
                
                MetricItem(
                    label = "CONSENSUS",
                    value = "${(threadMetrics.consensusLevel * 100).toInt()}%",
                    color = when {
                        threadMetrics.consensusLevel > 0.7f -> NeonGreen
                        threadMetrics.consensusLevel > 0.3f -> NeonYellow
                        else -> NeonRed
                    }
                )
                
                MetricItem(
                    label = "VELOCITY",
                    value = String.format("%.1f", threadMetrics.discussionVelocity),
                    color = NeonBlue
                )
            }
            
            // Controversy hotspots indicator
            if (threadMetrics.controversyHotspots.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(NeonRed, CircleShape)
                    )
                    Text(
                        text = "${threadMetrics.controversyHotspots.size} CONTROVERSY HOTSPOTS DETECTED",
                        color = NeonRed,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricItem(
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
            fontSize = 14.sp,
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
private fun MatrixThreadVisualization(
    commentThread: CommentThread,
    animationProgress: Float,
    onCommentClick: (ThreadComment) -> Unit
) {
    val density = LocalDensity.current
    
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
    ) {
        drawMatrixThreadNetwork(
            commentThread = commentThread,
            animationProgress = animationProgress,
            size = size,
            density = density
        )
    }
}

private fun DrawScope.drawMatrixThreadNetwork(
    commentThread: CommentThread,
    animationProgress: Float,
    size: androidx.compose.ui.geometry.Size,
    density: androidx.compose.ui.unit.Density
) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val maxRadius = minOf(size.width, size.height) / 2 - 50f
    
    // Draw background grid
    drawMatrixGrid(size, animationProgress)
    
    // Draw comment connections
    drawCommentConnections(commentThread, centerX, centerY, maxRadius, animationProgress)
    
    // Draw comment nodes
    drawCommentNodes(commentThread, centerX, centerY, maxRadius, animationProgress)
    
    // Draw data streams
    drawDataStreams(commentThread, animationProgress, size)
}

private fun DrawScope.drawMatrixGrid(size: androidx.compose.ui.geometry.Size, animationProgress: Float) {
    val gridSize = 20f
    val alpha = 0.1f + 0.05f * sin(animationProgress * 2 * PI).toFloat()
    
    // Vertical lines
    var x = 0f
    while (x < size.width) {
        drawLine(
            color = NeonGreen.copy(alpha = alpha),
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1f
        )
        x += gridSize
    }
    
    // Horizontal lines
    var y = 0f
    while (y < size.height) {
        drawLine(
            color = NeonGreen.copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1f
        )
        y += gridSize
    }
}

private fun DrawScope.drawCommentConnections(
    commentThread: CommentThread,
    centerX: Float,
    centerY: Float,
    maxRadius: Float,
    animationProgress: Float
) {
    commentThread.comments.forEach { comment ->
        comment.parent?.let { parentId ->
            val parentComment = commentThread.comments.find { it.id == parentId }
            if (parentComment != null) {
                val startPos = calculateNodePosition(parentComment, centerX, centerY, maxRadius)
                val endPos = calculateNodePosition(comment, centerX, centerY, maxRadius)
                
                val connectionAlpha = 0.6f + 0.4f * sin(animationProgress * 2 * PI + comment.id.toFloat()).toFloat()
                
                drawLine(
                    color = comment.branchType.color.copy(alpha = connectionAlpha),
                    start = startPos,
                    end = endPos,
                    strokeWidth = comment.branchType.strokeWidth,
                    pathEffect = if (comment.sentiment.agreement < 0) {
                        PathEffect.dashPathEffect(floatArrayOf(5f, 5f), animationProgress * 20f)
                    } else null
                )
                
                // Draw flow animation
                if (comment.influence.influenceScore > 0.5f) {
                    val flowPos = lerpOffset(startPos, endPos, (animationProgress * 2) % 1f)
                    drawCircle(
                        color = comment.branchType.color,
                        radius = 3f,
                        center = flowPos
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawCommentNodes(
    commentThread: CommentThread,
    centerX: Float,
    centerY: Float,
    maxRadius: Float,
    animationProgress: Float
) {
    commentThread.comments.forEach { comment ->
        val position = calculateNodePosition(comment, centerX, centerY, maxRadius)
        val nodeSize = comment.visualProperties.nodeSize
        val glowIntensity = comment.visualProperties.glowIntensity
        
        // Glow effect
        val pulseMultiplier = 1f + 0.3f * sin(animationProgress * comment.visualProperties.pulseRate * 2 * PI).toFloat()
        val glowRadius = nodeSize * pulseMultiplier * glowIntensity
        
        drawCircle(
            color = comment.sentiment.emotion.color.copy(alpha = 0.3f),
            radius = glowRadius,
            center = position
        )
        
        // Main node
        drawCircle(
            color = comment.sentiment.emotion.color,
            radius = nodeSize / 2,
            center = position
        )
        
        // Influence indicator
        if (comment.influence.influenceScore > 0.7f) {
            drawCircle(
                color = NeonYellow,
                radius = nodeSize / 2,
                center = position,
                style = Stroke(width = 2f)
            )
        }
        
        // Controversy indicator
        if (comment.influence.controversyLevel > 0.6f) {
            val controversyRadius = nodeSize / 2 + 5f
            drawCircle(
                color = NeonRed.copy(alpha = 0.8f),
                radius = controversyRadius,
                center = position,
                style = Stroke(width = 3f)
            )
        }
    }
}

private fun DrawScope.drawDataStreams(
    commentThread: CommentThread,
    animationProgress: Float,
    size: androidx.compose.ui.geometry.Size
) {
    commentThread.comments.take(5).forEachIndexed { index, comment ->
        val stream = generateMatrixDataStream(comment)
        val x = (index * 80f) % size.width
        val streamOffset = (animationProgress * 200f) % (size.height + 100f)
        
        stream.textChunks.forEachIndexed { chunkIndex, chunk ->
            val y = streamOffset + (chunkIndex * 20f)
            if (y > 0 && y < size.height) {
                val alpha = stream.opacity * (1f - (y / size.height))
                
                try {
                    drawContext.canvas.nativeCanvas.drawText(
                        chunk.take(50), // Limit text length
                        x,
                        y,
                        android.graphics.Paint().apply {
                            color = stream.color.copy(alpha = alpha.coerceIn(0f, 1f)).toArgb()
                            textSize = 16f // Smaller text size
                            typeface = android.graphics.Typeface.MONOSPACE
                            isAntiAlias = true
                        }
                    )
                } catch (e: Exception) {
                    // Skip drawing on error
                }
            }
        }
    }
}

private fun generateMatrixDataStream(comment: ThreadComment): MatrixDataStream {
    val text = comment.text?.takeIf { it.isNotBlank() } ?: "No content"
    val chunks = if (text.length > 15) text.chunked(15) else listOf(text)
    
    return MatrixDataStream(
        commentId = comment.id,
        textChunks = chunks,
        scrollSpeed = comment.visualProperties.textScrollSpeed.coerceIn(0.5f, 5f),
        color = comment.sentiment.emotion.color,
        opacity = comment.influence.influenceScore.coerceIn(0.3f, 1f),
        trailLength = comment.visualProperties.matrixTrailLength.coerceIn(1, 20),
        glitchEffect = comment.influence.controversyLevel > 0.7f
    )
}

private fun calculateNodePosition(
    comment: ThreadComment,
    centerX: Float,
    centerY: Float,
    maxRadius: Float
): Offset {
    val angle = comment.threadPosition.angle
    val radius = (comment.depth * 50f).coerceAtMost(maxRadius)
    
    return Offset(
        centerX + cos(angle) * radius,
        centerY + sin(angle) * radius
    )
}

private fun lerpOffset(start: Offset, end: Offset, t: Float): Offset {
    return Offset(
        start.x + (end.x - start.x) * t,
        start.y + (end.y - start.y) * t
    )
}

@Composable
private fun MatrixCommentCard(
    comment: ThreadComment,
    onClick: () -> Unit
) {
    val cardColor by animateColorAsState(
        targetValue = comment.sentiment.emotion.color.copy(alpha = 0.1f),
        animationSpec = tween(300)
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(start = (comment.depth * 16).dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header with user and sentiment
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Branch type indicator
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(comment.branchType.color, CircleShape)
                    )
                    
                    // Username
                    Text(
                        text = comment.by ?: "anonymous",
                        color = comment.sentiment.emotion.color,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // Sentiment indicator
                    Text(
                        text = comment.sentiment.emotion.displayName,
                        color = comment.sentiment.emotion.color,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                }
                
                // Influence score
                if (comment.influence.influenceScore > 0.3f) {
                    Text(
                        text = "INFLUENCE: ${(comment.influence.influenceScore * 100).toInt()}%",
                        color = NeonYellow,
                        fontSize = 8.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }
            
            // Comment text
            comment.text?.let { text ->
                Text(
                    text = text,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            // Metrics row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Reply count
                    Text(
                        text = "↳ ${comment.influence.replyCount}",
                        color = NeonGreen,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                    
                    // Controversy level
                    if (comment.influence.controversyLevel > 0.5f) {
                        Text(
                            text = "⚠ ${(comment.influence.controversyLevel * 100).toInt()}%",
                            color = NeonRed,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                    
                    // Agreement indicator
                    if (comment.sentiment.agreement != 0f) {
                        val agreementIcon = if (comment.sentiment.agreement > 0) "✓" else "✗"
                        val agreementColor = if (comment.sentiment.agreement > 0) NeonGreen else NeonRed
                        Text(
                            text = agreementIcon,
                            color = agreementColor,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
                
                // Depth indicator
                Text(
                    text = "DEPTH: ${comment.depth}",
                    color = TextSecondary,
                    fontSize = 8.sp,
                    fontFamily = MonospaceFont
                )
            }
        }
    }
}

@Composable
fun CommentThreadSelector(
    viewMode: CommentViewMode,
    onViewModeChange: (CommentViewMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CommentViewMode.values().forEach { mode ->
            Button(
                onClick = { onViewModeChange(mode) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewMode == mode) NeonBlue else DarkSurface,
                    contentColor = if (viewMode == mode) Color.Black else NeonBlue
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = mode.displayName,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

enum class CommentViewMode(val displayName: String) {
    MATRIX("MATRIX"),
    TREE("TREE"),
    LINEAR("LINEAR"),
    NETWORK("NETWORK")
}