package com.hackernews.futuristic.data.analyzer

import com.hackernews.futuristic.data.model.BiometricReadingData
import com.hackernews.futuristic.data.model.ReadingSession
import com.hackernews.futuristic.data.model.CognitiveMetrics
import com.hackernews.futuristic.data.model.AttentionPattern
import com.hackernews.futuristic.data.model.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.*
import kotlin.random.Random

@Singleton
class BiometricReadingAnalyzer @Inject constructor() {
    
    private val _currentSession = MutableStateFlow<ReadingSession?>(null)
    val currentSession: StateFlow<ReadingSession?> = _currentSession.asStateFlow()
    
    private val _biometricData = MutableStateFlow<BiometricReadingData?>(null)
    val biometricData: StateFlow<BiometricReadingData?> = _biometricData.asStateFlow()
    
    private var sessionStartTime: Long = 0
    private var currentStory: Story? = null
    
    fun startReadingSession(story: Story) {
        sessionStartTime = System.currentTimeMillis()
        currentStory = story
        
        val session = ReadingSession(
            storyId = story.id,
            startTime = sessionStartTime,
            endTime = null,
            totalReadingTime = 0L,
            cognitiveMetrics = CognitiveMetrics(),
            attentionPattern = AttentionPattern(),
            biometricData = mutableListOf()
        )
        
        _currentSession.value = session
        startBiometricMonitoring()
    }
    
    fun endReadingSession(): ReadingSession? {
        val session = _currentSession.value ?: return null
        val endTime = System.currentTimeMillis()
        
        val completedSession = session.copy(
            endTime = endTime,
            totalReadingTime = endTime - sessionStartTime,
            cognitiveMetrics = calculateFinalCognitiveMetrics(session),
            attentionPattern = calculateFinalAttentionPattern(session)
        )
        
        _currentSession.value = null
        _biometricData.value = null
        
        return completedSession
    }
    
    private fun startBiometricMonitoring() {
        // Simulate biometric data collection
        // In a real app, this would interface with sensors
        generateSimulatedBiometricData()
    }
    
    private fun generateSimulatedBiometricData() {
        val story = currentStory ?: return
        
        // Simulate reading patterns based on story content
        val complexity = calculateStoryComplexity(story)
        val engagement = calculateStoryEngagement(story)
        
        val biometricData = BiometricReadingData(
            timestamp = System.currentTimeMillis(),
            heartRate = generateHeartRate(complexity, engagement),
            eyeMovement = generateEyeMovement(complexity),
            blinkRate = generateBlinkRate(engagement),
            focusIntensity = generateFocusIntensity(complexity, engagement),
            comprehensionLevel = generateComprehensionLevel(complexity),
            stressLevel = generateStressLevel(complexity),
            interestLevel = generateInterestLevel(engagement),
            cognitiveLoad = generateCognitiveLoad(complexity)
        )
        
        _biometricData.value = biometricData
        
        // Update current session
        _currentSession.value?.let { session ->
            val updatedData = session.biometricData.toMutableList()
            updatedData.add(biometricData)
            
            _currentSession.value = session.copy(
                biometricData = updatedData,
                cognitiveMetrics = updateCognitiveMetrics(session.cognitiveMetrics, biometricData),
                attentionPattern = updateAttentionPattern(session.attentionPattern, biometricData)
            )
        }
    }
    
    private fun calculateStoryComplexity(story: Story): Float {
        val title = story.title ?: ""
        val text = story.text ?: ""
        
        var complexity = 0.5f
        
        // Technical terms increase complexity
        val technicalTerms = listOf(
            "algorithm", "machine learning", "neural network", "quantum", 
            "blockchain", "cryptocurrency", "API", "framework", "database",
            "security", "encryption", "protocol", "architecture", "distributed"
        )
        
        technicalTerms.forEach { term ->
            if (title.contains(term, ignoreCase = true) || text.contains(term, ignoreCase = true)) {
                complexity += 0.1f
            }
        }
        
        // Length affects complexity
        val wordCount = (title.split(" ").size + text.split(" ").size)
        complexity += min(wordCount / 1000f, 0.3f)
        
        return min(complexity, 1.0f)
    }
    
