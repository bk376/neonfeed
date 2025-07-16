package com.hackernews.futuristic.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun NeuralNetworkVisualization(
    visualization: NeuralNetworkVisualization,
    modifier: Modifier = Modifier
) {
    val animationProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        DarkBackground.copy(alpha = 0.9f),
                        Color.Black.copy(alpha = 0.95f)
                    ),
                    radius = 800f
                ),
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawNeuralNetwork(
                visualization = visualization,
                animationProgress = animationProgress,
                size = size
            )
        }
        
        // Neural network legend
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NeuralNetworkLegend()
        }
    }
}

@Composable
private fun NeuralNetworkLegend() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        LegendItem(color = NeonBlue, text = "Input")
        LegendItem(color = NeonPurple, text = "Hidden")
        LegendItem(color = NeonGreen, text = "Output")
    }
}

@Composable
private fun LegendItem(color: Color, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape)
        )
        Text(
            text = text,
            color = color,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
    }
}

private fun DrawScope.drawNeuralNetwork(
    visualization: NeuralNetworkVisualization,
    animationProgress: Float,
    size: androidx.compose.ui.geometry.Size
) {
    val width = size.width
    val height = size.height
    
    // Draw connections first (behind nodes)
    visualization.connections.forEach { connection ->
        val fromNode = visualization.nodes.find { it.id == connection.fromNodeId }
        val toNode = visualization.nodes.find { it.id == connection.toNodeId }
        
        if (fromNode != null && toNode != null) {
            val fromPos = Offset(fromNode.x * width, fromNode.y * height)
            val toPos = Offset(toNode.x * width, toNode.y * height)
            
            val alpha = if (connection.isActive) {
                0.6f + 0.4f * sin(animationProgress * 6.28f)
            } else {
                0.2f
            }
            
            val color = when {
                connection.weight > 0.5f -> NeonGreen
                connection.weight > 0f -> NeonBlue
                connection.weight > -0.5f -> NeonYellow
                else -> NeonRed
            }.copy(alpha = alpha)
            
            drawLine(
                color = color,
                start = fromPos,
                end = toPos,
                strokeWidth = 2.dp.toPx(),
                pathEffect = if (connection.isActive) {
                    PathEffect.dashPathEffect(floatArrayOf(5f, 5f), animationProgress * 10f)
                } else null
            )
        }
    }
    
    // Draw nodes
    visualization.nodes.forEach { node ->
        val position = Offset(node.x * width, node.y * height)
        val radius = 12.dp.toPx()
        
        val nodeColor = when (node.type) {
            NodeType.INPUT -> NeonBlue
            NodeType.HIDDEN -> NeonPurple
            NodeType.OUTPUT -> NeonGreen
        }
        
        val glowAlpha = 0.3f + 0.7f * node.activation
        val pulseRadius = radius * (1f + 0.3f * sin(animationProgress * 6.28f + node.activation * 3.14f))
        
        // Draw glow effect
        drawCircle(
            color = nodeColor.copy(alpha = glowAlpha * 0.3f),
            radius = pulseRadius * 1.5f,
            center = position
        )
        
        // Draw node
        drawCircle(
            color = nodeColor.copy(alpha = glowAlpha),
            radius = radius,
            center = position
        )
        
        // Draw node border
        drawCircle(
            color = nodeColor,
            radius = radius,
            center = position,
            style = Stroke(width = 2.dp.toPx())
        )
        
        // Draw activation level
        if (node.activation > 0.1f) {
            drawCircle(
                color = Color.White.copy(alpha = node.activation),
                radius = radius * node.activation,
                center = position
            )
        }
    }
}

@Composable
fun StoryAnalysisCard(
    analysis: StoryAnalysis,
    modifier: Modifier = Modifier
) {
    GlowingCard(
        modifier = modifier,
        glowColor = analysis.threatLevel.color
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header with threat level
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NEURAL ANALYSIS",
                    color = NeonBlue,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                ThreatLevelIndicator(threatLevel = analysis.threatLevel)
            }
            
            // Sentiment and emotion analysis
            SentimentAnalysisRow(
                sentimentScore = analysis.sentimentScore,
                emotionProfile = analysis.emotionProfile
            )
            
            // Topic classification
            TopicClassificationRow(
                topicClassification = analysis.topicClassification
            )
            
            // Metrics row
            MetricsRow(
                controversyLevel = analysis.controversyLevel,
                relevanceScore = analysis.relevanceScore
            )
            
            // Keywords
            if (analysis.keywords.isNotEmpty()) {
                KeywordChips(keywords = analysis.keywords)
            }
        }
    }
}

