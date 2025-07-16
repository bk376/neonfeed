package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color
import com.hackernews.futuristic.ui.theme.*

data class FilteredStory(
    val story: Story,
    val relevanceScore: Float,
    val predictions: ContentPrediction,
    val filterResults: FilteringResult,
    val shouldShow: Boolean,
    val reason: String,
    val rank: Int = 0,
    val boostFactor: Float = 1.0f
)

data class ContentPrediction(
    val storyId: Long,
    val relevanceScore: Float, // 0.0 to 1.0
    val engagementProbability: Float, // 0.0 to 1.0
    val estimatedReadingTime: Long, // milliseconds
    val userInterestLevel: Float, // 0.0 to 1.0
    val viralPotential: Float, // 0.0 to 1.0
    val topicMatchScore: Float, // 0.0 to 1.0
    val sentimentAlignment: Float, // 0.0 to 1.0
    val difficultyLevel: Float, // 0.0 to 1.0
    val noveltyScore: Float, // 0.0 to 1.0
    val qualityScore: Float, // 0.0 to 1.0
    val timeliness: Float, // 0.0 to 1.0
    val confidence: Float, // 0.0 to 1.0
    val reasoning: String,
    val predictedAt: Long = System.currentTimeMillis()
)

data class FilteringRule(
    val id: String,
    val name: String,
    val attribute: FilterAttribute,
    val operator: FilterOperator,
    val threshold: Float,
    val action: FilterDecision,
    val priority: Int,
    val isEnabled: Boolean,
    val confidence: Float,
    val description: String = "",
    val category: FilterCategory = FilterCategory.GENERAL
)

enum class FilterAttribute(val displayName: String, val description: String) {
    RELEVANCE_SCORE("Relevance Score", "Overall relevance to user interests"),
    ENGAGEMENT_PROBABILITY("Engagement Probability", "Likelihood of user engagement"),
    USER_INTEREST("User Interest", "Predicted user interest level"),
    QUALITY_SCORE("Quality Score", "Content quality assessment"),
    NOVELTY_SCORE("Novelty Score", "How novel/unique the content is"),
    DIFFICULTY_LEVEL("Difficulty Level", "Technical complexity level"),
    STORY_SCORE("Story Score", "Hacker News story score"),
    COMMENT_COUNT("Comment Count", "Number of comments"),
    STORY_AGE("Story Age", "Age of the story in seconds"),
    VIRAL_POTENTIAL("Viral Potential", "Likelihood of going viral"),
    TOPIC_MATCH("Topic Match", "Match with user's preferred topics"),
    SENTIMENT_ALIGNMENT("Sentiment Alignment", "Alignment with user's sentiment preferences"),
    READING_TIME("Reading Time", "Estimated reading time"),
    TIMELINESS("Timeliness", "How timely/current the content is")
}

// FilterOperator moved to SharedEnums.kt

enum class FilterDecision(val displayName: String, val color: Color) {
    SHOW("Show", NeonGreen),
    HIDE("Hide", NeonRed),
    HIGHLIGHT("Highlight", NeonBlue),
    DEMOTE("Demote", NeonYellow)
}

enum class FilterCategory(val displayName: String, val color: Color) {
    GENERAL("General", NeonBlue),
    QUALITY("Quality", NeonGreen),
    RELEVANCE("Relevance", NeonPurple),
    ENGAGEMENT("Engagement", NeonYellow),
    TEMPORAL("Temporal", NeonCyan),
    CUSTOM("Custom", NeonRed)
}

data class FilteringResult(
    val storyId: Long,
    val ruleResults: List<FilterRuleResult>,
    val overallDecision: FilterDecision,
    val primaryReason: String,
    val confidence: Float,
    val appliedAt: Long
)

data class FilterRuleResult(
    val ruleId: String,
    val ruleName: String,
    val decision: FilterDecision,
    val confidence: Float,
    val value: Float,
    val threshold: Float,
    val passes: Boolean,
    val reasoning: String
)

data class UserContentPreferences(
    val preferredTopics: List<String>,
    val topicWeights: Map<String, Float>,
    val difficultyPreference: Float, // 0.0 to 1.0
    val sentimentPreference: Float, // -1.0 to 1.0
    val lengthPreference: LengthPreference,
    val noveltyPreference: Float, // 0.0 to 1.0
    val qualityThreshold: Float, // 0.0 to 1.0
    val engagementThreshold: Float, // 0.0 to 1.0
    val viralityInterest: Float, // 0.0 to 1.0
    val timePreferences: Map<String, Float>,
    val domainPreferences: Map<String, Float>,
    val authorPreferences: Map<String, Float>,
    val avoidancePatterns: List<String>,
    val boostPatterns: List<String>,
    val lastUpdated: Long = System.currentTimeMillis()
)