    private fun calculateStoryEngagement(story: Story): Float {
        val score = story.score
        val comments = story.descendants ?: 0
        
        // Higher scores and more comments indicate higher engagement
        val scoreEngagement = min(score / 500f, 0.5f)
        val commentEngagement = min(comments / 100f, 0.5f)
        
        return scoreEngagement + commentEngagement
    }
    
    private fun generateHeartRate(complexity: Float, engagement: Float): Int {
        val baseRate = 70
        val complexityEffect = (complexity * 20).toInt()
        val engagementEffect = (engagement * 15).toInt()
        val noise = Random.nextInt(-5, 5)
        
        return baseRate + complexityEffect + engagementEffect + noise
    }
    
    private fun generateEyeMovement(complexity: Float): Float {
        // Higher complexity = more eye movements
        return 0.3f + (complexity * 0.5f) + (Random.nextFloat() * 0.2f)
    }
    
    private fun generateBlinkRate(engagement: Float): Float {
        // Higher engagement = lower blink rate (more focused)
        return 0.8f - (engagement * 0.3f) + (Random.nextFloat() * 0.2f)
    }
    
    private fun generateFocusIntensity(complexity: Float, engagement: Float): Float {
        // Balance between complexity and engagement
        return (engagement * 0.6f) + (complexity * 0.2f) + (Random.nextFloat() * 0.2f)
    }
    
    private fun generateComprehensionLevel(complexity: Float): Float {
        // Inverse relationship with complexity
        return 1.0f - (complexity * 0.4f) + (Random.nextFloat() * 0.2f - 0.1f)
    }
    
    private fun generateStressLevel(complexity: Float): Float {
        // Direct relationship with complexity
        return complexity * 0.6f + (Random.nextFloat() * 0.3f)
    }
    
    private fun generateInterestLevel(engagement: Float): Float {
        // Direct relationship with engagement
        return engagement * 0.8f + (Random.nextFloat() * 0.2f)
    }
    
    private fun generateCognitiveLoad(complexity: Float): Float {
        // Direct relationship with complexity
        return complexity * 0.7f + (Random.nextFloat() * 0.3f)
    }
    
    private fun updateCognitiveMetrics(
        current: CognitiveMetrics,
        newData: BiometricReadingData
    ): CognitiveMetrics {
        return current.copy(
            averageHeartRate = (current.averageHeartRate + newData.heartRate) / 2f,
            peakHeartRate = maxOf(current.peakHeartRate, newData.heartRate.toFloat()),
            averageFocusIntensity = (current.averageFocusIntensity + newData.focusIntensity) / 2f,
            peakFocusIntensity = maxOf(current.peakFocusIntensity, newData.focusIntensity),
            averageComprehension = (current.averageComprehension + newData.comprehensionLevel) / 2f,
            averageStressLevel = (current.averageStressLevel + newData.stressLevel) / 2f,
            averageInterestLevel = (current.averageInterestLevel + newData.interestLevel) / 2f,
            cognitiveEfficiency = calculateCognitiveEfficiency(newData)
        )
    }
    
    private fun updateAttentionPattern(
        current: AttentionPattern,
        newData: BiometricReadingData
    ): AttentionPattern {
        return current.copy(
            attentionSpan = current.attentionSpan + 1000L, // Each update represents ~1 second
            focusBreaks = if (newData.focusIntensity < 0.3f) current.focusBreaks + 1 else current.focusBreaks,
            deepFocusPeriods = if (newData.focusIntensity > 0.8f) current.deepFocusPeriods + 1 else current.deepFocusPeriods,
            distractionEvents = if (newData.eyeMovement > 0.7f) current.distractionEvents + 1 else current.distractionEvents,
            readingVelocity = calculateReadingVelocity(newData),
            comprehensionRate = newData.comprehensionLevel
        )
    }
    