@Composable
private fun ThreatLevelIndicator(threatLevel: ThreatLevel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(threatLevel.color, CircleShape)
        )
        Text(
            text = threatLevel.displayName,
            color = threatLevel.color,
            fontSize = 10.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SentimentAnalysisRow(
    sentimentScore: Float,
    emotionProfile: EmotionProfile
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Sentiment score
        Column {
            Text(
                text = "SENTIMENT",
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
            Text(
                text = String.format("%.2f", sentimentScore),
                color = when {
                    sentimentScore > 0.3f -> NeonGreen
                    sentimentScore < -0.3f -> NeonRed
                    else -> NeonYellow
                },
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Dominant emotion
        Column {
            Text(
                text = "EMOTION",
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
            val dominantEmotion = emotionProfile.getDominantEmotion()
            Text(
                text = dominantEmotion.displayName,
                color = dominantEmotion.color,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun TopicClassificationRow(
    topicClassification: TopicClassification
) {
    Column {
        Text(
            text = "TOPIC CLASSIFICATION",
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = topicClassification.primaryTopic.displayName,
                color = topicClassification.primaryTopic.color,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = String.format("%.1f%%", topicClassification.confidence * 100),
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun MetricsRow(
    controversyLevel: Float,
    relevanceScore: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MetricItem(
            label = "CONTROVERSY",
            value = controversyLevel,
            color = when {
                controversyLevel > 0.7f -> NeonRed
                controversyLevel > 0.4f -> NeonYellow
                else -> NeonGreen
            }
        )
        
        MetricItem(
            label = "RELEVANCE",
            value = relevanceScore,
            color = when {
                relevanceScore > 0.7f -> NeonGreen
                relevanceScore > 0.4f -> NeonYellow
                else -> NeonRed
            }
        )
    }
}

@Composable
private fun MetricItem(
    label: String,
    value: Float,
    color: Color
) {
    Column {
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
        Text(
            text = String.format("%.1f%%", value * 100),
            color = color,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun KeywordChips(keywords: List<String>) {
    Column {
        Text(
            text = "KEYWORDS",
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(keywords) { keyword ->
                Box(
                    modifier = Modifier
                        .background(
                            NeonBlue.copy(alpha = 0.2f),
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = keyword,
                        color = NeonBlue,
                        fontSize = 9.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }
        }
    }
}

@Composable
fun MatrixDataStream(
    analysis: StoryAnalysis,
    modifier: Modifier = Modifier
) {
    val animationProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.9f),
                        DarkBackground.copy(alpha = 0.7f)
                    )
                ),
                RoundedCornerShape(4.dp)
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawMatrixEffect(
                analysis = analysis,
                animationProgress = animationProgress,
                size = size
            )
        }
    }
}

private fun DrawScope.drawMatrixEffect(
    analysis: StoryAnalysis,
    animationProgress: Float,
    size: androidx.compose.ui.geometry.Size
) {
    val dataPoints = listOf(
        "SENTIMENT: ${String.format("%.2f", analysis.sentimentScore)}",
        "EMOTION: ${analysis.emotionProfile.getDominantEmotion().displayName}",
        "TOPIC: ${analysis.topicClassification.primaryTopic.displayName}",
        "CONTROVERSY: ${String.format("%.1f%%", analysis.controversyLevel * 100)}",
        "RELEVANCE: ${String.format("%.1f%%", analysis.relevanceScore * 100)}",
        "THREAT: ${analysis.threatLevel.displayName}"
    )
    
    dataPoints.forEachIndexed { index, text ->
        val yPos = (size.height / dataPoints.size) * index + 20f
        val xOffset = sin(animationProgress * 6.28f + index) * 10f
        val alpha = 0.7f + 0.3f * sin(animationProgress * 6.28f + index * 0.5f)
        
        drawContext.canvas.nativeCanvas.drawText(
            text,
            20f + xOffset,
            yPos,
            android.graphics.Paint().apply {
                color = NeonGreen.copy(alpha = alpha).toArgb()
                textSize = 24f
                typeface = android.graphics.Typeface.MONOSPACE
            }
        )
    }
}