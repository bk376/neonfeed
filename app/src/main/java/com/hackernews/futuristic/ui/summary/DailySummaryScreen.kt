package com.hackernews.futuristic.ui.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.components.GlowingCard
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailySummaryScreen(
    onBackClick: () -> Unit,
    onStoryClick: (Story) -> Unit,
    onScrollToTopCallback: ((suspend () -> Unit) -> Unit)? = null,
    viewModel: DailySummaryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    
    // Register scroll-to-top callback
    LaunchedEffect(onScrollToTopCallback) {
        onScrollToTopCallback?.invoke {
            listState.animateScrollToItem(0)
        }
    }
    
    LaunchedEffect(Unit) {
        viewModel.generateDailySummary()
    }
    
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
        TopAppBar(
            title = { 
                Text(
                    "DAILY NEURAL SYNTHESIS",
                    fontFamily = MonospaceFont,
                    color = NeonBlue
                ) 
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = LightText
                    )
                }
            },
            actions = {
                IconButton(onClick = { viewModel.shareSummary() }) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Share Summary",
                        tint = NeonGreen
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkSurface,
                titleContentColor = LightText
            )
        )
        
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CircularProgressIndicator(color = NeonBlue)
                        Text(
                            text = "SYNTHESIZING NEURAL PATTERNS...",
                            color = NeonBlue,
                            fontFamily = MonospaceFont,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            color = ErrorRed,
                            fontFamily = MonospaceFont
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = viewModel::generateDailySummary,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NeonBlue
                            )
                        ) {
                            Text("RETRY SYNTHESIS", fontFamily = MonospaceFont)
                        }
                    }
                }
            }
            uiState.summary != null -> {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        DailySummaryHeader(summary = uiState.summary!!)
                    }
                    
                    item {
                        TopStoriesSection(
                            stories = uiState.summary!!.topStories,
                            onStoryClick = onStoryClick
                        )
                    }
                    
                    item {
                        TrendingTopicsSection(topics = uiState.summary!!.trendingTopics)
                    }
                    
                    item {
                        AppOpportunitySection(
                            opportunity = uiState.summary!!.businessOpportunity,
                            onAcceptClick = { viewModel.acceptBusinessOpportunity() }
                        )
                    }
                    
                    item {
                        TalentScoutingSection(
                            talentRequirements = uiState.summary!!.businessOpportunity.talentRequirements
                        )
                    }
                    
                    item {
                        SynthesisMetricsSection(metrics = uiState.summary!!.synthesisMetrics)
                    }
                }
            }
        }
    }
}

@Composable
private fun DailySummaryHeader(summary: DailySummary) {
    GlowingCard(
        modifier = Modifier.fillMaxWidth(),
        glowColor = NeonBlue
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NEURAL SYNTHESIS REPORT",
                    color = NeonBlue,
                    fontSize = 18.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = NeonGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${summary.totalStories} STORIES",
                        color = NeonGreen,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Text(
                text = summary.date,
                color = TextSecondary,
                fontSize = 14.sp,
                fontFamily = MonospaceFont
            )
            
            Text(
                text = summary.executiveSummary,
                color = LightText,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun TopStoriesSection(
    stories: List<Story>,
    onStoryClick: (Story) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "TOP NEURAL STORIES",
                color = NeonGreen,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            stories.forEachIndexed { index, story ->
                TopStoryCard(
                    story = story,
                    rank = index + 1,
                    onClick = { onStoryClick(story) }
                )
            }
        }
    }
}

@Composable
private fun TopStoryCard(
    story: Story,
    rank: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        when (rank) {
                            1 -> NeonGold
                            2 -> NeonBlue
                            3 -> NeonPurple
                            else -> NeonGreen
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rank.toString(),
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = story.title ?: "No title",
                    color = LightText,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚡ ${story.score ?: 0}",
                        color = NeonYellow,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                    
                    Text(
                        text = "💬 ${story.descendants ?: 0}",
                        color = NeonPurple,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont
                    )
                    
                    story.by?.let { author ->
                        Text(
                            text = "👤 $author",
                            color = TextSecondary,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrendingTopicsSection(topics: List<TrendingTopic>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "TRENDING NEURAL PATTERNS",
                color = NeonPurple,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            topics.forEach { topic ->
                TrendingTopicCard(topic = topic)
            }
        }
    }
}

@Composable
private fun TrendingTopicCard(topic: TrendingTopic) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = topic.name,
                    color = NeonPurple,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = topic.description,
                    color = LightText,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "${topic.mentions} mentions",
                    color = NeonGreen,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                Text(
                    text = "${(topic.growthRate * 100).toInt()}% growth",
                    color = if (topic.growthRate > 0) NeonGreen else NeonRed,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
            }
        }
    }
}