    private fun calculateCognitiveEfficiency(data: BiometricReadingData): Float {
        // Efficiency is high comprehension with low stress and cognitive load
        val efficiency = (data.comprehensionLevel * 0.5f) + 
                        ((1.0f - data.stressLevel) * 0.3f) + 
                        ((1.0f - data.cognitiveLoad) * 0.2f)
        
        return min(efficiency, 1.0f)
    }
    
    private fun calculateReadingVelocity(data: BiometricReadingData): Float {
        // Reading velocity based on eye movement and focus
        return (data.eyeMovement * 0.6f) + (data.focusIntensity * 0.4f)
    }
    
    private fun calculateFinalCognitiveMetrics(session: ReadingSession): CognitiveMetrics {
        if (session.biometricData.isEmpty()) return session.cognitiveMetrics
        
        val data = session.biometricData
        
        return CognitiveMetrics(
            averageHeartRate = data.map { it.heartRate.toFloat() }.average().toFloat(),
            peakHeartRate = data.maxOfOrNull { it.heartRate.toFloat() } ?: 0f,
            averageFocusIntensity = data.map { it.focusIntensity }.average().toFloat(),
            peakFocusIntensity = data.maxOfOrNull { it.focusIntensity } ?: 0f,
            averageComprehension = data.map { it.comprehensionLevel }.average().toFloat(),
            averageStressLevel = data.map { it.stressLevel }.average().toFloat(),
            averageInterestLevel = data.map { it.interestLevel }.average().toFloat(),
            cognitiveEfficiency = data.map { calculateCognitiveEfficiency(it) }.average().toFloat()
        )
    }
    
    private fun calculateFinalAttentionPattern(session: ReadingSession): AttentionPattern {
        if (session.biometricData.isEmpty()) return session.attentionPattern
        
        val data = session.biometricData
        
        return AttentionPattern(
            attentionSpan = session.totalReadingTime,
            focusBreaks = data.count { it.focusIntensity < 0.3f },
            deepFocusPeriods = data.count { it.focusIntensity > 0.8f },
            distractionEvents = data.count { it.eyeMovement > 0.7f },
            readingVelocity = data.map { calculateReadingVelocity(it) }.average().toFloat(),
            comprehensionRate = data.map { it.comprehensionLevel }.average().toFloat()
        )
    }
    
    fun getReadingInsights(session: ReadingSession): List<String> {
        val insights = mutableListOf<String>()
        
        val metrics = session.cognitiveMetrics
        val pattern = session.attentionPattern
        
        // Heart rate insights
        when {
            metrics.averageHeartRate > 90 -> insights.add("High cognitive load detected during reading")
            metrics.averageHeartRate < 60 -> insights.add("Very relaxed reading state")
            else -> insights.add("Normal physiological response while reading")
        }
        
        // Focus insights
        when {
            metrics.averageFocusIntensity > 0.8f -> insights.add("Exceptional focus maintained throughout")
            metrics.averageFocusIntensity > 0.6f -> insights.add("Good focus levels observed")
            else -> insights.add("Consider reducing distractions for better focus")
        }
        
        // Comprehension insights
        when {
            metrics.averageComprehension > 0.8f -> insights.add("High comprehension achieved")
            metrics.averageComprehension > 0.6f -> insights.add("Good understanding of content")
            else -> insights.add("Content may be too complex for current reading level")
        }
        
        // Stress insights
        when {
            metrics.averageStressLevel > 0.7f -> insights.add("High stress detected - consider breaks")
            metrics.averageStressLevel < 0.3f -> insights.add("Low stress reading - optimal condition")
            else -> insights.add("Moderate stress levels during reading")
        }
        
        // Attention pattern insights
        if (pattern.focusBreaks > 5) {
            insights.add("Multiple focus breaks detected - consider shorter reading sessions")
        }
        
        if (pattern.deepFocusPeriods > 3) {
            insights.add("Multiple deep focus periods achieved - excellent concentration")
        }
        
        return insights
    }
}