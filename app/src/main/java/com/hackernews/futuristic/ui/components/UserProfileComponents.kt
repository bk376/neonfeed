package com.hackernews.futuristic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun UserProfileCard(
    profile: UserProfile,
    modifier: Modifier = Modifier
) {
    GlowingCard(
        modifier = modifier,
        glowColor = NeonBlue
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header with user info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = profile.username,
                        color = NeonBlue,
                        fontSize = 18.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "LEVEL ${profile.level}",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont
                    )
                }
                
                ReputationScoreDisplay(
                    score = profile.reputationScore,
                    level = profile.level
                )
            }
            
            // Quick stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(
                    label = "STORIES",
                    value = profile.readingStats.totalStoriesRead.toString(),
                    color = NeonGreen
                )
                StatItem(
                    label = "HOURS",
                    value = String.format("%.1f", profile.readingStats.totalReadingTime / 60f),
                    color = NeonPurple
                )
                StatItem(
                    label = "STREAK",
                    value = profile.readingStats.currentReadingStreak.toString(),
                    color = NeonYellow
                )
            }
            
            // Engagement style
            EngagementStyleIndicator(
                engagementStyle = profile.userDNA.engagementStyle
            )
        }
    }
}

@Composable
private fun ReputationScoreDisplay(
    score: Float,
    level: Int
) {
    val animatedScore by animateFloatAsState(
        targetValue = score,
        animationSpec = tween(1000, easing = EaseOutCubic)
    )
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = String.format("%.0f", animatedScore),
            color = NeonYellow,
            fontSize = 20.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "REPUTATION",
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun StatItem(
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
            fontSize = 16.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun EngagementStyleIndicator(
    engagementStyle: EngagementStyle
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                engagementStyle.color.copy(alpha = 0.1f),
                RoundedCornerShape(4.dp)
            )
            .border(
                1.dp,
                engagementStyle.color,
                RoundedCornerShape(4.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(engagementStyle.color, CircleShape)
        )
        
        Text(
            text = "ENGAGEMENT: ${engagementStyle.displayName}",
            color = engagementStyle.color,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SkillTreeVisualization(
    skillTrees: List<SkillTree>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(skillTrees) { skillTree ->
            SkillTreeCard(skillTree = skillTree)
        }
    }
}

@Composable
private fun SkillTreeCard(
    skillTree: SkillTree
) {
    GlowingCard(
        glowColor = skillTree.category.color
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = skillTree.category.icon,
                        fontSize = 20.sp
                    )
                    Text(
                        text = skillTree.category.displayName,
                        color = skillTree.category.color,
                        fontSize = 16.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = "LVL ${skillTree.level}",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Progress bar
            SkillProgressBar(
                progress = skillTree.progressPercentage,
                color = skillTree.category.color,
                experience = skillTree.experience,
                maxExperience = skillTree.maxExperience
            )
            
            // Skills
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(skillTree.skills) { skill ->
                    SkillNode(skill = skill)
                }
            }
        }
    }
}

@Composable
private fun SkillProgressBar(
    progress: Float,
    color: Color,
    experience: Float,
    maxExperience: Float
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseOutCubic)
    )
    
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "EXPERIENCE",
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
            Text(
                text = "${experience.toInt()} / ${maxExperience.toInt()}",
                color = color,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
        }
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(DarkSurface, RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                color.copy(alpha = 0.6f),
                                color
                            )
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

@Composable
private fun SkillNode(
    skill: Skill
) {
    val nodeColor = if (skill.isUnlocked) skill.category.color else TextSecondary
    
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                if (skill.isUnlocked) {
                    nodeColor.copy(alpha = 0.2f)
                } else {
                    DarkSurface
                },
                CircleShape
            )
            .border(
                2.dp,
                nodeColor,
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = skill.level.toString(),
            color = nodeColor,
            fontSize = 14.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AchievementGrid(
    achievements: List<Achievement>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(achievements) { achievement ->
            AchievementCard(achievement = achievement)
        }
    }
}

@Composable
private fun AchievementCard(
    achievement: Achievement
) {
    val cardColor = if (achievement.isUnlocked) {
        achievement.rarity.color
    } else {
        TextSecondary
    }
    
    GlowingCard(
        glowColor = cardColor.copy(alpha = if (achievement.isUnlocked) 0.8f else 0.3f)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Achievement icon/badge
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        if (achievement.isUnlocked) {
                            cardColor.copy(alpha = 0.2f)
                        } else {
                            DarkSurface
                        },
                        CircleShape
                    )
                    .border(2.dp, cardColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (achievement.isUnlocked) "✓" else "?",
                    color = cardColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Achievement info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = achievement.title,
                        color = if (achievement.isUnlocked) cardColor else TextSecondary,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Box(
                        modifier = Modifier
                            .background(
                                achievement.rarity.color.copy(alpha = 0.2f),
                                RoundedCornerShape(4.dp)
                            )
                            .border(
                                1.dp,
                                achievement.rarity.color,
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = achievement.rarity.displayName,
                            color = achievement.rarity.color,
                            fontSize = 8.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
                
                Text(
                    text = achievement.description,
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
                
                // Progress bar for incomplete achievements
                if (!achievement.isUnlocked) {
                    AchievementProgressBar(
                        progress = achievement.progressPercentage,
                        color = cardColor
                    )
                }
            }
        }
    }
}

@Composable
private fun AchievementProgressBar(
    progress: Float,
    color: Color
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseOutCubic)
    )
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(DarkSurface, RoundedCornerShape(2.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress)
                .fillMaxHeight()
                .background(color, RoundedCornerShape(2.dp))
        )
    }
}

