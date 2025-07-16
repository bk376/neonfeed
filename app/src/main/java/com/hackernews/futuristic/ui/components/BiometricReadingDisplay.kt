package com.hackernews.futuristic.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.*

@Composable
fun BiometricReadingDisplay(
    biometricData: BiometricReadingData?,
    readingSession: ReadingSession?,
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
    
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            BiometricHeader(animationProgress = animationProgress)
            
            biometricData?.let { data ->
                // Real-time metrics
                BiometricMetricsGrid(
                    data = data,
                    animationProgress = animationProgress
                )
                
                // Vital signs visualization
                VitalSignsVisualization(
                    data = data,
                    animationProgress = animationProgress
                )
                
                // Cognitive state indicators
                CognitiveStateIndicators(
                    data = data,
                    animationProgress = animationProgress
                )
            }
            
            readingSession?.let { session ->
                // Session summary
                ReadingSessionSummary(
                    session = session,
                    animationProgress = animationProgress
                )
            }
        }
    }
}

@Composable
private fun BiometricHeader(
    animationProgress: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Pulsing indicator
            Canvas(
                modifier = Modifier.size(16.dp)
            ) {
                val pulse = 0.5f + 0.5f * sin(animationProgress * 4 * PI).toFloat()
                drawCircle(
                    color = NeonGreen,
                    radius = size.width / 4 * pulse,
                    center = center
                )
            }
            
            Text(
                text = "BIOMETRIC_MONITORING",
                color = NeonGreen,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }
        
        Text(
            text = "ACTIVE",
            color = NeonGreen,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun BiometricMetricsGrid(
    data: BiometricReadingData,
    animationProgress: Float
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // First row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BiometricMetricCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Favorite,
                label = "HEART_RATE",
                value = "${data.heartRate}",
                unit = "BPM",
                color = NeonRed,
                progress = data.heartRate / 120f,
                animationProgress = animationProgress
            )
            
            BiometricMetricCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Visibility,
                label = "FOCUS",
                value = "${(data.focusIntensity * 100).toInt()}",
                unit = "%",
                color = NeonBlue,
                progress = data.focusIntensity,
                animationProgress = animationProgress
            )
        }
        
        // Second row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BiometricMetricCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Psychology,
                label = "COMPREHENSION",
                value = "${(data.comprehensionLevel * 100).toInt()}",
                unit = "%",
                color = NeonPurple,
                progress = data.comprehensionLevel,
                animationProgress = animationProgress
            )
            
            BiometricMetricCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Warning,
                label = "STRESS",
                value = "${(data.stressLevel * 100).toInt()}",
                unit = "%",
                color = NeonYellow,
                progress = data.stressLevel,
                animationProgress = animationProgress
            )
        }
    }
}

@Composable
private fun BiometricMetricCard(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    unit: String,
    color: Color,
    progress: Float,
    animationProgress: Float
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Icon with glow effect
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color.copy(alpha = 0.8f + 0.2f * sin(animationProgress * 2 * PI).toFloat()),
                modifier = Modifier.size(20.dp)
            )
            
            // Value
            Text(
                text = value,
                color = color,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Unit and label
            Text(
                text = unit,
                color = color.copy(alpha = 0.7f),
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
            
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 8.sp,
                fontFamily = MonospaceFont,
                textAlign = TextAlign.Center
            )
            
            // Progress indicator
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = color,
                trackColor = color.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
private fun VitalSignsVisualization(
    data: BiometricReadingData,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "VITAL_SIGNS_ANALYSIS",
                color = NeonCyan,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Heart rate waveform
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                drawHeartRateWaveform(
                    heartRate = data.heartRate,
                    animationProgress = animationProgress,
                    size = size
                )
            }
            
            // Brainwave pattern
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                drawBrainwavePattern(
                    focusIntensity = data.focusIntensity,
                    animationProgress = animationProgress,
                    size = size
                )
            }
        }
    }
}

@Composable
private fun CognitiveStateIndicators(
    data: BiometricReadingData,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkBackground.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "COGNITIVE_STATE",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Cognitive load indicator
            CognitiveLoadIndicator(
                cognitiveLoad = data.cognitiveLoad,
                animationProgress = animationProgress
            )
            
            // Interest level radar
            InterestLevelRadar(
                interestLevel = data.interestLevel,
                comprehensionLevel = data.comprehensionLevel,
                animationProgress = animationProgress
            )
        }
    }
}

