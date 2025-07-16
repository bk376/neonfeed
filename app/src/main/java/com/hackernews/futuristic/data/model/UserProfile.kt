package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color

data class UserProfile(
    val userId: String,
    val username: String,
    val reputationScore: Float,
    val level: Int,
    val skillTrees: List<SkillTree>,
    val achievements: List<Achievement>,
    val userDNA: UserDNA,
    val readingStats: ReadingStats,
    val preferences: UserPreferences,
    val createdAt: Long,
    val lastActiveAt: Long
)

data class SkillTree(
    val category: TechCategory,
    val level: Int,
    val experience: Float,
    val maxExperience: Float,
    val skills: List<Skill>
) {
    val progressPercentage: Float
        get() = (experience / maxExperience).coerceIn(0f, 1f)
}

enum class TechCategory(val displayName: String, val color: Color, val icon: String) {
    ARTIFICIAL_INTELLIGENCE("AI/ML", Color(0xFF00D4FF), "🤖"),
    CYBERSECURITY("Security", Color(0xFFFF0040), "🔒"),
    BLOCKCHAIN("Blockchain", Color(0xFFFFD700), "⛓️"),
    WEB_DEVELOPMENT("Web Dev", Color(0xFF00FF88), "🌐"),
    MOBILE_DEVELOPMENT("Mobile", Color(0xFF8B5CF6), "📱"),
    SYSTEMS_PROGRAMMING("Systems", Color(0xFFFF6B35), "⚙️"),
    DATA_SCIENCE("Data Science", Color(0xFF4A90E2), "📊"),
    CLOUD_COMPUTING("Cloud", Color(0xFF32CD32), "☁️"),
    GAME_DEVELOPMENT("Game Dev", Color(0xFFFF1493), "🎮"),
    DEVOPS("DevOps", Color(0xFF8B4513), "🔧")
}

data class Skill(
    val id: String,
    val name: String,
    val level: Int,
    val maxLevel: Int,
    val experience: Float,
    val category: TechCategory,
    val isUnlocked: Boolean,
    val prerequisites: List<String>
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val category: AchievementCategory,
    val rarity: AchievementRarity,
    val unlockedAt: Long?,
    val progress: Float,
    val maxProgress: Float,
    val rewards: List<AchievementReward>
) {
    val isUnlocked: Boolean
        get() = unlockedAt != null
        
    val progressPercentage: Float
        get() = (progress / maxProgress).coerceIn(0f, 1f)
}

enum class AchievementCategory(val displayName: String, val color: Color) {
    READING("Reading", Color(0xFF00D4FF)),
    ENGAGEMENT("Engagement", Color(0xFF00FF88)),
    KNOWLEDGE("Knowledge", Color(0xFFFFD700)),
    SOCIAL("Social", Color(0xFF8B5CF6)),
    EXPLORATION("Exploration", Color(0xFFFF6B35)),
    ANALYSIS("Analysis", Color(0xFFFF0040))
}

enum class AchievementRarity(val displayName: String, val color: Color, val multiplier: Float) {
    COMMON("Common", Color(0xFF808080), 1.0f),
    UNCOMMON("Uncommon", Color(0xFF00FF88), 1.5f),
    RARE("Rare", Color(0xFF00D4FF), 2.0f),
    EPIC("Epic", Color(0xFF8B5CF6), 3.0f),
    LEGENDARY("Legendary", Color(0xFFFFD700), 5.0f),
    MYTHICAL("Mythical", Color(0xFFFF0040), 10.0f)
}

data class AchievementReward(
    val type: RewardType,
    val value: Float,
    val description: String
)

enum class RewardType {
    REPUTATION_POINTS,
    SKILL_EXPERIENCE,
    UNLOCK_SKILL,
    COSMETIC_UNLOCK
}

data class UserDNA(
    val topicsOfInterest: Map<Topic, Float>,
    val readingPatterns: ReadingPattern,
    val engagementStyle: EngagementStyle,
    val preferredContentTypes: List<ContentType>,
    val activityHeatmap: List<ActivityHour>,
    val socialBehavior: SocialBehavior
)

data class ReadingPattern(
    val averageReadingTime: Float, // in minutes
    val preferredTimeOfDay: TimeOfDay,
    val readingSpeed: ReadingSpeed,
    val completionRate: Float, // percentage of stories read to completion
    val skipRate: Float, // percentage of stories skipped
    val bookmarkRate: Float // percentage of stories bookmarked
)

enum class TimeOfDay(val displayName: String, val hour: Int) {
    EARLY_MORNING("Early Morning", 6),
    MORNING("Morning", 9),
    MIDDAY("Midday", 12),
    AFTERNOON("Afternoon", 15),
    EVENING("Evening", 18),
    NIGHT("Night", 21),
    LATE_NIGHT("Late Night", 24)
}

