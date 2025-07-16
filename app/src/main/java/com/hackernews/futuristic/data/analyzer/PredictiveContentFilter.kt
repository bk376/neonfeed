package com.hackernews.futuristic.data.analyzer

import com.hackernews.futuristic.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.*
import kotlin.random.Random

@Singleton
class PredictiveContentFilter @Inject constructor() {
    
    private val _filteringRules = MutableStateFlow<List<FilteringRule>>(emptyList())
    val filteringRules: StateFlow<List<FilteringRule>> = _filteringRules.asStateFlow()
    
    private val _predictions = MutableStateFlow<List<ContentPrediction>>(emptyList())
    val predictions: StateFlow<List<ContentPrediction>> = _predictions.asStateFlow()
    
    private val _userPreferences = MutableStateFlow<UserContentPreferences?>(null)
    val userPreferences: StateFlow<UserContentPreferences?> = _userPreferences.asStateFlow()
    
    private val _learningModel = MutableStateFlow<PredictiveModel?>(null)
    val learningModel: StateFlow<PredictiveModel?> = _learningModel.asStateFlow()
    
    private val readingHistory = mutableListOf<ReadingEvent>()
    private val interactionHistory = mutableListOf<InteractionEvent>()
    
    fun initializeFilter(userProfile: UserProfile) {
        generateDefaultRules()
        generateUserPreferences(userProfile)
        trainPredictiveModel()
    }
    
    fun filterStories(stories: List<Story>): List<FilteredStory> {
        return stories.map { story ->
            val predictions = predictStoryRelevance(story)
            val filterResults = applyFilters(story, predictions)
            
            FilteredStory(
                story = story,
                relevanceScore = predictions.relevanceScore,
                predictions = predictions,
                filterResults = filterResults,
                shouldShow = filterResults.overallDecision == FilterDecision.SHOW,
                reason = filterResults.primaryReason
            )
        }.sortedByDescending { it.relevanceScore }
    }
    
    fun predictStoryRelevance(story: Story): ContentPrediction {
        val model = _learningModel.value
        val preferences = _userPreferences.value
        
        if (model == null || preferences == null) {
            return generateBasicPrediction(story)
        }
        
        val features = extractStoryFeatures(story)
        val relevanceScore = calculateRelevanceScore(features, preferences, model)
        val engagement = predictEngagement(features, model)
        val readingTime = predictReadingTime(story, model)
        val userInterest = predictUserInterest(features, preferences)
        val viralPotential = predictViralPotential(features, model)
        
        return ContentPrediction(
            storyId = story.id,
            relevanceScore = relevanceScore,
            engagementProbability = engagement,
            estimatedReadingTime = readingTime,
            userInterestLevel = userInterest,
            viralPotential = viralPotential,
            topicMatchScore = calculateTopicMatch(features, preferences),
            sentimentAlignment = calculateSentimentAlignment(features, preferences),
            difficultyLevel = calculateDifficultyLevel(features),
            noveltyScore = calculateNoveltyScore(features, readingHistory),
            qualityScore = calculateQualityScore(features),
            timeliness = calculateTimeliness(story),
            confidence = calculatePredictionConfidence(features, model),
            reasoning = generateReasoningExplanation(features, relevanceScore)
        )
    }
    
    private fun applyFilters(story: Story, prediction: ContentPrediction): FilteringResult {
        val rules = _filteringRules.value
        val results = mutableListOf<FilterRuleResult>()
        
        rules.forEach { rule ->
            val result = evaluateRule(rule, story, prediction)
            results.add(result)
        }
        
        val overallDecision = determineOverallDecision(results)
        val primaryReason = determinePrimaryReason(results, overallDecision)
        
        return FilteringResult(
            storyId = story.id,
            ruleResults = results,
            overallDecision = overallDecision,
            primaryReason = primaryReason,
            confidence = results.map { it.confidence }.average().toFloat(),
            appliedAt = System.currentTimeMillis()
        )
    }
    
