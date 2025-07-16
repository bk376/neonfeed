package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color

data class StoryAnalysis(
    val storyId: Long,
    val sentimentScore: Float, // -1.0 to 1.0
    val emotionProfile: EmotionProfile,
    val topicClassification: TopicClassification,
    val controversyLevel: Float, // 0.0 to 1.0
    val relevanceScore: Float, // 0.0 to 1.0
    val keywords: List<String>,
    val threatLevel: ThreatLevel,
    val processingTimestamp: Long
)

data class EmotionProfile(
    val joy: Float,
    val anger: Float,
    val fear: Float,
    val sadness: Float,
    val surprise: Float,
    val disgust: Float,
    val trust: Float,
    val anticipation: Float
) {
    fun getDominantEmotion(): Emotion {
        val emotions = mapOf(
            Emotion.JOY to joy,
            Emotion.ANGER to anger,
            Emotion.FEAR to fear,
            Emotion.SADNESS to sadness,
            Emotion.SURPRISE to surprise,
            Emotion.DISGUST to disgust,
            Emotion.TRUST to trust,
            Emotion.ANTICIPATION to anticipation
        )
        return emotions.maxByOrNull { it.value }?.key ?: Emotion.NEUTRAL
    }
}

enum class Emotion(val color: Color, val displayName: String) {
    JOY(Color(0xFF00FF88), "Joy"),
    ANGER(Color(0xFFFF0040), "Anger"),
    FEAR(Color(0xFF8B5CF6), "Fear"),
    SADNESS(Color(0xFF4A90E2), "Sadness"),
    SURPRISE(Color(0xFFFFD700), "Surprise"),
    DISGUST(Color(0xFF8B4513), "Disgust"),
    TRUST(Color(0xFF00D4FF), "Trust"),
    ANTICIPATION(Color(0xFFFF6B35), "Anticipation"),
    NEUTRAL(Color(0xFF808080), "Neutral")
}

data class TopicClassification(
    val primaryTopic: Topic,
    val secondaryTopics: List<Topic>,
    val confidence: Float
)

enum class Topic(val color: Color, val displayName: String) {
    ARTIFICIAL_INTELLIGENCE(Color(0xFF00D4FF), "AI/ML"),
    CYBERSECURITY(Color(0xFFFF0040), "Security"),
    BLOCKCHAIN(Color(0xFFFFD700), "Blockchain"),
    WEB_DEVELOPMENT(Color(0xFF00FF88), "Web Dev"),
    MOBILE_DEVELOPMENT(Color(0xFF8B5CF6), "Mobile"),
    STARTUP_BUSINESS(Color(0xFFFF6B35), "Startup"),
    PROGRAMMING_LANGUAGES(Color(0xFF4A90E2), "Programming"),
    HARDWARE(Color(0xFF8B4513), "Hardware"),
    OPEN_SOURCE(Color(0xFF32CD32), "Open Source"),
    PRIVACY(Color(0xFFDC143C), "Privacy"),
    GENERAL_TECH(Color(0xFF708090), "General Tech"),
    UNKNOWN(Color(0xFF696969), "Unknown")
}

enum class ThreatLevel(val color: Color, val displayName: String, val description: String) {
    LOW(Color(0xFF00FF88), "LOW", "Minimal controversy"),
    MEDIUM(Color(0xFFFFD700), "MEDIUM", "Some debate expected"),
    HIGH(Color(0xFFFF6B35), "HIGH", "Highly controversial"),
    CRITICAL(Color(0xFFFF0040), "CRITICAL", "Extreme controversy")
}

data class NeuralNetworkVisualization(
    val nodes: List<NetworkNode>,
    val connections: List<NetworkConnection>,
    val activationPattern: List<Float>
)

data class NetworkNode(
    val id: String,
    val x: Float,
    val y: Float,
    val activation: Float,
    val type: NodeType
)

enum class NodeType {
    INPUT, HIDDEN, OUTPUT
}

data class NetworkConnection(
    val fromNodeId: String,
    val toNodeId: String,
    val weight: Float,
    val isActive: Boolean
)