enum class LengthPreference(val displayName: String, val range: IntRange) {
    SHORT("Short", 0..500),
    MEDIUM("Medium", 500..2000),
    LONG("Long", 2000..5000),
    VERY_LONG("Very Long", 5000..Int.MAX_VALUE),
    ANY("Any Length", 0..Int.MAX_VALUE)
}

data class PredictiveModel(
    val version: String,
    val trainedAt: Long,
    val accuracy: Float,
    val precision: Float,
    val recall: Float,
    val f1Score: Float,
    val featureWeights: Map<String, Float>,
    val hyperparameters: Map<String, Float>,
    val trainingDataSize: Int,
    val validationAccuracy: Float,
    val modelType: ModelType = ModelType.NEURAL_NETWORK
)

enum class ModelType(val displayName: String, val description: String) {
    NEURAL_NETWORK("Neural Network", "Deep learning neural network"),
    RANDOM_FOREST("Random Forest", "Ensemble of decision trees"),
    SVM("Support Vector Machine", "Support vector machine classifier"),
    LOGISTIC_REGRESSION("Logistic Regression", "Linear logistic regression"),
    GRADIENT_BOOSTING("Gradient Boosting", "Gradient boosting classifier"),
    ENSEMBLE("Ensemble", "Combination of multiple models")
}

data class StoryFeatures(
    val titleLength: Int,
    val textLength: Int,
    val wordCount: Int,
    val technicalTerms: Int,
    val programmingLanguages: List<String>,
    val domains: List<String>,
    val topics: List<String>,
    val sentiment: Float,
    val readabilityScore: Float,
    val urgencyIndicators: Int,
    val controversyMarkers: Int,
    val innovationLevel: Float,
    val practicalityScore: Float,
    val score: Int,
    val commentCount: Int,
    val authorReputation: Float,
    val timeOfDay: String,
    val dayOfWeek: String,
    val recency: Long // seconds since posted
)

data class ReadingEvent(
    val storyId: Long,
    val timestamp: Long,
    val readingTime: Long,
    val completed: Boolean,
    val storyFeatures: StoryFeatures,
    val userState: UserState = UserState.NORMAL
)

enum class UserState(val displayName: String) {
    FOCUSED("Focused"),
    DISTRACTED("Distracted"),
    TIRED("Tired"),
    ENGAGED("Engaged"),
    NORMAL("Normal"),
    RUSHED("Rushed")
}

data class InteractionEvent(
    val type: String,
    val storyId: Long,
    val timestamp: Long,
    val value: Float,
    val context: Map<String, String> = emptyMap()
)

data class FilteringInsights(
    val totalStoriesProcessed: Int,
    val storiesFilteredOut: Int,
    val highEngagementStories: Int,
    val filteringAccuracy: Float,
    val averageReadingTime: Long,
    val topFilteringReasons: List<String>,
    val userSatisfactionScore: Float,
    val modelPerformance: Float,
    val recommendations: List<String>,
    val trendsDetected: List<ContentTrend> = emptyList(),
    val performanceMetrics: FilteringMetrics = FilteringMetrics()
)

data class ContentTrend(
    val trendType: TrendType,
    val topic: String,
    val strength: Float, // 0.0 to 1.0
    val direction: TrendDirection,
    val timeframe: String,
    val confidence: Float,
    val examples: List<Long> = emptyList() // Story IDs
)

enum class TrendType(val displayName: String, val color: Color) {
    EMERGING_TOPIC("Emerging Topic", NeonGreen),
    DECLINING_INTEREST("Declining Interest", NeonRed),
    SEASONAL_PATTERN("Seasonal Pattern", NeonBlue),
    VIRAL_CONTENT("Viral Content", NeonYellow),
    QUALITY_SHIFT("Quality Shift", NeonPurple),
    USER_BEHAVIOR("User Behavior", NeonCyan)
}

// TrendDirection enum is defined in AROverlay.kt to avoid duplication

data class FilteringMetrics(
    val precision: Float = 0.0f,
    val recall: Float = 0.0f,
    val f1Score: Float = 0.0f,
    val accuracy: Float = 0.0f,
    val falsePositiveRate: Float = 0.0f,
    val falseNegativeRate: Float = 0.0f,
    val processingTime: Long = 0L,
    val memoryUsage: Long = 0L
)

data class PredictionExplanation(
    val storyId: Long,
    val mainFactors: List<ExplanationFactor>,
    val confidence: Float,
    val reasoning: String,
    val alternatives: List<AlternativePrediction> = emptyList()
)

data class ExplanationFactor(
    val factor: String,
    val weight: Float,
    val value: Float,
    val impact: String, // "positive", "negative", "neutral"
    val description: String
)

data class AlternativePrediction(
    val scenario: String,
    val prediction: ContentPrediction,
    val probability: Float,
    val description: String
)