@Composable
private fun CognitiveLoadIndicator(
    cognitiveLoad: Float,
    animationProgress: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "COGNITIVE_LOAD",
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(5) { index ->
                val isActive = index < (cognitiveLoad * 5).toInt()
                val color = when {
                    index < 2 -> NeonGreen
                    index < 4 -> NeonYellow
                    else -> NeonRed
                }
                
                Box(
                    modifier = Modifier
                        .size(12.dp, 20.dp)
                        .background(
                            color = if (isActive) {
                                color.copy(alpha = 0.8f + 0.2f * sin(animationProgress * 2 * PI).toFloat())
                            } else {
                                color.copy(alpha = 0.2f)
                            },
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    }
}

@Composable
private fun InterestLevelRadar(
    interestLevel: Float,
    comprehensionLevel: Float,
    animationProgress: Float
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = minOf(size.width, size.height) / 3
        
        // Draw radar grid
        for (i in 1..3) {
            drawCircle(
                color = NeonPurple.copy(alpha = 0.3f),
                radius = radius * i / 3,
                center = center,
                style = Stroke(width = 1.dp.toPx())
            )
        }
        
        // Draw interest level
        val interestRadius = radius * interestLevel
        drawCircle(
            color = NeonBlue.copy(alpha = 0.5f),
            radius = interestRadius,
            center = center
        )
        
        // Draw comprehension level
        val comprehensionRadius = radius * comprehensionLevel
        drawCircle(
            color = NeonPurple.copy(alpha = 0.3f),
            radius = comprehensionRadius,
            center = center,
            style = Stroke(width = 3.dp.toPx())
        )
    }
}

@Composable
private fun ReadingSessionSummary(
    session: ReadingSession,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = NeonGreen.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "SESSION_SUMMARY",
                color = NeonGreen,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SessionMetric(
                    label = "DURATION",
                    value = formatDuration(session.totalReadingTime),
                    color = NeonGreen
                )
                
                SessionMetric(
                    label = "AVG_FOCUS",
                    value = "${(session.cognitiveMetrics.averageFocusIntensity * 100).toInt()}%",
                    color = NeonBlue
                )
                
                SessionMetric(
                    label = "EFFICIENCY",
                    value = "${(session.cognitiveMetrics.cognitiveEfficiency * 100).toInt()}%",
                    color = NeonPurple
                )
            }
        }
    }
}

@Composable
private fun SessionMetric(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
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

private fun DrawScope.drawHeartRateWaveform(
    heartRate: Int,
    animationProgress: Float,
    size: Size
) {
    val path = Path()
    val amplitude = size.height / 4
    val frequency = heartRate / 60f
    val waveLength = size.width / 3
    
    for (x in 0 until size.width.toInt()) {
        val normalizedX = x / size.width
        val wave = sin(normalizedX * frequency * 2 * PI + animationProgress * 2 * PI) * amplitude
        val y = size.height / 2 + wave.toFloat()
        
        if (x == 0) {
            path.moveTo(x.toFloat(), y)
        } else {
            path.lineTo(x.toFloat(), y)
        }
    }
    
    drawPath(
        path = path,
        color = NeonRed,
        style = Stroke(width = 3.dp.toPx())
    )
}

private fun DrawScope.drawBrainwavePattern(
    focusIntensity: Float,
    animationProgress: Float,
    size: Size
) {
    val path = Path()
    val amplitude = size.height / 6 * focusIntensity
    val frequency = 8f // Alpha waves
    
    for (x in 0 until size.width.toInt()) {
        val normalizedX = x / size.width
        val wave = sin(normalizedX * frequency * 2 * PI + animationProgress * 2 * PI) * amplitude
        val y = size.height / 2 + wave.toFloat()
        
        if (x == 0) {
            path.moveTo(x.toFloat(), y)
        } else {
            path.lineTo(x.toFloat(), y)
        }
    }
    
    drawPath(
        path = path,
        color = NeonBlue,
        style = Stroke(width = 2.dp.toPx())
    )
}

private fun formatDuration(milliseconds: Long): String {
    val seconds = milliseconds / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    
    return when {
        hours > 0 -> "${hours}h ${minutes % 60}m"
        minutes > 0 -> "${minutes}m ${seconds % 60}s"
        else -> "${seconds}s"
    }
}