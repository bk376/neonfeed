package com.hackernews.futuristic.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
fun PredictiveFilteringDashboard(
    insights: FilteringInsights,
    predictions: List<ContentPrediction>,
    filteringRules: List<FilteringRule>,
    userPreferences: UserContentPreferences?,
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
    
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Dashboard Header
            PredictiveFilteringHeader(
                insights = insights,
                animationProgress = animationProgress
            )
        }
        
        item {
            // Performance Metrics
            FilteringPerformanceMetrics(
                insights = insights,
                animationProgress = animationProgress
            )
        }
        
        item {
            // Real-time Predictions Visualization
            RealTimePredictionsView(
                predictions = predictions.take(10),
                animationProgress = animationProgress
            )
        }
        
        item {
            // AI Model Status
            AIModelStatusCard(
                modelPerformance = insights.modelPerformance,
                animationProgress = animationProgress
            )
        }
        
        item {
            // Content Trends Analysis
            ContentTrendsAnalysis(
                trends = insights.trendsDetected,
                animationProgress = animationProgress
            )
        }
        
        item {
            // Filtering Rules Overview
            FilteringRulesOverview(
                rules = filteringRules,
                animationProgress = animationProgress
            )
        }
        
        userPreferences?.let { preferences ->
            item {
                // User Preferences Visualization
                UserPreferencesVisualization(
                    preferences = preferences,
                    animationProgress = animationProgress
                )
            }
        }
        
        item {
            // Recommendations Panel
            RecommendationsPanel(
                recommendations = insights.recommendations,
                animationProgress = animationProgress
            )
        }
    }
}

@Composable
private fun PredictiveFilteringHeader(
    insights: FilteringInsights,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            // Background animation
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawNeuralNetworkBackground(animationProgress, size)
            }
            
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "PREDICTIVE_FILTERING_SYSTEM",
                            color = NeonBlue,
                            fontSize = 18.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = "AI-POWERED CONTENT CURATION",
                            color = NeonCyan,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                    
                    // Status indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Canvas(modifier = Modifier.size(12.dp)) {
                            val alpha = 0.6f + 0.4f * sin(animationProgress * 4 * PI).toFloat()
                            drawCircle(
                                color = NeonGreen.copy(alpha = alpha),
                                radius = size.width / 2
                            )
                        }
                        
                        Text(
                            text = "ACTIVE",
                            color = NeonGreen,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                // Key metrics summary
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MetricSummaryItem(
                        label = "ACCURACY",
                        value = "${(insights.filteringAccuracy * 100).toInt()}%",
                        color = NeonGreen,
                        animationProgress = animationProgress
                    )
                    
                    MetricSummaryItem(
                        label = "PROCESSED",
                        value = "${insights.totalStoriesProcessed}",
                        color = NeonBlue,
                        animationProgress = animationProgress
                    )
                    
                    MetricSummaryItem(
                        label = "SATISFACTION",
                        value = "${(insights.userSatisfactionScore * 100).toInt()}%",
                        color = NeonPurple,
                        animationProgress = animationProgress
                    )
                    
                    MetricSummaryItem(
                        label = "AI_PERFORMANCE",
                        value = "${(insights.modelPerformance * 100).toInt()}%",
                        color = NeonYellow,
                        animationProgress = animationProgress
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricSummaryItem(
    label: String,
    value: String,
    color: Color,
    animationProgress: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = value,
            color = color.copy(alpha = 0.8f + 0.2f * sin(animationProgress * 2 * PI).toFloat()),
            fontSize = 16.sp,
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
private fun FilteringPerformanceMetrics(
    insights: FilteringInsights,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "PERFORMANCE_ANALYTICS",
                color = NeonGreen,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Performance visualization
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                drawPerformanceGraph(
                    insights = insights,
                    animationProgress = animationProgress,
                    size = size
                )
            }
            
            // Detailed metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PerformanceMetricCard(
                    label = "FILTERED_OUT",
                    value = "${insights.storiesFilteredOut}",
                    percentage = if (insights.totalStoriesProcessed > 0) {
                        insights.storiesFilteredOut.toFloat() / insights.totalStoriesProcessed
                    } else 0f,
                    color = NeonRed
                )
                
                PerformanceMetricCard(
                    label = "HIGH_ENGAGEMENT",
                    value = "${insights.highEngagementStories}",
                    percentage = if (insights.totalStoriesProcessed > 0) {
                        insights.highEngagementStories.toFloat() / insights.totalStoriesProcessed
                    } else 0f,
                    color = NeonGreen
                )
                
                PerformanceMetricCard(
                    label = "AVG_READ_TIME",
                    value = "${insights.averageReadingTime / 1000}s",
                    percentage = min(insights.averageReadingTime / 300000f, 1f), // Normalize to 5 minutes
                    color = NeonBlue
                )
            }
        }
    }
}