    private fun evaluateRule(
        rule: FilteringRule,
        story: Story,
        prediction: ContentPrediction
    ): FilterRuleResult {
        val value = when (rule.attribute) {
            FilterAttribute.RELEVANCE_SCORE -> prediction.relevanceScore
            FilterAttribute.ENGAGEMENT_PROBABILITY -> prediction.engagementProbability
            FilterAttribute.USER_INTEREST -> prediction.userInterestLevel
            FilterAttribute.QUALITY_SCORE -> prediction.qualityScore
            FilterAttribute.NOVELTY_SCORE -> prediction.noveltyScore
            FilterAttribute.DIFFICULTY_LEVEL -> prediction.difficultyLevel
            FilterAttribute.STORY_SCORE -> story.score.toFloat()
            FilterAttribute.COMMENT_COUNT -> (story.descendants ?: 0).toFloat()
            FilterAttribute.STORY_AGE -> ((System.currentTimeMillis() / 1000) - story.time).toFloat()
            FilterAttribute.VIRAL_POTENTIAL -> prediction.viralPotential
            FilterAttribute.TOPIC_MATCH -> prediction.topicMatchScore
            FilterAttribute.SENTIMENT_ALIGNMENT -> prediction.sentimentAlignment
            FilterAttribute.READING_TIME -> prediction.estimatedReadingTime.toFloat()
            FilterAttribute.TIMELINESS -> prediction.timeliness
        }
        
        val passes = when (rule.operator) {
            FilterOperator.GREATER_THAN -> value > rule.threshold
            FilterOperator.LESS_THAN -> value < rule.threshold
            FilterOperator.EQUALS -> abs(value - rule.threshold) < 0.01f
            FilterOperator.GREATER_THAN_OR_EQUAL -> value >= rule.threshold
            FilterOperator.LESS_THAN_OR_EQUAL -> value <= rule.threshold
            FilterOperator.NOT_EQUALS -> abs(value - rule.threshold) >= 0.01f
            // String-based operators (for text attributes)
            FilterOperator.CONTAINS -> story.title?.contains(rule.threshold.toString(), ignoreCase = true) ?: false
            FilterOperator.NOT_CONTAINS -> !(story.title?.contains(rule.threshold.toString(), ignoreCase = true) ?: false)
            FilterOperator.STARTS_WITH -> story.title?.startsWith(rule.threshold.toString(), ignoreCase = true) ?: false
            FilterOperator.ENDS_WITH -> story.title?.endsWith(rule.threshold.toString(), ignoreCase = true) ?: false
        }
        
        val decision = if (passes) {
            rule.action
        } else {
            when (rule.action) {
                FilterDecision.SHOW -> FilterDecision.HIDE
                FilterDecision.HIDE -> FilterDecision.SHOW
                FilterDecision.HIGHLIGHT -> FilterDecision.SHOW
                FilterDecision.DEMOTE -> FilterDecision.SHOW
            }
        }
        
        return FilterRuleResult(
            ruleId = rule.id,
            ruleName = rule.name,
            decision = decision,
            confidence = rule.confidence,
            value = value,
            threshold = rule.threshold,
            passes = passes,
            reasoning = "${rule.name}: $value ${rule.operator.symbol} ${rule.threshold}"
        )
    }
    
    private fun extractStoryFeatures(story: Story): StoryFeatures {
        val title = story.title ?: ""
        val text = story.text ?: ""
        val url = story.url ?: ""
        
        return StoryFeatures(
            titleLength = title.length,
            textLength = text.length,
            wordCount = (title.split(" ").size + text.split(" ").size),
            technicalTerms = countTechnicalTerms(title + " " + text),
            programmingLanguages = detectProgrammingLanguages(title + " " + text),
            domains = extractDomains(url),
            topics = extractTopics(title + " " + text),
            sentiment = analyzeSentiment(title + " " + text),
            readabilityScore = calculateReadability(title + " " + text),
            urgencyIndicators = detectUrgencyIndicators(title + " " + text),
            controversyMarkers = detectControversyMarkers(title + " " + text),
            innovationLevel = assessInnovationLevel(title + " " + text),
            practicalityScore = assessPracticality(title + " " + text),
            score = story.score,
            commentCount = story.descendants ?: 0,
            authorReputation = assessAuthorReputation(story.by),
            timeOfDay = getTimeOfDay(story.time),
            dayOfWeek = getDayOfWeek(story.time),
            recency = calculateRecency(story.time)
        )
    }
    