@Composable
private fun AppOpportunitySection(
    opportunity: BusinessOpportunity,
    onAcceptClick: () -> Unit
) {
    GlowingCard(
        modifier = Modifier.fillMaxWidth(),
        glowColor = NeonGold
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🚀 APP OPPORTUNITY DETECTED",
                    color = NeonGold,
                    fontSize = 16.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = NeonGold,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            // App Details
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = opportunity.appOpportunity.appName.uppercase(),
                    color = NeonGold,
                    fontSize = 20.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = opportunity.description,
                    color = LightText,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    lineHeight = 20.sp
                )
                
                Text(
                    text = "App Type: ${opportunity.appOpportunity.appType}",
                    color = NeonBlue,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Market & Revenue
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "MARKET POTENTIAL",
                        color = NeonGreen,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = opportunity.marketPotential,
                        color = LightText,
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont
                    )
                }
                
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "REVENUE ESTIMATE",
                        color = NeonGold,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = opportunity.revenueEstimate,
                        color = NeonGold,
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            // Core Features
            Text(
                text = "CORE FEATURES",
                color = NeonBlue,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            opportunity.appOpportunity.coreFeatures.forEach { feature ->
                Text(
                    text = "• $feature",
                    color = LightText,
                    fontSize = 11.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Unique Value Proposition
            Text(
                text = "UNIQUE VALUE",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = opportunity.appOpportunity.uniqueValue,
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
            
            // Technical Stack
            Text(
                text = "TECHNICAL STACK",
                color = NeonGreen,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            opportunity.appOpportunity.technicalStack.take(3).forEach { tech ->
                Text(
                    text = "• $tech",
                    color = LightText,
                    fontSize = 11.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Development Timeline
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "DEVELOPMENT TIME",
                        color = NeonRed,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = opportunity.appOpportunity.developmentTime,
                        color = LightText,
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont
                    )
                }
                
                Button(
                    onClick = onAcceptClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (opportunity.isAccepted) NeonGreen else NeonGold,
                        contentColor = androidx.compose.ui.graphics.Color.Black
                    )
                ) {
                    Text(
                        text = if (opportunity.isAccepted) "✓ ACCEPTED" else "SCOUT THIS APP",
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun TalentScoutingSection(
    talentRequirements: TalentRequirements
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "👥 TALENT SCOUTING REQUIREMENTS",
                color = NeonPurple,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            // Team Overview
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "TEAM SIZE",
                        color = NeonGreen,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${talentRequirements.totalTeamSize} people",
                        color = LightText,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont
                    )
                }
                
                Column {
                    Text(
                        text = "TOTAL COST",
                        color = NeonGold,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = talentRequirements.estimatedCost,
                        color = NeonGold,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Column {
                    Text(
                        text = "TIME TO HIRE",
                        color = NeonBlue,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = talentRequirements.timeToHire,
                        color = LightText,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }
            
            // Key Roles
            Text(
                text = "KEY ROLES TO SCOUT",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            talentRequirements.roles.forEach { role ->
                TalentRoleCard(role = role)
            }
            
            // Key Skills Summary
            Text(
                text = "CRITICAL SKILLS",
                color = NeonGreen,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(talentRequirements.keySkills) { skill ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = DarkSurface),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = skill,
                            color = NeonGreen,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TalentRoleCard(role: TalentRole) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = role.title,
                    color = NeonPurple,
                    fontSize = 14.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = role.priority.uppercase(),
                        color = when (role.priority) {
                            "Critical" -> NeonRed
                            "High" -> NeonYellow
                            else -> NeonGreen
                        },
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    if (role.remoteOption) {
                        Text(
                            text = "🌐 REMOTE",
                            color = NeonBlue,
                            fontSize = 9.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
            }
            
            Text(
                text = role.description,
                color = LightText,
                fontSize = 11.sp,
                fontFamily = MonospaceFont
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "💰 ${role.salaryRange}",
                    color = NeonGold,
                    fontSize = 11.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = role.skills.joinToString(", "),
                    color = TextSecondary,
                    fontSize = 9.sp,
                    fontFamily = MonospaceFont,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun SynthesisMetricsSection(metrics: SynthesisMetrics) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "SYNTHESIS METRICS",
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
                    label = "NEURAL COHERENCE",
                    value = "${(metrics.neuralCoherence * 100).toInt()}%",
                    color = NeonGreen
                )
                
                MetricItem(
                    label = "SYNTHESIS QUALITY",
                    value = "${(metrics.synthesisQuality * 100).toInt()}%",
                    color = NeonBlue
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricItem(
                    label = "PATTERN STRENGTH",
                    value = "${(metrics.patternStrength * 100).toInt()}%",
                    color = NeonPurple
                )
                
                MetricItem(
                    label = "BUSINESS VIABILITY",
                    value = "${(metrics.businessViability * 100).toInt()}%",
                    color = NeonGold
                )
            }
        }
    }
}

@Composable
private fun MetricItem(
    label: String,
    value: String,
    color: androidx.compose.ui.graphics.Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = value,
            color = color,
            fontSize = 18.sp,
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