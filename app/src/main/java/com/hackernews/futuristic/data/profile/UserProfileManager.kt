package com.hackernews.futuristic.data.profile

import com.hackernews.futuristic.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min
import kotlin.math.roundToInt

@Singleton
class UserProfileManager @Inject constructor() {
    
    private val _userProfile = MutableStateFlow(createDefaultProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()
    
    private val _achievements = MutableStateFlow(createDefaultAchievements())
    val achievements: StateFlow<List<Achievement>> = _achievements.asStateFlow()
    
    private val skillTreeExperience = mutableMapOf<TechCategory, Float>()
    
    init {
        // Initialize skill tree experience
        TechCategory.values().forEach { category ->
            skillTreeExperience[category] = 0f
        }
    }
    
    fun updateReadingStats(story: Story, readingTime: Long, completed: Boolean) {
        val currentProfile = _userProfile.value
        val updatedStats = currentProfile.readingStats.copy(
            totalStoriesRead = currentProfile.readingStats.totalStoriesRead + 1,
            totalReadingTime = currentProfile.readingStats.totalReadingTime + readingTime,
            averageSessionLength = calculateAverageSessionLength(
                currentProfile.readingStats.totalReadingTime + readingTime,
                currentProfile.readingStats.totalStoriesRead + 1
            )
        )
        
        val updatedProfile = currentProfile.copy(
            readingStats = updatedStats,
            reputationScore = calculateReputationScore(updatedStats),
            level = calculateLevel(updatedStats),
            lastActiveAt = System.currentTimeMillis()
        )
        
        _userProfile.value = updatedProfile
        
        // Update skill trees based on story topics
        updateSkillTrees(story)
        
        // Check for achievement unlocks
        checkAchievementUnlocks(story, readingTime, completed)
    }
    
    fun updateStoryAnalysis(analysis: StoryAnalysis) {
        val currentProfile = _userProfile.value
        val updatedDNA = updateUserDNA(currentProfile.userDNA, analysis)
        
        val updatedProfile = currentProfile.copy(
            userDNA = updatedDNA,
            lastActiveAt = System.currentTimeMillis()
        )
        
        _userProfile.value = updatedProfile
    }
    
    private fun updateSkillTrees(story: Story) {
        val storyTopics = analyzeStoryTopics(story)
        val currentProfile = _userProfile.value
        
        val updatedSkillTrees = currentProfile.skillTrees.map { skillTree ->
            val experienceGain = calculateExperienceGain(skillTree.category, storyTopics)
            
            if (experienceGain > 0) {
                val newExperience = min(
                    skillTree.experience + experienceGain,
                    skillTree.maxExperience
                )
                
                val newLevel = calculateSkillTreeLevel(newExperience, skillTree.maxExperience)
                val updatedSkills = updateSkillsInTree(skillTree.skills, newLevel)
                
                skillTree.copy(
                    experience = newExperience,
                    level = newLevel,
                    skills = updatedSkills
                )
            } else {
                skillTree
            }
        }
        
        val updatedProfile = currentProfile.copy(skillTrees = updatedSkillTrees)
        _userProfile.value = updatedProfile
    }
    
    private fun analyzeStoryTopics(story: Story): Map<TechCategory, Float> {
        val text = "${story.title} ${story.text ?: ""}".lowercase()
        val topicScores = mutableMapOf<TechCategory, Float>()
        
        // Simple keyword-based topic analysis
        val keywords = mapOf(
            TechCategory.ARTIFICIAL_INTELLIGENCE to listOf("ai", "machine learning", "neural", "chatgpt", "openai", "artificial intelligence"),
            TechCategory.CYBERSECURITY to listOf("security", "hack", "vulnerability", "breach", "encryption", "malware"),
            TechCategory.BLOCKCHAIN to listOf("blockchain", "bitcoin", "crypto", "ethereum", "nft", "web3"),
            TechCategory.WEB_DEVELOPMENT to listOf("javascript", "react", "angular", "vue", "html", "css", "web"),
            TechCategory.MOBILE_DEVELOPMENT to listOf("ios", "android", "mobile", "swift", "kotlin", "flutter"),
            TechCategory.SYSTEMS_PROGRAMMING to listOf("c++", "rust", "go", "systems", "kernel", "operating system"),
            TechCategory.DATA_SCIENCE to listOf("data", "analytics", "statistics", "python", "pandas", "numpy"),
            TechCategory.CLOUD_COMPUTING to listOf("aws", "azure", "cloud", "docker", "kubernetes", "serverless"),
            TechCategory.GAME_DEVELOPMENT to listOf("game", "unity", "unreal", "gamedev", "graphics", "rendering"),
            TechCategory.DEVOPS to listOf("devops", "ci/cd", "jenkins", "deployment", "infrastructure", "monitoring")
        )
        
        keywords.forEach { (category, keywordList) ->
            var score = 0f
            keywordList.forEach { keyword ->
                score += (text.split(keyword).size - 1) * 0.1f
            }
            topicScores[category] = score.coerceIn(0f, 1f)
        }
        
        return topicScores
    }
    
    private fun calculateExperienceGain(category: TechCategory, storyTopics: Map<TechCategory, Float>): Float {
        val baseExperience = 10f
        val topicRelevance = storyTopics[category] ?: 0f
        return baseExperience * topicRelevance
    }
    
    private fun calculateSkillTreeLevel(experience: Float, maxExperience: Float): Int {
        val progressPercentage = experience / maxExperience
        return (progressPercentage * 100).roundToInt().coerceIn(0, 100)
    }
    
    private fun updateSkillsInTree(skills: List<Skill>, treeLevel: Int): List<Skill> {
        return skills.map { skill ->
            val shouldUnlock = skill.prerequisites.all { prereq ->
                skills.find { it.id == prereq }?.isUnlocked == true
            } && treeLevel >= skill.level * 10
            
            if (shouldUnlock && !skill.isUnlocked) {
                skill.copy(isUnlocked = true)
            } else {
                skill
            }
        }
    }
    
    private fun calculateReputationScore(stats: ReadingStats): Float {
        val baseScore = stats.totalStoriesRead * 10f
        val timeBonus = (stats.totalReadingTime / 60f) * 5f // 5 points per hour
        val streakBonus = stats.currentReadingStreak * 20f
        val engagementBonus = stats.commentsWritten * 15f
        
        return (baseScore + timeBonus + streakBonus + engagementBonus).coerceIn(0f, 100000f)
    }
    
    private fun calculateLevel(stats: ReadingStats): Int {
        val totalXP = stats.totalStoriesRead * 100 + stats.commentsWritten * 50
        return (totalXP / 1000).coerceIn(1, 100)
    }
    
    private fun calculateAverageSessionLength(totalTime: Long, totalStories: Int): Float {
        return if (totalStories > 0) totalTime.toFloat() / totalStories else 0f
    }
    
    private fun updateUserDNA(currentDNA: UserDNA, analysis: StoryAnalysis): UserDNA {
        val updatedTopics = currentDNA.topicsOfInterest.toMutableMap()
        val topic = analysis.topicClassification.primaryTopic
        
        // Convert Topic to TechCategory mapping
        val techCategory = when (topic) {
            Topic.ARTIFICIAL_INTELLIGENCE -> TechCategory.ARTIFICIAL_INTELLIGENCE
            Topic.CYBERSECURITY -> TechCategory.CYBERSECURITY
            Topic.BLOCKCHAIN -> TechCategory.BLOCKCHAIN
            Topic.WEB_DEVELOPMENT -> TechCategory.WEB_DEVELOPMENT
            Topic.MOBILE_DEVELOPMENT -> TechCategory.MOBILE_DEVELOPMENT
            Topic.PROGRAMMING_LANGUAGES -> TechCategory.SYSTEMS_PROGRAMMING
            Topic.HARDWARE -> TechCategory.SYSTEMS_PROGRAMMING
            Topic.OPEN_SOURCE -> TechCategory.WEB_DEVELOPMENT
            Topic.PRIVACY -> TechCategory.CYBERSECURITY
            else -> null
        }
        
        techCategory?.let { category ->
            val currentInterest = updatedTopics[topic] ?: 0f
            val newInterest = (currentInterest + analysis.topicClassification.confidence * 0.1f).coerceIn(0f, 1f)
            updatedTopics[topic] = newInterest
        }
        
        return currentDNA.copy(
            topicsOfInterest = updatedTopics
        )
    }
    
    private fun checkAchievementUnlocks(story: Story, readingTime: Long, completed: Boolean) {
        val currentProfile = _userProfile.value
        val currentAchievements = _achievements.value
        
        val updatedAchievements = currentAchievements.map { achievement ->
            when (achievement.id) {
                "first_story" -> {
                    if (currentProfile.readingStats.totalStoriesRead >= 1 && !achievement.isUnlocked) {
                        achievement.copy(
                            unlockedAt = System.currentTimeMillis(),
                            progress = 1f
                        )
                    } else achievement
                }
                "story_marathon" -> {
                    val progress = (currentProfile.readingStats.totalStoriesRead / 100f).coerceIn(0f, 1f)
                    if (progress >= 1f && !achievement.isUnlocked) {
                        achievement.copy(
                            unlockedAt = System.currentTimeMillis(),
                            progress = 1f
                        )
                    } else {
                        achievement.copy(progress = progress)
                    }
                }
                "speed_reader" -> {
                    val avgTime = currentProfile.readingStats.averageSessionLength
                    if (avgTime > 0 && avgTime < 2f && !achievement.isUnlocked) {
                        achievement.copy(
                            unlockedAt = System.currentTimeMillis(),
                            progress = 1f
                        )
                    } else achievement
                }
                else -> achievement
            }
        }
        
        _achievements.value = updatedAchievements
        
        // Check for newly unlocked achievements
        val newlyUnlocked = updatedAchievements.filter { newAchievement ->
            val oldAchievement = currentAchievements.find { it.id == newAchievement.id }
            newAchievement.isUnlocked && oldAchievement?.isUnlocked == false
        }
        
        // Apply achievement rewards
        newlyUnlocked.forEach { achievement ->
            applyAchievementRewards(achievement)
        }
    }
    
    private fun applyAchievementRewards(achievement: Achievement) {
        val currentProfile = _userProfile.value
        var updatedProfile = currentProfile
        
        achievement.rewards.forEach { reward ->
            when (reward.type) {
                RewardType.REPUTATION_POINTS -> {
                    updatedProfile = updatedProfile.copy(
                        reputationScore = updatedProfile.reputationScore + reward.value
                    )
                }
                RewardType.SKILL_EXPERIENCE -> {
                    // Apply experience to all skill trees
                    val updatedSkillTrees = updatedProfile.skillTrees.map { skillTree ->
                        val newExperience = min(
                            skillTree.experience + reward.value,
                            skillTree.maxExperience
                        )
                        skillTree.copy(experience = newExperience)
                    }
                    updatedProfile = updatedProfile.copy(skillTrees = updatedSkillTrees)
                }
                RewardType.UNLOCK_SKILL -> {
                    // Implementation for unlocking specific skills
                }
                RewardType.COSMETIC_UNLOCK -> {
                    // Implementation for cosmetic unlocks
                }
            }
        }
        
        _userProfile.value = updatedProfile
    }
    
    private fun createDefaultProfile(): UserProfile {
        return UserProfile(
            userId = "user_001",
            username = "CyberHacker",
            reputationScore = 0f,
            level = 1,
            skillTrees = createDefaultSkillTrees(),
            achievements = emptyList(),
            userDNA = createDefaultUserDNA(),
            readingStats = createDefaultReadingStats(),
            preferences = createDefaultPreferences(),
            createdAt = System.currentTimeMillis(),
            lastActiveAt = System.currentTimeMillis()
        )
    }
    
    private fun createDefaultSkillTrees(): List<SkillTree> {
        return TechCategory.values().map { category ->
            SkillTree(
                category = category,
                level = 0,
                experience = 0f,
                maxExperience = 10000f,
                skills = createSkillsForCategory(category)
            )
        }
    }
    
    private fun createSkillsForCategory(category: TechCategory): List<Skill> {
        return when (category) {
            TechCategory.ARTIFICIAL_INTELLIGENCE -> listOf(
                Skill("ai_basics", "AI Basics", 1, 10, 0f, category, true, emptyList()),
                Skill("ml_fundamentals", "ML Fundamentals", 5, 10, 0f, category, false, listOf("ai_basics")),
                Skill("deep_learning", "Deep Learning", 10, 10, 0f, category, false, listOf("ml_fundamentals")),
                Skill("neural_networks", "Neural Networks", 15, 10, 0f, category, false, listOf("deep_learning")),
                Skill("ai_ethics", "AI Ethics", 20, 10, 0f, category, false, listOf("neural_networks"))
            )
            TechCategory.CYBERSECURITY -> listOf(
                Skill("sec_basics", "Security Basics", 1, 10, 0f, category, true, emptyList()),
                Skill("cryptography", "Cryptography", 5, 10, 0f, category, false, listOf("sec_basics")),
                Skill("penetration_testing", "Penetration Testing", 10, 10, 0f, category, false, listOf("cryptography")),
                Skill("incident_response", "Incident Response", 15, 10, 0f, category, false, listOf("penetration_testing")),
                Skill("security_architecture", "Security Architecture", 20, 10, 0f, category, false, listOf("incident_response"))
            )
            // Add more categories as needed
            else -> listOf(
                Skill("${category.name.lowercase()}_basics", "${category.displayName} Basics", 1, 10, 0f, category, true, emptyList())
            )
        }
    }
    
    private fun createDefaultUserDNA(): UserDNA {
        return UserDNA(
            topicsOfInterest = Topic.values().associateWith { 0f },
            readingPatterns = ReadingPattern(
                averageReadingTime = 0f,
                preferredTimeOfDay = TimeOfDay.EVENING,
                readingSpeed = ReadingSpeed.AVERAGE,
                completionRate = 0f,
                skipRate = 0f,
                bookmarkRate = 0f
            ),
            engagementStyle = EngagementStyle.CASUAL_READER,
            preferredContentTypes = emptyList(),
            activityHeatmap = (0..23).map { hour ->
                ActivityHour(hour, 0f)
            },
            socialBehavior = SocialBehavior(
                sharingFrequency = 0f,
                commentingFrequency = 0f,
                collaborationLevel = 0f,
                mentorshipLevel = 0f,
                controversyTolerance = 0.5f
            )
        )
    }
    
    private fun createDefaultReadingStats(): ReadingStats {
        return ReadingStats(
            totalStoriesRead = 0,
            totalReadingTime = 0,
            averageSessionLength = 0f,
            longestReadingStreak = 0,
            currentReadingStreak = 0,
            topicsExplored = 0,
            commentsWritten = 0,
            storiesShared = 0,
            bookmarksCreated = 0,
            searchesPerformed = 0,
            weeklyReadingGoal = 20,
            monthlyReadingGoal = 100,
            yearlyReadingGoal = 1000
        )
    }
    
    private fun createDefaultPreferences(): UserPreferences {
        return UserPreferences(
            theme = ThemePreference.CYBERPUNK_BLUE,
            notifications = NotificationPreferences(
                pushNotifications = true,
                emailDigest = false,
                trendingTopics = true,
                personalizedRecommendations = true,
                achievementUnlocks = true,
                socialActivity = false
            ),
            privacy = PrivacyPreferences(
                shareReadingStats = false,
                shareAchievements = true,
                allowDataCollection = true,
                anonymousAnalytics = true,
                publicProfile = false
            ),
            content = ContentPreferences(
                preferredLanguages = listOf("en"),
                contentFilters = emptyList(),
                minimumStoryScore = 0,
                maximumStoryAge = 24,
                showAnalysisCards = true,
                autoPlayAnimations = true
            ),
            interfacePrefs = InterfacePreferences(
                animationSpeed = AnimationSpeed.NORMAL,
                compactView = false,
                showLineNumbers = false,
                enableHapticFeedback = true,
                fontSize = FontSize.NORMAL,
                neuralAnalysisDefault = true
            )
        )
    }
    
    private fun createDefaultAchievements(): List<Achievement> {
        return listOf(
            Achievement(
                id = "first_story",
                title = "First Contact",
                description = "Read your first story",
                category = AchievementCategory.READING,
                rarity = AchievementRarity.COMMON,
                unlockedAt = null,
                progress = 0f,
                maxProgress = 1f,
                rewards = listOf(
                    AchievementReward(RewardType.REPUTATION_POINTS, 100f, "100 Reputation Points")
                )
            ),
            Achievement(
                id = "story_marathon",
                title = "Story Marathon",
                description = "Read 100 stories",
                category = AchievementCategory.READING,
                rarity = AchievementRarity.UNCOMMON,
                unlockedAt = null,
                progress = 0f,
                maxProgress = 100f,
                rewards = listOf(
                    AchievementReward(RewardType.REPUTATION_POINTS, 1000f, "1000 Reputation Points"),
                    AchievementReward(RewardType.SKILL_EXPERIENCE, 500f, "500 Skill Experience")
                )
            ),
            Achievement(
                id = "speed_reader",
                title = "Speed Reader",
                description = "Maintain average reading time under 2 minutes",
                category = AchievementCategory.READING,
                rarity = AchievementRarity.RARE,
                unlockedAt = null,
                progress = 0f,
                maxProgress = 1f,
                rewards = listOf(
                    AchievementReward(RewardType.REPUTATION_POINTS, 2000f, "2000 Reputation Points")
                )
            ),
            Achievement(
                id = "code_samurai",
                title = "Code Samurai",
                description = "Master all programming skill trees",
                category = AchievementCategory.KNOWLEDGE,
                rarity = AchievementRarity.LEGENDARY,
                unlockedAt = null,
                progress = 0f,
                maxProgress = 1f,
                rewards = listOf(
                    AchievementReward(RewardType.REPUTATION_POINTS, 10000f, "10000 Reputation Points"),
                    AchievementReward(RewardType.COSMETIC_UNLOCK, 1f, "Legendary Badge")
                )
            ),
            Achievement(
                id = "data_archaeologist",
                title = "Data Archaeologist",
                description = "Discover hidden patterns in 1000 stories",
                category = AchievementCategory.ANALYSIS,
                rarity = AchievementRarity.EPIC,
                unlockedAt = null,
                progress = 0f,
                maxProgress = 1000f,
                rewards = listOf(
                    AchievementReward(RewardType.REPUTATION_POINTS, 5000f, "5000 Reputation Points"),
                    AchievementReward(RewardType.UNLOCK_SKILL, 1f, "Unlock Advanced Analytics")
                )
            )
        )
    }
}