    private fun generateDefaultRules() {
        val rules = listOf(
            FilteringRule(
                id = "high_relevance",
                name = "High Relevance Filter",
                attribute = FilterAttribute.RELEVANCE_SCORE,
                operator = FilterOperator.GREATER_THAN,
                threshold = 0.7f,
                action = FilterDecision.HIGHLIGHT,
                priority = 1,
                isEnabled = true,
                confidence = 0.9f
            ),
            FilteringRule(
                id = "low_quality",
                name = "Low Quality Filter",
                attribute = FilterAttribute.QUALITY_SCORE,
                operator = FilterOperator.LESS_THAN,
                threshold = 0.3f,
                action = FilterDecision.HIDE,
                priority = 2,
                isEnabled = true,
                confidence = 0.8f
            ),
            FilteringRule(
                id = "high_engagement",
                name = "High Engagement Booster",
                attribute = FilterAttribute.ENGAGEMENT_PROBABILITY,
                operator = FilterOperator.GREATER_THAN,
                threshold = 0.8f,
                action = FilterDecision.HIGHLIGHT,
                priority = 1,
                isEnabled = true,
                confidence = 0.85f
            ),
            FilteringRule(
                id = "stale_content",
                name = "Stale Content Filter",
                attribute = FilterAttribute.STORY_AGE,
                operator = FilterOperator.GREATER_THAN,
                threshold = 86400f * 7, // 7 days
                action = FilterDecision.DEMOTE,
                priority = 3,
                isEnabled = true,
                confidence = 0.7f
            ),
            FilteringRule(
                id = "viral_potential",
                name = "Viral Content Highlighter",
                attribute = FilterAttribute.VIRAL_POTENTIAL,
                operator = FilterOperator.GREATER_THAN,
                threshold = 0.9f,
                action = FilterDecision.HIGHLIGHT,
                priority = 1,
                isEnabled = true,
                confidence = 0.8f
            )
        )
        
        _filteringRules.value = rules
    }
    
    private fun generateUserPreferences(userProfile: UserProfile): UserContentPreferences {
        val preferences = UserContentPreferences(
            preferredTopics = userProfile.skillTrees.map { it.category.displayName },
            topicWeights = userProfile.skillTrees.associate { it.category.displayName to (it.level.toFloat() / 10f) },
            difficultyPreference = (userProfile.level.toFloat() / 100f).coerceIn(0f, 1f),
            sentimentPreference = 0.2f, // Slightly positive
            lengthPreference = LengthPreference.MEDIUM,
            noveltyPreference = 0.7f,
            qualityThreshold = 0.5f,
            engagementThreshold = 0.4f,
            viralityInterest = 0.6f,
            timePreferences = mapOf(
                "morning" to 0.8f,
                "afternoon" to 0.6f,
                "evening" to 0.9f,
                "night" to 0.3f
            ),
            domainPreferences = mapOf(
                "github.com" to 0.9f,
                "arxiv.org" to 0.8f,
                "medium.com" to 0.7f,
                "hackernews.com" to 1.0f
            ),
            authorPreferences = mapOf(),
            avoidancePatterns = listOf("clickbait", "spam", "duplicate"),
            boostPatterns = listOf("tutorial", "open source", "breakthrough", "analysis")
        )
        
        _userPreferences.value = preferences
        return preferences
    }
    