@Composable
fun UserDNAVisualization(
    userDNA: UserDNA,
    modifier: Modifier = Modifier
) {
    GlowingCard(
        modifier = modifier,
        glowColor = NeonPurple
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "USER DNA ANALYSIS",
                color = NeonPurple,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Topic interests radar chart
            TopicInterestsRadar(
                topicsOfInterest = userDNA.topicsOfInterest,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            
            // Reading patterns
            ReadingPatternsDisplay(
                readingPatterns = userDNA.readingPatterns
            )
            
            // Activity heatmap
            ActivityHeatmap(
                activityHeatmap = userDNA.activityHeatmap,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }
    }
}

@Composable
private fun TopicInterestsRadar(
    topicsOfInterest: Map<Topic, Float>,
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
    
    Canvas(modifier = modifier) {
        drawRadarChart(
            topicsOfInterest = topicsOfInterest,
            animationProgress = animationProgress,
            size = size
        )
    }
}

private fun DrawScope.drawRadarChart(
    topicsOfInterest: Map<Topic, Float>,
    animationProgress: Float,
    size: androidx.compose.ui.geometry.Size
) {
    val center = Offset(size.width / 2f, size.height / 2f)
    val radius = minOf(size.width, size.height) / 2f - 20.dp.toPx()
    
    val topics = topicsOfInterest.keys.toList()
    val angleStep = 2 * Math.PI / topics.size
    
    // Draw grid circles
    for (i in 1..5) {
        val gridRadius = radius * (i / 5f)
        drawCircle(
            color = DarkBorder,
            radius = gridRadius,
            center = center,
            style = Stroke(width = 1.dp.toPx())
        )
    }
    
    // Draw axes
    topics.forEachIndexed { index, topic ->
        val angle = index * angleStep - Math.PI / 2
        val endPoint = Offset(
            center.x + (radius * cos(angle)).toFloat(),
            center.y + (radius * sin(angle)).toFloat()
        )
        
        drawLine(
            color = DarkBorder,
            start = center,
            end = endPoint,
            strokeWidth = 1.dp.toPx()
        )
        
        // Draw topic labels
        drawContext.canvas.nativeCanvas.drawText(
            topic.displayName,
            endPoint.x,
            endPoint.y - 10.dp.toPx(),
            android.graphics.Paint().apply {
                color = topic.color.toArgb()
                textSize = 24f
                textAlign = android.graphics.Paint.Align.CENTER
                typeface = android.graphics.Typeface.MONOSPACE
            }
        )
    }
    
    // Draw data polygon
    val dataPoints = topics.mapIndexed { index, topic ->
        val angle = index * angleStep - Math.PI / 2
        val interest = topicsOfInterest[topic] ?: 0f
        val dataRadius = radius * interest
        
        Offset(
            center.x + (dataRadius * cos(angle)).toFloat(),
            center.y + (dataRadius * sin(angle)).toFloat()
        )
    }
    
    if (dataPoints.isNotEmpty()) {
        val path = Path()
        path.moveTo(dataPoints[0].x, dataPoints[0].y)
        
        for (i in 1 until dataPoints.size) {
            path.lineTo(dataPoints[i].x, dataPoints[i].y)
        }
        path.close()
        
        // Fill
        drawPath(
            path = path,
            color = NeonPurple.copy(alpha = 0.3f)
        )
        
        // Stroke
        drawPath(
            path = path,
            color = NeonPurple,
            style = Stroke(width = 2.dp.toPx())
        )
        
        // Draw data points
        dataPoints.forEach { point ->
            drawCircle(
                color = NeonPurple,
                radius = 4.dp.toPx(),
                center = point
            )
        }
    }
}

@Composable
private fun ReadingPatternsDisplay(
    readingPatterns: ReadingPattern
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "READING PATTERNS",
            color = TextSecondary,
            fontSize = 12.sp,
            fontFamily = MonospaceFont
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PatternItem(
                label = "AVG TIME",
                value = "${readingPatterns.averageReadingTime.toInt()}m",
                color = NeonBlue
            )
            PatternItem(
                label = "SPEED",
                value = readingPatterns.readingSpeed.displayName,
                color = NeonGreen
            )
            PatternItem(
                label = "COMPLETION",
                value = "${(readingPatterns.completionRate * 100).toInt()}%",
                color = NeonYellow
            )
        }
    }
}

@Composable
private fun PatternItem(
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
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun ActivityHeatmap(
    activityHeatmap: List<ActivityHour>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(activityHeatmap) { activityHour ->
            val intensity = activityHour.activityLevel
            val color = NeonGreen.copy(alpha = intensity)
            
            Box(
                modifier = Modifier
                    .width(12.dp)
                    .fillMaxHeight()
                    .background(color, RoundedCornerShape(2.dp))
            )
        }
    }
}