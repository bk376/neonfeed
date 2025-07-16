package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color
import com.hackernews.futuristic.ui.theme.*

data class BiometricReadingData(
    val timestamp: Long,
    val heartRate: Int,
    val eyeMovement: Float, // 0.0 to 1.0
    val blinkRate: Float, // 0.0 to 1.0
    val focusIntensity: Float, // 0.0 to 1.0
    val comprehensionLevel: Float, // 0.0 to 1.0
    val stressLevel: Float, // 0.0 to 1.0
    val interestLevel: Float, // 0.0 to 1.0
    val cognitiveLoad: Float // 0.0 to 1.0
)

data class ReadingSession(
    val storyId: Long,
    val startTime: Long,
    val endTime: Long?,
    val totalReadingTime: Long,
    val cognitiveMetrics: CognitiveMetrics,
    val attentionPattern: AttentionPattern,
    val biometricData: List<BiometricReadingData>
)

data class CognitiveMetrics(
    val averageHeartRate: Float = 70f,
    val peakHeartRate: Float = 70f,
    val averageFocusIntensity: Float = 0.5f,
    val peakFocusIntensity: Float = 0.5f,
    val averageComprehension: Float = 0.7f,
    val averageStressLevel: Float = 0.3f,
    val averageInterestLevel: Float = 0.6f,
    val cognitiveEfficiency: Float = 0.7f
)

data class AttentionPattern(
    val attentionSpan: Long = 0L,
    val focusBreaks: Int = 0,
    val deepFocusPeriods: Int = 0,
    val distractionEvents: Int = 0,
    val readingVelocity: Float = 0.5f,
    val comprehensionRate: Float = 0.7f
)

data class BiometricAlert(
    val type: AlertType,
    val message: String,
    val severity: AlertSeverity,
    val timestamp: Long,
    val recommendations: List<String>
)

enum class AlertType(val displayName: String, val color: Color) {
    HIGH_STRESS("High Stress", NeonRed),
    LOW_FOCUS("Low Focus", NeonYellow),
    FATIGUE("Fatigue Detected", CyberpunkOrange),
    OPTIMAL_STATE("Optimal Reading State", NeonGreen),
    COMPREHENSION_ISSUE("Comprehension Difficulty", NeonPurple),
    DISTRACTION("Distraction Detected", NeonBlue)
}

enum class AlertSeverity(val level: Int, val color: Color) {
    LOW(1, NeonGreen),
    MEDIUM(2, NeonYellow),
    HIGH(3, NeonRed),
    CRITICAL(4, CyberpunkRed)
}

data class ReadingRecommendation(
    val type: RecommendationType,
    val title: String,
    val description: String,
    val actionText: String,
    val priority: Int
)

enum class RecommendationType(val displayName: String, val color: Color) {
    BREAK("Take a Break", NeonYellow),
    ENVIRONMENT("Adjust Environment", NeonBlue),
    CONTENT("Content Adjustment", NeonPurple),
    TECHNIQUE("Reading Technique", NeonGreen),
    WELLNESS("Wellness Check", NeonCyan)
}

data class BiometricVisualization(
    val heartRateGraph: List<Float>,
    val focusIntensityGraph: List<Float>,
    val stressLevelGraph: List<Float>,
    val comprehensionGraph: List<Float>,
    val timeLabels: List<String>,
    val currentValues: BiometricReadingData?
)

data class ReadingPerformance(
    val readingSpeed: Float, // words per minute
    val comprehensionAccuracy: Float, // percentage
    val retentionRate: Float, // percentage
    val engagementLevel: Float, // 0.0 to 1.0
    val difficultyRating: Float, // 0.0 to 1.0
    val optimalReadingTime: Long, // milliseconds
    val fatigueLevel: Float, // 0.0 to 1.0
    val learningEfficiency: Float // 0.0 to 1.0
)

data class NeuralFeedback(
    val brainwavePattern: BrainwavePattern,
    val mentalState: MentalState,
    val cognitiveCapacity: Float, // 0.0 to 1.0
    val memoryEncoding: Float, // 0.0 to 1.0
    val informationProcessing: Float, // 0.0 to 1.0
    val creativityIndex: Float, // 0.0 to 1.0
    val analyticalThinking: Float // 0.0 to 1.0
)

enum class BrainwavePattern(val displayName: String, val color: Color, val frequency: String) {
    ALPHA("Alpha", NeonBlue, "8-12 Hz"),
    BETA("Beta", NeonGreen, "12-30 Hz"),
    GAMMA("Gamma", NeonPurple, "30-100 Hz"),
    THETA("Theta", NeonYellow, "4-8 Hz"),
    DELTA("Delta", NeonCyan, "0.5-4 Hz")
}

enum class MentalState(val displayName: String, val color: Color, val description: String) {
    FOCUSED("Focused", NeonGreen, "High concentration and attention"),
    RELAXED("Relaxed", NeonBlue, "Calm and receptive state"),
    CREATIVE("Creative", NeonPurple, "Enhanced creative thinking"),
    ANALYTICAL("Analytical", NeonYellow, "Logical problem-solving mode"),
    STRESSED("Stressed", NeonRed, "High cognitive load and tension"),
    FATIGUED("Fatigued", CyberpunkOrange, "Mental exhaustion detected")
}

data class BiometricInsight(
    val category: InsightCategory,
    val title: String,
    val description: String,
    val confidence: Float, // 0.0 to 1.0
    val actionable: Boolean,
    val recommendations: List<String>
)

enum class InsightCategory(val displayName: String, val color: Color) {
    PERFORMANCE("Performance", NeonGreen),
    HEALTH("Health", NeonBlue),
    LEARNING("Learning", NeonPurple),
    BEHAVIOR("Behavior", NeonYellow),
    ENVIRONMENT("Environment", NeonCyan)
}

data class ReadingGoal(
    val type: GoalType,
    val target: Float,
    val current: Float,
    val progress: Float,
    val deadline: Long?,
    val achieved: Boolean
)

enum class GoalType(val displayName: String, val unit: String, val color: Color) {
    FOCUS_TIME("Focus Time", "minutes", NeonGreen),
    COMPREHENSION("Comprehension", "percentage", NeonBlue),
    READING_SPEED("Reading Speed", "wpm", NeonPurple),
    STRESS_REDUCTION("Stress Reduction", "level", NeonYellow),
    LEARNING_EFFICIENCY("Learning Efficiency", "percentage", NeonCyan)
}

data class BiometricProfile(
    val userId: String,
    val baselineMetrics: CognitiveMetrics,
    val readingPreferences: ReadingPreferences,
    val performanceHistory: List<ReadingPerformance>,
    val goals: List<ReadingGoal>,
    val insights: List<BiometricInsight>
)

data class ReadingPreferences(
    val preferredReadingTime: Long, // milliseconds
    val optimalBreakInterval: Long, // milliseconds
    val difficultyPreference: Float, // 0.0 to 1.0
    val environmentalFactors: Map<String, Float>,
    val contentTypes: Map<String, Float>
)