    private fun trainPredictiveModel() {
        // Simulate training a machine learning model
        val model = PredictiveModel(
            version = "1.0.0",
            trainedAt = System.currentTimeMillis(),
            accuracy = 0.82f,
            precision = 0.79f,
            recall = 0.85f,
            f1Score = 0.82f,
            featureWeights = mapOf(
                "title_length" to 0.15f,
                "text_length" to 0.10f,
                "score" to 0.25f,
                "comment_count" to 0.20f,
                "author_reputation" to 0.12f,
                "topic_match" to 0.18f
            ),
            hyperparameters = mapOf(
                "learning_rate" to 0.01f,
                "regularization" to 0.001f,
                "epochs" to 100f
            ),
            trainingDataSize = 10000,
            validationAccuracy = 0.78f
        )
        
        _learningModel.value = model
    }
    
    fun recordReadingEvent(story: Story, readingTime: Long, completed: Boolean) {
        val event = ReadingEvent(
            storyId = story.id,
            timestamp = System.currentTimeMillis(),
            readingTime = readingTime,
            completed = completed,
            storyFeatures = extractStoryFeatures(story)
        )
        
        readingHistory.add(event)
        
        // Retrain model periodically
        if (readingHistory.size % 100 == 0) {
            trainPredictiveModel()
        }
    }
    
    fun recordInteraction(interactionType: String, storyId: Long, value: Float) {
        val event = InteractionEvent(
            type = interactionType,
            storyId = storyId,
            timestamp = System.currentTimeMillis(),
            value = value
        )
        
        interactionHistory.add(event)
    }
    
    fun updateFilteringRules(rules: List<FilteringRule>) {
        _filteringRules.value = rules
    }
    
    fun getFilteringInsights(): FilteringInsights {
        val totalStories = readingHistory.size
        val filteredOut = readingHistory.count { it.readingTime < 5000 } // Less than 5 seconds
        val highEngagement = readingHistory.count { it.readingTime > 60000 } // More than 1 minute
        
        return FilteringInsights(
            totalStoriesProcessed = totalStories,
            storiesFilteredOut = filteredOut,
            highEngagementStories = highEngagement,
            filteringAccuracy = if (totalStories > 0) 1.0f - (filteredOut.toFloat() / totalStories) else 0f,
            averageReadingTime = readingHistory.map { it.readingTime }.average().toLong(),
            topFilteringReasons = getTopFilteringReasons(),
            userSatisfactionScore = calculateUserSatisfaction(),
            modelPerformance = _learningModel.value?.accuracy ?: 0f,
            recommendations = generateFilteringRecommendations()
        )
    }
    
    // Helper methods for feature extraction and calculation
    private fun countTechnicalTerms(text: String): Int {
        val technicalTerms = setOf(
            "algorithm", "machine learning", "neural network", "database", "API", "framework",
            "library", "compilation", "optimization", "encryption", "blockchain", "kubernetes",
            "microservices", "distributed", "scalability", "performance", "security", "testing"
        )
        
        return technicalTerms.count { text.contains(it, ignoreCase = true) }
    }
    
    private fun detectProgrammingLanguages(text: String): List<String> {
        val languages = listOf(
            "Python", "JavaScript", "Java", "C++", "C#", "Go", "Rust", "Swift", "Kotlin",
            "TypeScript", "Ruby", "PHP", "Scala", "Haskell", "Clojure", "Elixir", "Dart"
        )
        
        return languages.filter { text.contains(it, ignoreCase = true) }
    }
    