enum class ReadingSpeed(val displayName: String, val wpm: Int) {
    SLOW("Slow", 200),
    AVERAGE("Average", 250),
    FAST("Fast", 300),
    VERY_FAST("Very Fast", 400),
    SPEED_READER("Speed Reader", 500)
}

enum class EngagementStyle(val displayName: String, val color: Color) {
    LURKER("Lurker", Color(0xFF808080)),
    CASUAL_READER("Casual Reader", Color(0xFF00FF88)),
    ACTIVE_COMMENTER("Active Commenter", Color(0xFF00D4FF)),
    CURATOR("Curator", Color(0xFFFFD700)),
    INFLUENCER("Influencer", Color(0xFFFF0040)),
    EXPERT("Expert", Color(0xFF8B5CF6))
}

enum class ContentType(val displayName: String) {
    TECHNICAL_ARTICLES("Technical Articles"),
    NEWS_UPDATES("News Updates"),
    TUTORIALS("Tutorials"),
    OPINION_PIECES("Opinion Pieces"),
    RESEARCH_PAPERS("Research Papers"),
    PRODUCT_LAUNCHES("Product Launches"),
    DISCUSSIONS("Discussions"),
    SHOW_AND_TELL("Show and Tell")
}

data class ActivityHour(
    val hour: Int,
    val activityLevel: Float // 0.0 to 1.0
)

data class SocialBehavior(
    val sharingFrequency: Float,
    val commentingFrequency: Float,
    val collaborationLevel: Float,
    val mentorshipLevel: Float,
    val controversyTolerance: Float
)

data class ReadingStats(
    val totalStoriesRead: Int,
    val totalReadingTime: Long, // in minutes
    val averageSessionLength: Float, // in minutes
    val longestReadingStreak: Int, // in days
    val currentReadingStreak: Int, // in days
    val topicsExplored: Int,
    val commentsWritten: Int,
    val storiesShared: Int,
    val bookmarksCreated: Int,
    val searchesPerformed: Int,
    val weeklyReadingGoal: Int,
    val monthlyReadingGoal: Int,
    val yearlyReadingGoal: Int
)

data class UserPreferences(
    val theme: ThemePreference,
    val notifications: NotificationPreferences,
    val privacy: PrivacyPreferences,
    val content: ContentPreferences,
    val interfacePrefs: InterfacePreferences
)

enum class ThemePreference(val displayName: String) {
    CYBERPUNK_BLUE("Cyberpunk Blue"),
    CYBERPUNK_GREEN("Cyberpunk Green"),
    CYBERPUNK_PURPLE("Cyberpunk Purple"),
    CYBERPUNK_YELLOW("Cyberpunk Yellow"),
    MATRIX_GREEN("Matrix Green"),
    NEON_PINK("Neon Pink"),
    CLASSIC_DARK("Classic Dark")
}

data class NotificationPreferences(
    val pushNotifications: Boolean,
    val emailDigest: Boolean,
    val trendingTopics: Boolean,
    val personalizedRecommendations: Boolean,
    val achievementUnlocks: Boolean,
    val socialActivity: Boolean
)

data class PrivacyPreferences(
    val shareReadingStats: Boolean,
    val shareAchievements: Boolean,
    val allowDataCollection: Boolean,
    val anonymousAnalytics: Boolean,
    val publicProfile: Boolean
)

data class ContentPreferences(
    val preferredLanguages: List<String>,
    val contentFilters: List<ContentFilter>,
    val minimumStoryScore: Int,
    val maximumStoryAge: Int, // in hours
    val showAnalysisCards: Boolean,
    val autoPlayAnimations: Boolean
)

data class ContentFilter(
    val type: FilterType,
    val keywords: List<String>,
    val enabled: Boolean
)

enum class FilterType {
    INCLUDE_KEYWORDS,
    EXCLUDE_KEYWORDS,
    INCLUDE_DOMAINS,
    EXCLUDE_DOMAINS
}

data class InterfacePreferences(
    val animationSpeed: AnimationSpeed,
    val compactView: Boolean,
    val showLineNumbers: Boolean,
    val enableHapticFeedback: Boolean,
    val fontSize: FontSize,
    val neuralAnalysisDefault: Boolean
)

enum class AnimationSpeed(val displayName: String, val multiplier: Float) {
    SLOW("Slow", 0.5f),
    NORMAL("Normal", 1.0f),
    FAST("Fast", 1.5f),
    VERY_FAST("Very Fast", 2.0f),
    DISABLED("Disabled", 0f)
}

enum class FontSize(val displayName: String, val scale: Float) {
    SMALL("Small", 0.8f),
    NORMAL("Normal", 1.0f),
    LARGE("Large", 1.2f),
    EXTRA_LARGE("Extra Large", 1.4f)
}