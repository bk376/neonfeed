package com.hackernews.futuristic.data.service

import com.hackernews.futuristic.data.model.AppOpportunity
import com.hackernews.futuristic.data.model.BusinessOpportunity
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.model.TrendingTopic
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.StoryAnalysis
import com.hackernews.futuristic.data.model.DailySummary
import com.hackernews.futuristic.data.config.ClaudeConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

data class ClaudeRequest(
    val model: String = ClaudeConfig.MODEL,
    val max_tokens: Int = ClaudeConfig.MAX_TOKENS,
    val messages: List<ClaudeMessage>
)

data class ClaudeMessage(
    val role: String,
    val content: String
)

data class ClaudeResponse(
    val id: String,
    val type: String,
    val role: String,
    val content: List<ClaudeContent>
)

data class ClaudeContent(
    val type: String,
    val text: String
)

@Singleton
class ClaudeApiService @Inject constructor(
    private val gson: Gson
) {
    companion object {
        private const val CLAUDE_API_URL = ClaudeConfig.BASE_URL
        private const val CLAUDE_VERSION = ClaudeConfig.VERSION
    }
    
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
    
    suspend fun generateAppDevelopmentPlan(
        opportunity: BusinessOpportunity,
        trendingTopics: List<TrendingTopic>,
        relatedStories: List<Story>
    ): AppDevelopmentPlan {
        
        val prompt = buildAppDevelopmentPrompt(opportunity, trendingTopics, relatedStories)
        
        // Check if should use mock mode
        if (ClaudeConfig.shouldUseMockMode()) {
            return createFallbackAppDevelopmentPlan(opportunity)
        }
        
        try {
            val response = callClaudeApi(prompt)
            return parseAppDevelopmentResponse(response, opportunity)
        } catch (e: Exception) {
            // Fallback to mock service if API call fails
            return createFallbackAppDevelopmentPlan(opportunity)
        }
    }
    
    suspend fun generateStoryAnalysis(story: Story): StoryAnalysis {
        val prompt = buildStoryAnalysisPrompt(story)
        
        if (ClaudeConfig.shouldUseMockMode()) {
            return createFallbackStoryAnalysis(story)
        }
        
        try {
            val response = callClaudeApi(prompt)
            return parseStoryAnalysisResponse(response, story)
        } catch (e: Exception) {
            // Fallback to mock analysis
            return createFallbackStoryAnalysis(story)
        }
    }
    
    suspend fun generateDailySummary(stories: List<Story>): DailySummary {
        val prompt = buildDailySummaryPrompt(stories)
        
        if (ClaudeConfig.shouldUseMockMode()) {
            return createFallbackDailySummary(stories)
        }
        
        try {
            val response = callClaudeApi(prompt)
            return parseDailySummaryResponse(response, stories)
        } catch (e: Exception) {
            // Fallback to mock summary
            return createFallbackDailySummary(stories)
        }
    }
    
    suspend fun generateCommentInsights(comments: List<Comment>): String {
        val prompt = buildCommentInsightsPrompt(comments)
        
        if (ClaudeConfig.shouldUseMockMode()) {
            return "Running in mock mode. Enable Claude API for real AI insights, or enjoy the intelligent mock analysis!"
        }
        
        try {
            val response = callClaudeApi(prompt)
            return response
        } catch (e: Exception) {
            return "AI insights temporarily unavailable. Please check your Claude API configuration."
        }
    }
    
    private suspend fun callClaudeApi(prompt: String): String {
        val request = ClaudeRequest(
            messages = listOf(
                ClaudeMessage(
                    role = "user",
                    content = prompt
                )
            )
        )
        
        val requestBody = gson.toJson(request)
            .toRequestBody("application/json".toMediaType())
        
        val httpRequest = Request.Builder()
            .url(CLAUDE_API_URL)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", ClaudeConfig.getApiKey())
            .addHeader("anthropic-version", CLAUDE_VERSION)
            .build()
        
        val response = client.newCall(httpRequest).execute()
        
        if (!response.isSuccessful) {
            throw Exception("Claude API call failed: ${response.code} - ${response.message}")
        }
        
        val responseBody = response.body?.string() ?: throw Exception("Empty response body")
        val claudeResponse = gson.fromJson(responseBody, ClaudeResponse::class.java)
        
        return claudeResponse.content.firstOrNull()?.text 
            ?: throw Exception("No content in Claude response")
    }
    
    private fun buildAppDevelopmentPrompt(
        opportunity: BusinessOpportunity,
        trendingTopics: List<TrendingTopic>,
        relatedStories: List<Story>
    ): String {
        return """
        You are Claude 4, an expert startup advisor and technical architect. Using your advanced reasoning capabilities, analyze the following business opportunity and create a comprehensive, data-driven app development plan.
        
        Business Opportunity: ${opportunity.title}
        Description: ${opportunity.description}
        Market Potential: ${opportunity.marketPotential}
        Revenue Estimate: ${opportunity.revenueEstimate}
        
        Current Market Context - Trending Topics:
        ${trendingTopics.joinToString("\n") { "- ${it.name}: ${it.description} (${it.mentions} mentions, ${String.format("%.1f", it.relevanceScore * 100)}% relevance)" }}
        
        Related High-Performing Stories:
        ${relatedStories.take(3).joinToString("\n") { "- ${it.title} (Score: ${it.score}, Comments: ${it.descendants ?: 0})" }}
        
        Please provide a detailed, strategic development plan with:
        
        1. **Technical Architecture** - Modern, scalable stack recommendations with AI integration
        2. **Development Timeline** - Realistic milestones with risk buffers
        3. **Team Strategy** - Optimal team composition for current market conditions
        4. **Risk Analysis** - Comprehensive threat assessment with mitigation strategies
        5. **Go-to-Market Strategy** - Data-driven launch approach based on current trends
        6. **Competitive Intelligence** - Deep analysis of market positioning
        7. **Financial Projections** - Realistic budget breakdown with scaling considerations
        8. **AI Integration Strategy** - How to leverage Claude 4 and other AI tools for competitive advantage
        
        Focus on practical, immediately actionable advice. Consider the current tech landscape, economic conditions, and emerging opportunities. Provide specific recommendations rather than generic advice.
        """.trimIndent()
    }
    
    private fun buildStoryAnalysisPrompt(story: Story): String {
        return """
        You are Claude 4 with advanced analytical capabilities. Perform a comprehensive analysis of this Hacker News story using your enhanced reasoning and pattern recognition abilities.
        
        Story Details:
        Title: ${story.title}
        Author: ${story.by}
        Score: ${story.score}
        Comments: ${story.descendants}
        Content: ${story.text?.take(800) ?: "No text content"}
        Timestamp: ${story.time}
        
        Please provide a detailed analysis including:
        
        1. **Sentiment Analysis** - Emotional tone and community reception
        2. **Topic Classification** - Primary and secondary tech categories
        3. **Innovation Assessment** - Technological advancement potential (0-1 scale)
        4. **Controversy Evaluation** - Potential for debate and discussion (0-1 scale)
        5. **Business Impact** - Commercial opportunity and market implications
        6. **Trend Significance** - How this fits into broader tech trends
        7. **Key Insights** - Strategic takeaways for developers/entrepreneurs
        8. **Risk Assessment** - Potential technical, regulatory, or market risks
        
        Use your advanced reasoning to provide nuanced, context-aware insights that go beyond surface-level analysis. Consider the broader tech ecosystem and current market dynamics.
        """.trimIndent()
    }
    
    private fun buildDailySummaryPrompt(stories: List<Story>): String {
        val topStories = stories.sortedByDescending { it.score ?: 0 }.take(15)
        val totalScore = topStories.sumOf { it.score ?: 0 }
        val totalComments = topStories.sumOf { it.descendants ?: 0 }
        
        return """
        You are Claude 4 with advanced analytical and synthesis capabilities. Create a comprehensive daily intelligence summary of today's Hacker News stories using your enhanced reasoning abilities.
        
        Dataset Overview:
        - Total stories analyzed: ${stories.size}
        - Top stories engagement: ${totalScore} total points, ${totalComments} comments
        - Time range: Last 24 hours
        
        Top Stories:
        ${topStories.joinToString("\n") { story -> 
            "- ${story.title} (Score: ${story.score}, Comments: ${story.descendants ?: 0}, Author: ${story.by})"
        }}
        
        Please provide a strategic intelligence summary including:
        
        1. **Executive Summary** - Key trends and patterns with strategic implications
        2. **Critical Stories** - Top 5 most significant developments with impact analysis
        3. **Emerging Tech Trends** - New technologies and their potential trajectory
        4. **Business Intelligence** - Market opportunities and competitive landscape shifts
        5. **Developer Insights** - Technical trends and tools gaining traction
        6. **Market Dynamics** - Economic and industry forces at play
        7. **Strategic Recommendations** - Actionable advice for entrepreneurs and developers
        8. **Risk Assessment** - Potential challenges and market threats
        9. **Future Outlook** - Predictions based on current patterns
        
        Use your advanced reasoning to identify non-obvious connections, emerging patterns, and strategic opportunities. Focus on actionable intelligence that goes beyond surface-level reporting.
        """.trimIndent()
    }
    
    private fun buildCommentInsightsPrompt(comments: List<Comment>): String {
        val commentTexts = comments.take(15).mapNotNull { it.text }.joinToString("\n\n")
        val commentStats = comments.mapNotNull { it.by }.groupingBy { it }.eachCount()
        
        return """
        You are Claude 4 with advanced discourse analysis capabilities. Analyze this Hacker News discussion thread using your enhanced reasoning and pattern recognition abilities.
        
        Discussion Overview:
        - Total comments: ${comments.size}
        - Unique participants: ${commentStats.size}
        - Active contributors: ${commentStats.filter { it.value > 1 }.size}
        
        Comment Thread:
        ${commentTexts}
        
        Please provide a comprehensive discussion analysis including:
        
        1. **Sentiment Analysis** - Overall emotional tone and community reception
        2. **Argument Mapping** - Key viewpoints, counterarguments, and debate structure
        3. **Expertise Assessment** - Technical depth and credibility indicators
        4. **Consensus Identification** - Points of agreement and disagreement
        5. **Risk Factors** - Concerns, challenges, and potential issues raised
        6. **Opportunity Signals** - Solutions, innovations, and positive developments
        7. **Community Intelligence** - Insights into developer/tech community thinking
        8. **Strategic Implications** - What this discussion means for the broader tech landscape
        
        Use your advanced reasoning to identify underlying patterns, implicit assumptions, and strategic insights that may not be immediately obvious. Focus on actionable intelligence.
        """.trimIndent()
    }
    
    // Fallback methods for when API is unavailable
    private fun createFallbackAppDevelopmentPlan(opportunity: BusinessOpportunity): AppDevelopmentPlan {
        return AppDevelopmentPlan(
            projectName = opportunity.appOpportunity.appName,
            description = opportunity.description,
            marketValidation = MarketValidation(
                marketSize = opportunity.marketPotential,
                targetAudience = opportunity.appOpportunity.targetUsers,
                customerProblems = listOf("AI analysis unavailable - please configure Claude API"),
                solutionFit = 0.7f,
                monetizationPotential = opportunity.revenueEstimate,
                existingCompetitors = listOf("API configuration required"),
                marketGap = "Claude API integration needed for full analysis"
            ),
            technicalPlan = TechnicalPlan(
                architecture = "Modern full-stack architecture",
                techStack = listOf("React/Next.js", "Node.js", "PostgreSQL", "Claude API"),
                coreFeatures = opportunity.appOpportunity.coreFeatures.map { feature ->
                    FeatureSpec(
                        name = feature,
                        description = "Feature implementation with AI assistance",
                        priority = "Medium",
                        complexity = "Medium",
                        estimatedHours = 40,
                        dependencies = emptyList()
                    )
                },
                scalabilityPlan = "Cloud-native architecture",
                securityConsiderations = listOf("API key management", "Data encryption"),
                performanceTargets = listOf("< 2s response time", "99% uptime"),
                infrastructureNeeds = "Cloud hosting with AI integration"
            ),
            businessPlan = BusinessPlan(
                businessModel = "SaaS",
                revenueStreams = listOf("Subscription", "API access"),
                costStructure = "Development 60%, Operations 40%",
                fundingRequirements = "Bootstrap with AI tools",
                goToMarketStrategy = "Product Hunt launch",
                marketingChannels = listOf("Content marketing", "Social media"),
                partnershipOpportunities = listOf("AI tool integrations")
            ),
            timelinePlan = TimelinePlan(
                totalDuration = "6-12 months",
                phases = listOf(
                    DevelopmentPhase("Planning", "1 month", listOf("Requirements", "Architecture"), listOf("Solo developer"))
                ),
                milestones = listOf(
                    Milestone("MVP", "Month 3", listOf("Core features"), listOf("Working prototype"))
                ),
                criticalPath = listOf("API integration", "User validation")
            ),
            resourcePlan = ResourcePlan(
                teamComposition = listOf(
                    TeamRole("Developer", "Full-stack development", listOf("React", "Node.js"), "3+ years", "$100K", "Remote", "Full-time")
                ),
                budgetBreakdown = listOf(
                    BudgetItem("Development", "Salary and tools", "$8K", "Monthly")
                ),
                toolsAndServices = listOf("Claude API", "Development tools"),
                totalCost = "$8K/month",
                fundingOptions = listOf("Bootstrap", "Angel funding")
            ),
            riskAssessment = RiskAssessment(
                technicalRisks = listOf(
                    Risk("API", "Claude API integration complexity", "Medium", "Medium", "Fallback implementations")
                ),
                marketRisks = listOf(
                    Risk("Market", "Competition", "Medium", "High", "Unique value proposition")
                ),
                businessRisks = listOf(
                    Risk("Business", "Funding", "Medium", "High", "Multiple revenue streams")
                ),
                mitigationStrategies = listOf("MVP first", "Customer validation"),
                overallRiskLevel = "Medium"
            ),
            targetLocations = listOf(
                TargetLocation("US", "San Francisco", "Large", "High", "Moderate", "Excellent", "High", "Competitive", listOf("Innovation hub"), listOf("VC access"), 0.85f)
            ),
            competitorAnalysis = CompetitorAnalysis(
                directCompetitors = listOf(
                    Competitor("Existing Solution", "Current market player", listOf("Market presence"), listOf("Limited AI"), "Unknown", "Unknown", "Various")
                ),
                indirectCompetitors = emptyList(),
                marketGaps = listOf("AI-powered solution"),
                competitiveAdvantages = listOf("Claude integration"),
                threatLevel = "Medium"
            ),
            isViable = true,
            viabilityScore = 0.75f
        )
    }
    
    private fun createFallbackStoryAnalysis(story: Story): StoryAnalysis {
        return StoryAnalysis(
            storyId = story.id,
            sentimentScore = 0.0f,
            emotionProfile = com.hackernews.futuristic.data.model.EmotionProfile(
                joy = 0.3f,
                anger = 0.1f,
                fear = 0.1f,
                sadness = 0.1f,
                surprise = 0.2f,
                disgust = 0.1f,
                trust = 0.1f,
                anticipation = 0.1f
            ),
            topicClassification = com.hackernews.futuristic.data.model.TopicClassification(
                primaryTopic = com.hackernews.futuristic.data.model.Topic.GENERAL_TECH,
                confidence = 0.7f,
                secondaryTopics = emptyList()
            ),
            controversyLevel = 0.3f,
            relevanceScore = 0.6f,
            keywords = listOf("Claude API", "configuration", "required"),
            threatLevel = com.hackernews.futuristic.data.model.ThreatLevel.LOW,
            processingTimestamp = System.currentTimeMillis()
        )
    }
    
    private fun createFallbackDailySummary(stories: List<Story>): DailySummary {
        return DailySummary(
            date = java.text.SimpleDateFormat("EEEE, MMMM dd, yyyy", java.util.Locale.getDefault()).format(java.util.Date()),
            totalStories = stories.size,
            executiveSummary = "AI-powered analysis requires Claude API configuration. Please add your API key to enable full features.",
            topStories = stories.sortedByDescending { it.score ?: 0 }.take(5),
            trendingTopics = listOf(
                TrendingTopic("AI", "Artificial Intelligence trends", 5, 0.2f, 0.8f, emptyList()),
                TrendingTopic("Technology", "General tech discussions", 10, 0.1f, 0.9f, emptyList())
            ),
            businessOpportunity = BusinessOpportunity(
                title = "Configure Claude API",
                description = "Add your Claude API key to unlock AI-powered insights",
                marketPotential = "High with proper configuration",
                revenueEstimate = "Depends on implementation",
                viabilityScore = 0.5f,
                implementationPlan = listOf("Add API key", "Test integration", "Enable features"),
                hostingRecommendations = listOf("Cloud hosting recommended"),
                contextEngineering = com.hackernews.futuristic.data.model.ContextEngineeringPlan(
                    contentPipeline = listOf("API integration needed"),
                    aiModels = listOf("Claude 3.5 Sonnet"),
                    dataProcessing = listOf("Real-time analysis"),
                    qualityAssurance = listOf("API validation"),
                    automationLevel = 0.8f
                ),
                distributionStrategy = com.hackernews.futuristic.data.model.DistributionStrategy(
                    platforms = emptyList(),
                    contentFormats = emptyList(),
                    targetAudience = "Tech professionals",
                    marketingChannels = emptyList(),
                    revenueStreams = emptyList()
                ),
                appOpportunity = AppOpportunity(
                    appName = "Claude Integration",
                    appType = "API Integration",
                    coreFeatures = listOf("AI analysis", "Real-time insights"),
                    targetUsers = "App users",
                    competitorAnalysis = emptyList(),
                    uniqueValue = "Claude-powered insights",
                    monetizationModel = emptyList(),
                    technicalStack = emptyList(),
                    developmentTime = "Configuration required",
                    launchStrategy = emptyList()
                ),
                talentRequirements = com.hackernews.futuristic.data.model.TalentRequirements(
                    roles = emptyList(),
                    totalTeamSize = 1,
                    estimatedCost = "API usage costs",
                    timeToHire = "Immediate",
                    keySkills = listOf("API integration"),
                    experienceLevel = "Basic"
                )
            ),
            synthesisMetrics = com.hackernews.futuristic.data.model.SynthesisMetrics(
                neuralCoherence = 0.5f,
                synthesisQuality = 0.5f,
                patternStrength = 0.5f,
                businessViability = 0.5f,
                processingTime = 1000L,
                dataPoints = stories.size
            )
        )
    }
    
    // Parsing methods would extract structured data from Claude's responses
    private fun parseAppDevelopmentResponse(response: String, opportunity: BusinessOpportunity): AppDevelopmentPlan {
        // In a real implementation, this would parse Claude's structured response
        // For now, return enhanced fallback with Claude's insights
        return createFallbackAppDevelopmentPlan(opportunity).copy(
            description = "Claude-enhanced: ${response.take(200)}..."
        )
    }
    
    private fun parseStoryAnalysisResponse(response: String, story: Story): StoryAnalysis {
        // Parse Claude's analysis response
        return createFallbackStoryAnalysis(story).copy(
            keywords = listOf("Claude analysis: ${response.take(100)}...")
        )
    }
    
    private fun parseDailySummaryResponse(response: String, stories: List<Story>): DailySummary {
        // Parse Claude's summary response
        return createFallbackDailySummary(stories).copy(
            executiveSummary = "Claude-powered summary: ${response.take(300)}..."
        )
    }
}