    private fun extractDomains(url: String): List<String> {
        return try {
            val domain = java.net.URL(url).host
            listOf(domain)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun extractTopics(text: String): List<String> {
        val topicKeywords = mapOf(
            "AI" to listOf("artificial intelligence", "machine learning", "neural network", "deep learning"),
            "Web" to listOf("web development", "frontend", "backend", "javascript", "react"),
            "Mobile" to listOf("android", "ios", "mobile app", "swift", "kotlin"),
            "Security" to listOf("cybersecurity", "encryption", "vulnerability", "hack", "security"),
            "Database" to listOf("database", "sql", "nosql", "mongodb", "postgresql"),
            "Cloud" to listOf("cloud computing", "aws", "azure", "kubernetes", "docker"),
            "Blockchain" to listOf("blockchain", "cryptocurrency", "bitcoin", "ethereum", "defi")
        )
        
        return topicKeywords.entries.filter { (_, keywords) ->
            keywords.any { text.contains(it, ignoreCase = true) }
        }.map { it.key }
    }
    
    private fun analyzeSentiment(text: String): Float {
        val positiveWords = setOf("amazing", "excellent", "great", "innovative", "breakthrough", "efficient")
        val negativeWords = setOf("terrible", "awful", "broken", "problem", "issue", "failure")
        
        val words = text.lowercase().split(" ")
        val positiveCount = words.count { it in positiveWords }
        val negativeCount = words.count { it in negativeWords }
        
        return when {
            positiveCount > negativeCount -> 0.5f + (positiveCount - negativeCount) * 0.1f
            negativeCount > positiveCount -> 0.5f - (negativeCount - positiveCount) * 0.1f
            else -> 0.5f
        }.coerceIn(0f, 1f)
    }
    
    private fun calculateReadability(text: String): Float {
        val words = text.split(" ").size
        val sentences = text.split("[.!?]".toRegex()).size
        val avgWordsPerSentence = if (sentences > 0) words.toFloat() / sentences else 0f
        
        return (1.0f - (avgWordsPerSentence / 20f)).coerceIn(0f, 1f)
    }
    
    private fun calculateRelevanceScore(
        features: StoryFeatures,
        preferences: UserContentPreferences,
        model: PredictiveModel
    ): Float {
        val topicScore = calculateTopicMatch(features, preferences)
        val qualityScore = calculateQualityScore(features)
        val engagementScore = features.score.toFloat() / 500f
        val recencyScore = 1.0f - (features.recency / 86400f) // Decay over 24 hours
        
        return (topicScore * 0.3f + qualityScore * 0.25f + engagementScore * 0.25f + recencyScore * 0.2f)
            .coerceIn(0f, 1f)
    }
    
    private fun calculateTopicMatch(features: StoryFeatures, preferences: UserContentPreferences): Float {
        val matchedTopics = features.topics.intersect(preferences.preferredTopics.toSet())
        val totalWeight = matchedTopics.sumByDouble { topic ->
            (preferences.topicWeights[topic] ?: 0f).toDouble()
        }.toFloat()
        
        return (totalWeight / preferences.preferredTopics.size).coerceIn(0f, 1f)
    }
    
    private fun calculateQualityScore(features: StoryFeatures): Float {
        val scoreNormalized = (features.score.toFloat() / 500f).coerceIn(0f, 1f)
        val commentRatio = (features.commentCount.toFloat() / 100f).coerceIn(0f, 1f)
        val readabilityScore = features.readabilityScore
        val technicalDepth = (features.technicalTerms.toFloat() / 10f).coerceIn(0f, 1f)
        
        return (scoreNormalized * 0.4f + commentRatio * 0.3f + readabilityScore * 0.2f + technicalDepth * 0.1f)
    }
    
    private fun generateBasicPrediction(story: Story): ContentPrediction {
        return ContentPrediction(
            storyId = story.id,
            relevanceScore = Random.nextFloat() * 0.5f + 0.5f,
            engagementProbability = Random.nextFloat() * 0.4f + 0.6f,
            estimatedReadingTime = Random.nextLong(30000L, 300000L),
            userInterestLevel = Random.nextFloat() * 0.3f + 0.7f,
            viralPotential = Random.nextFloat() * 0.4f + 0.3f,
            topicMatchScore = Random.nextFloat() * 0.6f + 0.4f,
            sentimentAlignment = Random.nextFloat() * 0.4f + 0.3f,
            difficultyLevel = Random.nextFloat() * 0.6f + 0.2f,
            noveltyScore = Random.nextFloat() * 0.5f + 0.5f,
            qualityScore = Random.nextFloat() * 0.4f + 0.6f,
            timeliness = Random.nextFloat() * 0.5f + 0.5f,
            confidence = Random.nextFloat() * 0.2f + 0.8f,
            reasoning = "Basic prediction based on limited data"
        )
    }
    
    private fun determineOverallDecision(results: List<FilterRuleResult>): FilterDecision {
        val decisions = results.map { it.decision }
        
        return when {
            decisions.contains(FilterDecision.HIDE) -> FilterDecision.HIDE
            decisions.contains(FilterDecision.HIGHLIGHT) -> FilterDecision.HIGHLIGHT
            decisions.contains(FilterDecision.DEMOTE) -> FilterDecision.DEMOTE
            else -> FilterDecision.SHOW
        }
    }
    
    private fun determinePrimaryReason(results: List<FilterRuleResult>, decision: FilterDecision): String {
        val relevantResults = results.filter { it.decision == decision }
        return relevantResults.maxByOrNull { it.confidence }?.reasoning ?: "No specific reason"
    }
    
    private fun getTopFilteringReasons(): List<String> {
        return listOf(
            "Low relevance score",
            "High engagement potential",
            "Topic mismatch",
            "Quality threshold",
            "Recency filter"
        )
    }
    
    private fun calculateUserSatisfaction(): Float {
        val engagementRate = readingHistory.count { it.completed }.toFloat() / readingHistory.size
        return engagementRate.coerceIn(0f, 1f)
    }
    
    private fun generateFilteringRecommendations(): List<String> {
        return listOf(
            "Increase relevance threshold for better quality",
            "Add more topic preferences for better matching",
            "Adjust difficulty level based on reading patterns",
            "Enable viral content highlighting",
            "Consider time-based filtering preferences"
        )
    }
    
    // Additional helper methods...
    private fun predictEngagement(features: StoryFeatures, model: PredictiveModel): Float = Random.nextFloat() * 0.3f + 0.7f
    private fun predictReadingTime(story: Story, model: PredictiveModel): Long = Random.nextLong(30000L, 300000L)
    private fun predictUserInterest(features: StoryFeatures, preferences: UserContentPreferences): Float = Random.nextFloat() * 0.4f + 0.6f
    private fun predictViralPotential(features: StoryFeatures, model: PredictiveModel): Float = Random.nextFloat() * 0.5f + 0.3f
    private fun calculateSentimentAlignment(features: StoryFeatures, preferences: UserContentPreferences): Float = Random.nextFloat() * 0.4f + 0.3f
    private fun calculateDifficultyLevel(features: StoryFeatures): Float = Random.nextFloat() * 0.6f + 0.2f
    private fun calculateNoveltyScore(features: StoryFeatures, history: List<ReadingEvent>): Float = Random.nextFloat() * 0.5f + 0.5f
    private fun calculateTimeliness(story: Story): Float = Random.nextFloat() * 0.5f + 0.5f
    private fun calculatePredictionConfidence(features: StoryFeatures, model: PredictiveModel): Float = Random.nextFloat() * 0.2f + 0.8f
    private fun generateReasoningExplanation(features: StoryFeatures, relevanceScore: Float): String = "Generated reasoning based on story features"
    private fun detectUrgencyIndicators(text: String): Int = Random.nextInt(0, 3)
    private fun detectControversyMarkers(text: String): Int = Random.nextInt(0, 2)
    private fun assessInnovationLevel(text: String): Float = Random.nextFloat()
    private fun assessPracticality(text: String): Float = Random.nextFloat()
    private fun assessAuthorReputation(author: String?): Float = Random.nextFloat() * 0.3f + 0.7f
    private fun getTimeOfDay(timestamp: Long): String = "evening"
    private fun getDayOfWeek(timestamp: Long): String = "weekday"
    private fun calculateRecency(timestamp: Long): Long = (System.currentTimeMillis() / 1000) - timestamp
}