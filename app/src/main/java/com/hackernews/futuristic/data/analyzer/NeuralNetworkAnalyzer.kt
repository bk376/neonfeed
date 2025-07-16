package com.hackernews.futuristic.data.analyzer

import com.hackernews.futuristic.data.model.*
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.*
import kotlin.random.Random

@Singleton
class NeuralNetworkAnalyzer @Inject constructor() {
    
    private val sentimentKeywords = mapOf(
        "positive" to listOf("amazing", "great", "awesome", "excellent", "fantastic", "breakthrough", "innovation", "revolutionary", "impressive", "outstanding"),
        "negative" to listOf("terrible", "awful", "disaster", "failure", "broken", "useless", "disappointing", "problematic", "concerning", "dangerous"),
        "neutral" to listOf("released", "announced", "available", "updated", "version", "support", "feature", "development", "technical", "implementation")
    )
    
    private val topicKeywords = mapOf(
        Topic.ARTIFICIAL_INTELLIGENCE to listOf("AI", "machine learning", "neural", "deep learning", "ChatGPT", "OpenAI", "artificial intelligence", "ML", "algorithm", "model"),
        Topic.CYBERSECURITY to listOf("security", "hack", "vulnerability", "breach", "encryption", "password", "malware", "phishing", "firewall", "cyberattack"),
        Topic.BLOCKCHAIN to listOf("blockchain", "bitcoin", "cryptocurrency", "crypto", "ethereum", "NFT", "web3", "decentralized", "mining", "DeFi"),
        Topic.WEB_DEVELOPMENT to listOf("JavaScript", "React", "Angular", "Vue", "Node.js", "frontend", "backend", "API", "web", "HTML", "CSS"),
        Topic.MOBILE_DEVELOPMENT to listOf("iOS", "Android", "mobile", "app", "Swift", "Kotlin", "Flutter", "React Native", "mobile development"),
        Topic.STARTUP_BUSINESS to listOf("startup", "funding", "venture", "IPO", "business", "entrepreneur", "investment", "acquisition", "merger"),
        Topic.PROGRAMMING_LANGUAGES to listOf("Python", "Java", "C++", "Rust", "Go", "programming", "language", "compiler", "framework"),
        Topic.HARDWARE to listOf("hardware", "CPU", "GPU", "chip", "semiconductor", "Intel", "AMD", "NVIDIA", "processor", "memory"),
        Topic.OPEN_SOURCE to listOf("open source", "GitHub", "license", "GPL", "MIT", "Apache", "community", "contribution", "fork"),
        Topic.PRIVACY to listOf("privacy", "data", "tracking", "surveillance", "GDPR", "cookies", "personal data", "anonymity")
    )
    
    private val emotionKeywords = mapOf(
        "joy" to listOf("happy", "excited", "thrilled", "delighted", "amazing", "fantastic", "wonderful", "brilliant"),
        "anger" to listOf("angry", "frustrated", "outraged", "furious", "terrible", "awful", "disgusting", "hate"),
        "fear" to listOf("scary", "terrifying", "concerned", "worried", "dangerous", "risky", "threat", "warning"),
        "sadness" to listOf("sad", "disappointed", "unfortunate", "tragic", "depressing", "heartbreaking"),
        "surprise" to listOf("surprising", "unexpected", "shocking", "amazing", "unbelievable", "incredible"),
        "disgust" to listOf("disgusting", "revolting", "appalling", "horrible", "sickening", "nauseating"),
        "trust" to listOf("reliable", "trustworthy", "credible", "authentic", "verified", "proven", "solid"),
        "anticipation" to listOf("excited", "anticipating", "looking forward", "upcoming", "soon", "expecting")
    )
    
    suspend fun analyzeStory(story: Story): StoryAnalysis {
        delay(100) // Simulate neural network processing
        
        val text = "${story.title} ${story.text ?: ""}".lowercase()
        
        return StoryAnalysis(
            storyId = story.id,
            sentimentScore = calculateSentimentScore(text),
            emotionProfile = analyzeEmotions(text),
            topicClassification = classifyTopic(text),
            controversyLevel = calculateControversyLevel(text, story.score, story.descendants),
            relevanceScore = calculateRelevanceScore(story),
            keywords = extractKeywords(text),
            threatLevel = calculateThreatLevel(text, story.score, story.descendants),
            processingTimestamp = System.currentTimeMillis()
        )
    }
    
    private fun calculateSentimentScore(text: String): Float {
        var score = 0f
        var totalWords = 0
        
        sentimentKeywords["positive"]?.forEach { keyword ->
            val count = text.split(keyword).size - 1
            score += count * 0.1f
            totalWords += count
        }
        
        sentimentKeywords["negative"]?.forEach { keyword ->
            val count = text.split(keyword).size - 1
            score -= count * 0.1f
            totalWords += count
        }
        
        return if (totalWords > 0) (score / totalWords).coerceIn(-1f, 1f) else 0f
    }
    
    private fun analyzeEmotions(text: String): EmotionProfile {
        val emotions = mutableMapOf<String, Float>()
        
        emotionKeywords.forEach { (emotion, keywords) ->
            var score = 0f
            keywords.forEach { keyword ->
                score += (text.split(keyword).size - 1) * 0.1f
            }
            emotions[emotion] = score.coerceIn(0f, 1f)
        }
        
        return EmotionProfile(
            joy = emotions["joy"] ?: 0f,
            anger = emotions["anger"] ?: 0f,
            fear = emotions["fear"] ?: 0f,
            sadness = emotions["sadness"] ?: 0f,
            surprise = emotions["surprise"] ?: 0f,
            disgust = emotions["disgust"] ?: 0f,
            trust = emotions["trust"] ?: 0f,
            anticipation = emotions["anticipation"] ?: 0f
        )
    }
    
