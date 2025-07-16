package com.hackernews.futuristic.data.model

data class DailySummary(
    val date: String,
    val totalStories: Int,
    val executiveSummary: String,
    val topStories: List<Story>,
    val trendingTopics: List<TrendingTopic>,
    val businessOpportunity: BusinessOpportunity,
    val synthesisMetrics: SynthesisMetrics
)

data class TrendingTopic(
    val name: String,
    val description: String,
    val mentions: Int,
    val growthRate: Float,
    val relevanceScore: Float,
    val associatedStories: List<Story>
)

data class BusinessOpportunity(
    val title: String,
    val description: String,
    val marketPotential: String,
    val revenueEstimate: String,
    val viabilityScore: Float,
    val implementationPlan: List<String>,
    val hostingRecommendations: List<String>,
    val contextEngineering: ContextEngineeringPlan,
    val distributionStrategy: DistributionStrategy,
    val appOpportunity: AppOpportunity,
    val talentRequirements: TalentRequirements,
    val isAccepted: Boolean = false
)

data class AppOpportunity(
    val appName: String,
    val appType: String,
    val coreFeatures: List<String>,
    val targetUsers: String,
    val competitorAnalysis: List<String>,
    val uniqueValue: String,
    val monetizationModel: List<String>,
    val technicalStack: List<String>,
    val developmentTime: String,
    val launchStrategy: List<String>
)

data class TalentRequirements(
    val roles: List<TalentRole>,
    val totalTeamSize: Int,
    val estimatedCost: String,
    val timeToHire: String,
    val keySkills: List<String>,
    val experienceLevel: String
)

data class TalentRole(
    val title: String,
    val description: String,
    val skills: List<String>,
    val salaryRange: String,
    val priority: String,
    val remoteOption: Boolean
)

data class ContextEngineeringPlan(
    val contentPipeline: List<String>,
    val aiModels: List<String>,
    val dataProcessing: List<String>,
    val qualityAssurance: List<String>,
    val automationLevel: Float
)

data class DistributionStrategy(
    val platforms: List<Platform>,
    val contentFormats: List<String>,
    val targetAudience: String,
    val marketingChannels: List<String>,
    val revenueStreams: List<String>
)

data class Platform(
    val name: String,
    val description: String,
    val audience: String,
    val costEstimate: String,
    val potentialReach: String,
    val implementationComplexity: String
)

data class SynthesisMetrics(
    val neuralCoherence: Float,
    val synthesisQuality: Float,
    val patternStrength: Float,
    val businessViability: Float,
    val processingTime: Long,
    val dataPoints: Int
)