@Composable
private fun PerformanceMetricCard(
    label: String,
    value: String,
    percentage: Float,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
            fontFamily = MonospaceFont,
            textAlign = TextAlign.Center
        )
        
        // Progress indicator
        CircularProgressIndicator(
            progress = { percentage },
            modifier = Modifier.size(32.dp),
            color = color,
            strokeWidth = 3.dp,
            trackColor = color.copy(alpha = 0.2f)
        )
    }
}

@Composable
private fun RealTimePredictionsView(
    predictions: List<ContentPrediction>,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkBackground.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "REAL_TIME_PREDICTIONS",
                    color = NeonPurple,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${predictions.size} ACTIVE",
                    color = NeonCyan,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Predictions list
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(predictions) { prediction ->
                    PredictionCard(
                        prediction = prediction,
                        animationProgress = animationProgress
                    )
                }
            }
        }
    }
}

@Composable
private fun PredictionCard(
    prediction: ContentPrediction,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.width(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkCard.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "STORY_${prediction.storyId}",
                color = NeonBlue,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Relevance score
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "RELEVANCE",
                    color = TextSecondary,
                    fontSize = 8.sp,
                    fontFamily = MonospaceFont
                )
                
                Text(
                    text = "${(prediction.relevanceScore * 100).toInt()}%",
                    color = NeonGreen,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Progress bars
            PredictionMetricBar(
                label = "ENGAGEMENT",
                value = prediction.engagementProbability,
                color = NeonBlue
            )
            
            PredictionMetricBar(
                label = "QUALITY",
                value = prediction.qualityScore,
                color = NeonPurple
            )
            
            PredictionMetricBar(
                label = "NOVELTY",
                value = prediction.noveltyScore,
                color = NeonYellow
            )
            
            // Confidence indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CONFIDENCE",
                    color = TextSecondary,
                    fontSize = 8.sp,
                    fontFamily = MonospaceFont
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    repeat(5) { index ->
                        val isActive = index < (prediction.confidence * 5).toInt()
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .background(
                                    color = if (isActive) NeonCyan else DarkBorder,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PredictionMetricBar(
    label: String,
    value: Float,
    color: Color
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 7.sp,
                fontFamily = MonospaceFont
            )
            
            Text(
                text = "${(value * 100).toInt()}%",
                color = color,
                fontSize = 7.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }
        
        LinearProgressIndicator(
            progress = { value },
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(1.5.dp)),
            color = color,
            trackColor = color.copy(alpha = 0.2f)
        )
    }
}

@Composable
private fun AIModelStatusCard(
    modelPerformance: Float,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = NeonBlue.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = "AI Model",
                        tint = NeonBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    
                    Text(
                        text = "AI_MODEL_STATUS",
                        color = NeonBlue,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = "Neural Network v2.1.0",
                    color = LightText,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
                
                Text(
                    text = "Last trained: 2 hours ago",
                    color = TextSecondary,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Performance gauge
            Box(
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.size(80.dp)
                ) {
                    drawAIPerformanceGauge(
                        performance = modelPerformance,
                        animationProgress = animationProgress,
                        size = size
                    )
                }
                
                Text(
                    text = "${(modelPerformance * 100).toInt()}%",
                    color = NeonBlue,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ContentTrendsAnalysis(
    trends: List<ContentTrend>,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "CONTENT_TRENDS_ANALYSIS",
                color = NeonYellow,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            if (trends.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(trends) { trend ->
                        TrendCard(
                            trend = trend,
                            animationProgress = animationProgress
                        )
                    }
                }
            } else {
                // Mock trends for demonstration
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(generateMockTrends()) { trend ->
                        TrendCard(
                            trend = trend,
                            animationProgress = animationProgress
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrendCard(
    trend: ContentTrend,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.width(140.dp),
        colors = CardDefaults.cardColors(
            containerColor = trend.trendType.color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = trend.topic.uppercase(),
                color = trend.trendType.color,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when(trend.direction) {
                        com.hackernews.futuristic.data.model.TrendDirection.UP -> "↗"
                        com.hackernews.futuristic.data.model.TrendDirection.DOWN -> "↘"
                        com.hackernews.futuristic.data.model.TrendDirection.STABLE -> "→"
                        com.hackernews.futuristic.data.model.TrendDirection.VOLATILE -> "↕"
                    },
                    color = trend.trendType.color,
                    fontSize = 16.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${(trend.strength * 100).toInt()}%",
                    color = trend.trendType.color,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Text(
                text = trend.trendType.displayName,
                color = TextSecondary,
                fontSize = 8.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun FilteringRulesOverview(
    rules: List<FilteringRule>,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FILTERING_RULES",
                    color = NeonGreen,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${rules.count { it.isEnabled }}/${rules.size} ACTIVE",
                    color = NeonCyan,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Rules summary
            rules.take(5).forEach { rule ->
                FilteringRuleItem(
                    rule = rule,
                    animationProgress = animationProgress
                )
            }
        }
    }
}

@Composable
private fun FilteringRuleItem(
    rule: FilteringRule,
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
            // Status indicator
            Canvas(modifier = Modifier.size(8.dp)) {
                val color = if (rule.isEnabled) NeonGreen else DarkBorder
                drawCircle(color = color, radius = size.width / 2)
            }
            
            Text(
                text = rule.name,
                color = if (rule.isEnabled) LightText else TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
        }
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = rule.action.displayName.uppercase(),
                color = rule.action.color,
                fontSize = 8.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "P${rule.priority}",
                color = TextSecondary,
                fontSize = 8.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun UserPreferencesVisualization(
    preferences: UserContentPreferences,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = NeonPurple.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "USER_PREFERENCES_PROFILE",
                color = NeonPurple,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Topic preferences
            Text(
                text = "PREFERRED_TOPICS",
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(preferences.preferredTopics.take(6)) { topic ->
                    val weight = preferences.topicWeights[topic] ?: 0.5f
                    
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = NeonPurple.copy(alpha = weight * 0.3f)
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = topic.uppercase(),
                            color = NeonPurple,
                            fontSize = 8.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
            }
            
            // Preference metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PreferenceMetric(
                    label = "DIFFICULTY",
                    value = preferences.difficultyPreference,
                    color = NeonBlue
                )
                
                PreferenceMetric(
                    label = "NOVELTY",
                    value = preferences.noveltyPreference,
                    color = NeonGreen
                )
                
                PreferenceMetric(
                    label = "QUALITY",
                    value = preferences.qualityThreshold,
                    color = NeonYellow
                )
                
                PreferenceMetric(
                    label = "VIRALITY",
                    value = preferences.viralityInterest,
                    color = NeonCyan
                )
            }
        }
    }
}

@Composable
private fun PreferenceMetric(
    label: String,
    value: Float,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "${(value * 100).toInt()}%",
            color = color,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 7.sp,
            fontFamily = MonospaceFont
        )
        
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(4.dp)
                .background(color.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(value)
                    .background(color, RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
private fun RecommendationsPanel(
    recommendations: List<String>,
    animationProgress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = NeonCyan.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = "Recommendations",
                    tint = NeonCyan,
                    modifier = Modifier.size(16.dp)
                )
                
                Text(
                    text = "AI_RECOMMENDATIONS",
                    color = NeonCyan,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
            
            recommendations.forEach { recommendation ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        color = NeonCyan,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont
                    )
                    
                    Text(
                        text = recommendation,
                        color = LightText,
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

// Helper functions for drawing
private fun DrawScope.drawNeuralNetworkBackground(progress: Float, size: Size) {
    val nodeCount = 20
    val nodes = (0 until nodeCount).map {
        val angle = (it.toFloat() / nodeCount) * 2 * PI
        val radius = minOf(size.width, size.height) / 4
        val x = size.width / 2 + cos(angle).toFloat() * radius
        val y = size.height / 2 + sin(angle).toFloat() * radius
        Offset(x, y)
    }
    
    // Draw connections
    nodes.forEachIndexed { i, node1 ->
        nodes.forEachIndexed { j, node2 ->
            if (i < j && (i + j) % 3 == 0) {
                val alpha = 0.1f + 0.05f * sin(progress * 2 * PI + i + j).toFloat()
                drawLine(
                    color = NeonBlue.copy(alpha = alpha),
                    start = node1,
                    end = node2,
                    strokeWidth = 1.dp.toPx()
                )
            }
        }
    }
    
    // Draw nodes
    nodes.forEachIndexed { i, node ->
        val alpha = 0.3f + 0.3f * sin(progress * 2 * PI + i * 0.5f).toFloat()
        drawCircle(
            color = NeonBlue.copy(alpha = alpha),
            radius = 3.dp.toPx(),
            center = node
        )
    }
}

private fun DrawScope.drawPerformanceGraph(
    insights: FilteringInsights,
    animationProgress: Float,
    size: Size
) {
    val dataPoints = listOf(
        insights.filteringAccuracy,
        insights.userSatisfactionScore,
        insights.modelPerformance,
        if (insights.totalStoriesProcessed > 0) {
            insights.highEngagementStories.toFloat() / insights.totalStoriesProcessed
        } else 0f
    )
    
    val stepWidth = size.width / (dataPoints.size - 1)
    val path = Path()
    
    dataPoints.forEachIndexed { index, value ->
        val x = index * stepWidth
        val y = size.height - (value * size.height)
        
        if (index == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
        
        // Draw data points
        drawCircle(
            color = NeonGreen,
            radius = 4.dp.toPx(),
            center = Offset(x, y)
        )
    }
    
    // Draw the line
    drawPath(
        path = path,
        color = NeonGreen,
        style = Stroke(width = 2.dp.toPx())
    )
}

private fun DrawScope.drawAIPerformanceGauge(
    performance: Float,
    animationProgress: Float,
    size: Size
) {
    val center = Offset(size.width / 2, size.height / 2)
    val radius = minOf(size.width, size.height) / 3
    
    // Background arc
    drawArc(
        color = DarkBorder,
        startAngle = 180f,
        sweepAngle = 180f,
        useCenter = false,
        style = Stroke(width = 6.dp.toPx()),
        size = Size(radius * 2, radius * 2),
        topLeft = Offset(center.x - radius, center.y - radius)
    )
    
    // Performance arc
    val sweepAngle = 180f * performance
    drawArc(
        color = NeonBlue.copy(alpha = 0.8f + 0.2f * sin(animationProgress * 2 * PI).toFloat()),
        startAngle = 180f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(width = 6.dp.toPx()),
        size = Size(radius * 2, radius * 2),
        topLeft = Offset(center.x - radius, center.y - radius)
    )
}

private fun generateMockTrends(): List<ContentTrend> {
    return listOf(
        ContentTrend(
            trendType = TrendType.EMERGING_TOPIC,
            topic = "AI Safety",
            strength = 0.8f,
            direction = com.hackernews.futuristic.data.model.TrendDirection.UP,
            timeframe = "24h",
            confidence = 0.9f
        ),
        ContentTrend(
            trendType = TrendType.VIRAL_CONTENT,
            topic = "Quantum Computing",
            strength = 0.9f,
            direction = com.hackernews.futuristic.data.model.TrendDirection.VOLATILE,
            timeframe = "12h",
            confidence = 0.85f
        ),
        ContentTrend(
            trendType = TrendType.DECLINING_INTEREST,
            topic = "Cryptocurrency",
            strength = 0.3f,
            direction = com.hackernews.futuristic.data.model.TrendDirection.DOWN,
            timeframe = "7d",
            confidence = 0.7f
        )
    )
}