    private fun classifyTopic(text: String): TopicClassification {
        val topicScores = mutableMapOf<Topic, Float>()
        
        topicKeywords.forEach { (topic, keywords) ->
            var score = 0f
            keywords.forEach { keyword ->
                score += (text.split(keyword.lowercase()).size - 1) * 1f
            }
            topicScores[topic] = score
        }
        
        val sortedTopics = topicScores.toList().sortedByDescending { it.second }
        val primaryTopic = sortedTopics.firstOrNull()?.first ?: Topic.UNKNOWN
        val secondaryTopics = sortedTopics.drop(1).take(3).map { it.first }
        
        val confidence = if (sortedTopics.isNotEmpty()) {
            val totalScore = sortedTopics.sumOf { it.second.toDouble() }.toFloat()
            if (totalScore > 0) sortedTopics.first().second / totalScore else 0f
        } else 0f
        
        return TopicClassification(
            primaryTopic = primaryTopic,
            secondaryTopics = secondaryTopics,
            confidence = confidence.coerceIn(0f, 1f)
        )
    }
    
    private fun calculateControversyLevel(text: String, score: Int, comments: Int?): Float {
        val controversyWords = listOf("controversial", "debate", "argument", "disagree", "wrong", "problem", "issue", "concern")
        var controversyScore = 0f
        
        controversyWords.forEach { word ->
            controversyScore += (text.split(word).size - 1) * 0.1f
        }
        
        // Factor in comment activity vs score ratio
        val engagementRatio = if (score > 0 && comments != null) comments.toFloat() / score else 0f
        controversyScore += engagementRatio * 0.1f
        
        return controversyScore.coerceIn(0f, 1f)
    }
    
    private fun calculateRelevanceScore(story: Story): Float {
        val timeDecay = exp(-((System.currentTimeMillis() / 1000 - story.time) / 86400.0)).toFloat()
        val scoreWeight = (story.score / 100f).coerceIn(0f, 1f)
        val commentWeight = ((story.descendants ?: 0) / 50f).coerceIn(0f, 1f)
        
        return (timeDecay * 0.4f + scoreWeight * 0.3f + commentWeight * 0.3f).coerceIn(0f, 1f)
    }
    
    private fun calculateThreatLevel(text: String, score: Int, comments: Int?): ThreatLevel {
        val controversyLevel = calculateControversyLevel(text, score, comments)
        
        return when {
            controversyLevel >= 0.8f -> ThreatLevel.CRITICAL
            controversyLevel >= 0.6f -> ThreatLevel.HIGH
            controversyLevel >= 0.3f -> ThreatLevel.MEDIUM
            else -> ThreatLevel.LOW
        }
    }
    
    private fun extractKeywords(text: String): List<String> {
        val words = text.split("\\s+".toRegex())
            .map { it.replace("[^a-zA-Z0-9]".toRegex(), "") }
            .filter { it.length > 3 }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(10)
            .map { it.first }
        
        return words
    }
    
    fun generateNeuralNetworkVisualization(analysis: StoryAnalysis): NeuralNetworkVisualization {
        val nodes = mutableListOf<NetworkNode>()
        val connections = mutableListOf<NetworkConnection>()
        
        // Input layer (8 nodes for emotions)
        repeat(8) { i ->
            nodes.add(NetworkNode(
                id = "input_$i",
                x = 0.1f,
                y = 0.1f + (i * 0.1f),
                activation = when(i) {
                    0 -> analysis.emotionProfile.joy
                    1 -> analysis.emotionProfile.anger
                    2 -> analysis.emotionProfile.fear
                    3 -> analysis.emotionProfile.sadness
                    4 -> analysis.emotionProfile.surprise
                    5 -> analysis.emotionProfile.disgust
                    6 -> analysis.emotionProfile.trust
                    else -> analysis.emotionProfile.anticipation
                },
                type = NodeType.INPUT
            ))
        }
        
        // Hidden layer (6 nodes)
        repeat(6) { i ->
            nodes.add(NetworkNode(
                id = "hidden_$i",
                x = 0.5f,
                y = 0.2f + (i * 0.1f),
                activation = Random.nextFloat(),
                type = NodeType.HIDDEN
            ))
        }
        
        // Output layer (4 nodes)
        repeat(4) { i ->
            nodes.add(NetworkNode(
                id = "output_$i",
                x = 0.9f,
                y = 0.3f + (i * 0.1f),
                activation = when(i) {
                    0 -> analysis.sentimentScore
                    1 -> analysis.controversyLevel
                    2 -> analysis.relevanceScore
                    else -> analysis.topicClassification.confidence
                },
                type = NodeType.OUTPUT
            ))
        }
        
        // Generate connections
        nodes.filter { it.type == NodeType.INPUT }.forEach { inputNode ->
            nodes.filter { it.type == NodeType.HIDDEN }.forEach { hiddenNode ->
                connections.add(NetworkConnection(
                    fromNodeId = inputNode.id,
                    toNodeId = hiddenNode.id,
                    weight = Random.nextFloat() * 2 - 1,
                    isActive = inputNode.activation > 0.3f
                ))
            }
        }
        
        nodes.filter { it.type == NodeType.HIDDEN }.forEach { hiddenNode ->
            nodes.filter { it.type == NodeType.OUTPUT }.forEach { outputNode ->
                connections.add(NetworkConnection(
                    fromNodeId = hiddenNode.id,
                    toNodeId = outputNode.id,
                    weight = Random.nextFloat() * 2 - 1,
                    isActive = hiddenNode.activation > 0.3f
                ))
            }
        }
        
        return NeuralNetworkVisualization(
            nodes = nodes,
            connections = connections,
            activationPattern = nodes.map { it.activation }
        )
    }
}