data class FilteringConfiguration(
    val isEnabled: Boolean = true,
    val aggressiveness: FilterAggressiveness = FilterAggressiveness.MODERATE,
    val learningRate: Float = 0.01f,
    val adaptationEnabled: Boolean = true,
    val explainabilityLevel: ExplainabilityLevel = ExplainabilityLevel.MEDIUM,
    val privacyMode: PrivacyMode = PrivacyMode.STANDARD,
    val updateFrequency: UpdateFrequency = UpdateFrequency.DAILY
)

enum class FilterAggressiveness(val displayName: String, val threshold: Float) {
    CONSERVATIVE("Conservative", 0.3f),
    MODERATE("Moderate", 0.5f),
    AGGRESSIVE("Aggressive", 0.7f),
    VERY_AGGRESSIVE("Very Aggressive", 0.9f)
}

enum class ExplainabilityLevel(val displayName: String, val detailLevel: Int) {
    MINIMAL("Minimal", 1),
    BASIC("Basic", 2),
    MEDIUM("Medium", 3),
    DETAILED("Detailed", 4),
    COMPREHENSIVE("Comprehensive", 5)
}

enum class PrivacyMode(val displayName: String, val description: String) {
    MINIMAL("Minimal Tracking", "Minimal data collection"),
    STANDARD("Standard", "Standard data collection"),
    ENHANCED("Enhanced", "Enhanced personalization"),
    FULL("Full Personalization", "Maximum personalization")
}

enum class UpdateFrequency(val displayName: String, val intervalHours: Int) {
    REAL_TIME("Real-time", 0),
    HOURLY("Hourly", 1),
    DAILY("Daily", 24),
    WEEKLY("Weekly", 168),
    MANUAL("Manual", -1)
}

data class PersonalizationProfile(
    val userId: String,
    val createdAt: Long,
    val lastUpdated: Long,
    val preferences: UserContentPreferences,
    val behaviorPatterns: BehaviorPatterns,
    val contextualFactors: ContextualFactors,
    val adaptationHistory: List<AdaptationEvent>
)

data class BehaviorPatterns(
    val readingTimes: Map<String, Long>, // time of day -> average reading time
    val topicEngagement: Map<String, Float>, // topic -> engagement level
    val difficultyPreferences: Map<String, Float>, // context -> preferred difficulty
    val sessionPatterns: SessionPatterns,
    val attentionSpan: Long,
    val preferredContentLength: IntRange
)

data class SessionPatterns(
    val averageSessionDuration: Long,
    val storiesPerSession: Int,
    val peakActivityTimes: List<String>,
    val sessionFrequency: Map<String, Int>, // day of week -> session count
    val multitaskingLevel: Float
)

data class ContextualFactors(
    val deviceType: String,
    val connectionQuality: String,
    val timeZone: String,
    val language: String,
    val accessibilityNeeds: List<String>,
    val environmentalContext: Map<String, Float>
)

data class AdaptationEvent(
    val timestamp: Long,
    val eventType: AdaptationType,
    val trigger: String,
    val changes: Map<String, Float>,
    val impact: Float,
    val success: Boolean
)

enum class AdaptationType(val displayName: String) {
    PREFERENCE_UPDATE("Preference Update"),
    RULE_ADJUSTMENT("Rule Adjustment"),
    MODEL_RETRAIN("Model Retrain"),
    THRESHOLD_CHANGE("Threshold Change"),
    FEATURE_WEIGHT_UPDATE("Feature Weight Update"),
    BEHAVIOR_ADAPTATION("Behavior Adaptation")
}

data class FilteringExperiment(
    val id: String,
    val name: String,
    val description: String,
    val hypothesis: String,
    val controlGroup: FilteringConfiguration,
    val testGroup: FilteringConfiguration,
    val metrics: ExperimentMetrics,
    val startTime: Long,
    val endTime: Long?,
    val status: ExperimentStatus,
    val results: ExperimentResults?
)

enum class ExperimentStatus {
    PLANNED,
    RUNNING,
    COMPLETED,
    CANCELLED,
    PAUSED
}

data class ExperimentMetrics(
    val primaryMetric: String,
    val secondaryMetrics: List<String>,
    val sampleSize: Int,
    val significanceLevel: Float,
    val minimumDetectableEffect: Float
)

data class ExperimentResults(
    val primaryMetricResult: MetricResult,
    val secondaryMetricResults: List<MetricResult>,
    val statisticalSignificance: Boolean,
    val confidence: Float,
    val recommendation: String,
    val insights: List<String>
)

data class MetricResult(
    val metricName: String,
    val controlValue: Float,
    val testValue: Float,
    val improvement: Float,
    val pValue: Float,
    val isSignificant: Boolean
)