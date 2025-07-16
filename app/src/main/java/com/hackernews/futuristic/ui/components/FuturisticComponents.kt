package com.hackernews.futuristic.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun GlowingCard(
    modifier: Modifier = Modifier,
    glowColor: Color = NeonBlue,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Box(
        modifier = modifier
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        glowColor.copy(alpha = glowAlpha * 0.1f),
                        Color.Transparent
                    ),
                    radius = 200f
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = glowColor.copy(alpha = glowAlpha),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(DarkCard)
    ) {
        content()
    }
}

@Composable
fun PulsingLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = NeonBlue
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(60.dp)
        ) {
            rotate(rotation) {
                drawCircularGrid(
                    color = color,
                    scale = scale,
                    center = center
                )
            }
        }
    }
}

private fun DrawScope.drawCircularGrid(
    color: Color,
    scale: Float,
    center: Offset
) {
    val radius = 25.dp.toPx() * scale
    
    // Draw outer ring
    drawCircle(
        color = color,
        radius = radius,
        center = center,
        style = Stroke(width = 2.dp.toPx())
    )
    
    // Draw inner rings
    for (i in 1..3) {
        drawCircle(
            color = color.copy(alpha = 0.6f / i),
            radius = radius * (0.7f / i),
            center = center,
            style = Stroke(width = 1.dp.toPx())
        )
    }
    
    // Draw radial lines
    for (i in 0..7) {
        val angle = i * 45f * (kotlin.math.PI / 180f)
        val startX = center.x + cos(angle).toFloat() * radius * 0.3f
        val startY = center.y + sin(angle).toFloat() * radius * 0.3f
        val endX = center.x + cos(angle).toFloat() * radius * 0.9f
        val endY = center.y + sin(angle).toFloat() * radius * 0.9f
        
        drawLine(
            color = color.copy(alpha = 0.4f),
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            strokeWidth = 1.dp.toPx()
        )
    }
}

@Composable
fun HologramText(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = NeonBlue
) {
    val infiniteTransition = rememberInfiniteTransition()
    val shimmer by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(modifier = modifier) {
        // Shadow text
        Text(
            text = text,
            style = style,
            color = color.copy(alpha = 0.3f),
            modifier = Modifier.offset(x = 1.dp, y = 1.dp)
        )
        
        // Main text with shimmer effect
        Text(
            text = text,
            style = style,
            color = color.copy(alpha = 0.8f + shimmer * 0.2f),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FuturisticButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    color: Color = NeonBlue
) {
    val infiniteTransition = rememberInfiniteTransition()
    val borderGlow by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Button(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 1.dp,
                color = color.copy(alpha = borderGlow),
                shape = RoundedCornerShape(12.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.2f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        HologramText(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = color
        )
    }
}

@Composable
fun AnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enter: EnterTransition = slideInVertically() + fadeIn(),
    exit: ExitTransition = slideOutVertically() + fadeOut(),
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    androidx.compose.animation.AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enter,
        exit = exit,
        content = content
    )
}

@Composable
fun ScaleInAnimation(
    content: @Composable () -> Unit
) {
    val scale by remember { mutableStateOf(androidx.compose.animation.core.Animatable(0.8f)) }
    
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }
    
    Box(
        modifier = Modifier.graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        }
    ) {
        content()
    }
}

@Composable
fun CyberpunkButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    val selectedColor = if (isSelected) NeonYellow else DarkBorder
    val textColor = if (isSelected) Color.Black else LightText
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp)) // Angular, not rounded
            .background(
                brush = Brush.linearGradient(
                    colors = if (isSelected) {
                        listOf(NeonYellow.copy(alpha = 0.3f), CyberpunkOrange.copy(alpha = 0.2f))
                    } else {
                        listOf(DarkCard, DarkSurface)
                    }
                )
            )
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = if (isSelected) {
                        listOf(NeonYellow.copy(alpha = glowIntensity), CyberpunkOrange.copy(alpha = glowIntensity))
                    } else {
                        listOf(DarkBorder, DarkBorder)
                    }
                ),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Cyberpunk-style icon
            if (isSelected) {
                Canvas(modifier = Modifier.size(8.dp)) {
                    drawCircle(
                        color = NeonYellow,
                        radius = 4.dp.toPx()
                    )
                }
            }
            
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = textColor,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
        }
        
        // Glitch effect overlay when selected
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                NeonYellow.copy(alpha = 0.1f),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}

@Composable
fun GlitchText(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = LightText
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glitchOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    val glitchAlpha by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Box(modifier = modifier) {
        // Main text
        Text(
            text = text,
            style = style,
            color = color
        )
        
        // Red glitch layer
        Text(
            text = text,
            style = style,
            color = CyberpunkRed.copy(alpha = glitchAlpha),
            modifier = Modifier.offset(x = glitchOffset.dp, y = (-glitchOffset/2).dp)
        )
        
        // Blue glitch layer
        Text(
            text = text,
            style = style,
            color = NeonBlue.copy(alpha = glitchAlpha),
            modifier = Modifier.offset(x = (-glitchOffset).dp, y = (glitchOffset/2).dp)
        )
    }
}

@Composable
fun ScanlineOverlay(
    modifier: Modifier = Modifier,
    alpha: Float = 0.1f
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scanlinePosition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        val lineHeight = 4.dp.toPx()
        val screenHeight = size.height
        val currentY = scanlinePosition * screenHeight
        
        // Draw moving scanline
        drawRect(
            color = NeonBlue.copy(alpha = alpha),
            topLeft = Offset(0f, currentY),
            size = androidx.compose.ui.geometry.Size(size.width, lineHeight)
        )
        
        // Draw static scanlines
        for (i in 0 until (screenHeight / (lineHeight * 3)).toInt()) {
            val y = i * lineHeight * 3
            drawRect(
                color = NeonGreen.copy(alpha = alpha * 0.5f),
                topLeft = Offset(0f, y),
                size = androidx.compose.ui.geometry.Size(size.width, 1.dp.toPx())
            )
        }
    }
}

@Composable
fun HexagonalBackground(
    modifier: Modifier = Modifier,
    alpha: Float = 0.05f
) {
    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        val hexSize = 40.dp.toPx()
        val hexWidth = hexSize * 2
        val hexHeight = hexSize * 1.732f
        
        for (row in 0 until (size.height / hexHeight).toInt() + 1) {
            for (col in 0 until (size.width / hexWidth).toInt() + 1) {
                val x = col * hexWidth * 0.75f
                val y = row * hexHeight + (if (col % 2 == 1) hexHeight / 2 else 0f)
                
                drawHexagon(
                    center = Offset(x, y),
                    size = hexSize,
                    color = NeonBlue.copy(alpha = alpha)
                )
            }
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawHexagon(
    center: Offset,
    size: Float,
    color: Color
) {
    val path = androidx.compose.ui.graphics.Path()
    val angles = (0..5).map { it * 60f * kotlin.math.PI / 180f }
    
    angles.forEachIndexed { index, angle ->
        val x = center.x + size * cos(angle).toFloat()
        val y = center.y + size * sin(angle).toFloat()
        
        if (index == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    path.close()
    
    drawPath(
        path = path,
        color = color,
        style = Stroke(width = 1.dp.toPx())
    )
}

@Composable
fun CyberpunkCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkCard.copy(alpha = 0.9f),
                        DarkSurface.copy(alpha = 0.9f)
                    )
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        NeonYellow.copy(alpha = 0.3f),
                        NeonBlue.copy(alpha = 0.3f),
                        NeonPurple.copy(alpha = 0.3f)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        // Hexagonal background
        HexagonalBackground(alpha = 0.02f)
        
        // Content
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
        
        // Scanline overlay
        ScanlineOverlay(alpha = 0.05f)
    }
}