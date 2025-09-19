package com.hackernews.futuristic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackernews.futuristic.ui.detail.SimpleStoryDetailScreen
import com.hackernews.futuristic.ui.search.CrashProofSearchScreen
import com.hackernews.futuristic.ui.stories.StoriesScreenFixed
import com.hackernews.futuristic.ui.settings.SettingsScreen
import com.hackernews.futuristic.ui.components.PredictiveFilteringDashboard
import com.hackernews.futuristic.ui.browser.RetroWebViewScreen
import com.hackernews.futuristic.ui.summary.DailySummaryScreen
import com.hackernews.futuristic.ui.bookmarks.BookmarksScreen
import com.hackernews.futuristic.ui.user.UserProfileScreen
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.theme.HackerNewsFuturisticTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerNewsFuturisticTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HackerNewsApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HackerNewsApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scope = rememberCoroutineScope()
    
    // Scroll-to-top callbacks for each screen
    var scrollToTopStories: (suspend () -> Unit)? by remember { mutableStateOf(null) }
    var scrollToTopSearch: (suspend () -> Unit)? by remember { mutableStateOf(null) }
    var scrollToTopSummary: (suspend () -> Unit)? by remember { mutableStateOf(null) }
    
    val bottomNavItems = listOf(
        BottomNavItem("stories", "Stories", Icons.Default.Home),
        BottomNavItem("search", "Search", Icons.Default.Search),
        BottomNavItem("bookmarks", "Bookmarks", Icons.Default.Bookmark),
        BottomNavItem("summary", "Daily AI", Icons.Default.Dashboard),
        BottomNavItem("settings", "Settings", Icons.Default.Settings)
    )
    
    val showBottomNav = currentDestination?.route in bottomNavItems.map { it.route }
    
    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = null,
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                // Check if we're already on the destination and trigger scroll-to-top
                                val isAlreadyOnDestination = currentDestination?.hierarchy?.any { it.route == item.route } == true
                                
                                if (isAlreadyOnDestination) {
                                    // Trigger scroll to top for the current screen
                                    scope.launch {
                                        when (item.route) {
                                            "stories" -> scrollToTopStories?.invoke()
                                            "search" -> scrollToTopSearch?.invoke()
                                            "summary" -> scrollToTopSummary?.invoke()
                                        }
                                    }
                                } else {
                                    // Navigate to the destination
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "stories",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("stories") {
                StoriesScreenFixed(
                    onStoryClick = { story ->
                        navController.navigate("story/${story.id}")
                    },
                    onUrlClick = { url, title ->
                        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        val encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString())
                        navController.navigate("browser/$encodedUrl/$encodedTitle")
                    },
                    onAuthorClick = { username ->
                        navController.navigate("user/$username")
                    },
                    onScrollToTopCallback = { callback ->
                        scrollToTopStories = callback
                    }
                )
            }
            
            composable("search") {
                CrashProofSearchScreen(
                    onBackClick = { navController.navigate("stories") },
                    onStoryClick = { story ->
                        navController.navigate("story/${story.id}")
                    },
                    onAuthorClick = { username ->
                        navController.navigate("user/$username")
                    }
                )
            }
            
            composable("bookmarks") {
                BookmarksScreen(
                    onBackClick = { /* No back action needed for bottom nav */ },
                    onStoryClick = { story ->
                        navController.navigate("story/${story.id}")
                    },
                    onAuthorClick = { username ->
                        navController.navigate("user/$username")
                    }
                )
            }
            
            composable("summary") {
                DailySummaryScreen(
                    onBackClick = { /* No back action needed for bottom nav */ },
                    onStoryClick = { story ->
                        navController.navigate("story/${story.id}")
                    },
                    onAuthorClick = { username ->
                        navController.navigate("user/$username")
                    },
                    onScrollToTopCallback = { callback ->
                        scrollToTopSummary = callback
                    }
                )
            }
            
            composable("dashboard") {
                // Mock data for demonstration
                val mockInsights = FilteringInsights(
                    totalStoriesProcessed = 1250,
                    storiesFilteredOut = 325,
                    highEngagementStories = 89,
                    filteringAccuracy = 0.87f,
                    averageReadingTime = 240000L,
                    topFilteringReasons = listOf("Low relevance", "Quality threshold", "User preference"),
                    userSatisfactionScore = 0.92f,
                    modelPerformance = 0.85f,
                    recommendations = listOf("Increase neural analysis sensitivity", "Update content preferences")
                )
                
                val mockPredictions = listOf(
                    ContentPrediction(
                        storyId = 1,
                        relevanceScore = 0.89f,
                        engagementProbability = 0.76f,
                        estimatedReadingTime = 180000L,
                        userInterestLevel = 0.84f,
                        viralPotential = 0.62f,
                        topicMatchScore = 0.91f,
                        sentimentAlignment = 0.73f,
                        difficultyLevel = 0.45f,
                        noveltyScore = 0.67f,
                        qualityScore = 0.88f,
                        timeliness = 0.95f,
                        confidence = 0.82f,
                        reasoning = "High technical relevance with strong engagement indicators"
                    )
                )
                
                val mockRules = listOf(
                    FilteringRule(
                        id = "rule1",
                        name = "High Quality Filter",
                        attribute = FilterAttribute.QUALITY_SCORE,
                        operator = FilterOperator.GREATER_THAN,
                        threshold = 0.7f,
                        action = FilterDecision.SHOW,
                        priority = 1,
                        isEnabled = true,
                        confidence = 0.9f
                    )
                )
                
                val mockPreferences = UserContentPreferences(
                    preferredTopics = listOf("Technology", "AI", "Programming"),
                    topicWeights = mapOf("Technology" to 0.9f, "AI" to 0.8f),
                    difficultyPreference = 0.7f,
                    sentimentPreference = 0.5f,
                    lengthPreference = LengthPreference.MEDIUM,
                    noveltyPreference = 0.6f,
                    qualityThreshold = 0.8f,
                    engagementThreshold = 0.7f,
                    viralityInterest = 0.5f,
                    timePreferences = mapOf("morning" to 0.8f, "evening" to 0.9f),
                    domainPreferences = mapOf("github.com" to 0.9f, "stackoverflow.com" to 0.8f),
                    authorPreferences = mapOf(),
                    avoidancePatterns = listOf("clickbait", "ads"),
                    boostPatterns = listOf("open source", "tutorial")
                )
                
                PredictiveFilteringDashboard(
                    insights = mockInsights,
                    predictions = mockPredictions,
                    filteringRules = mockRules,
                    userPreferences = mockPreferences,
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            composable("settings") {
                SettingsScreen(
                    onBackClick = { navController.navigate("stories") }
                )
            }
            
            composable("story/{storyId}") { backStackEntry ->
                val storyId = backStackEntry.arguments?.getString("storyId")?.toLongOrNull()
                if (storyId != null) {
                    SimpleStoryDetailScreen(
                        storyId = storyId,
                        onBackClick = { navController.popBackStack() },
                        onUrlClick = { url, title ->
                            val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                            val encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString())
                            navController.navigate("browser/$encodedUrl/$encodedTitle")
                        },
                        onAuthorClick = { username ->
                            navController.navigate("user/$username")
                        }
                    )
                }
            }
            
            composable("user/{username}") { backStackEntry ->
                val username = backStackEntry.arguments?.getString("username")
                if (username != null) {
                    UserProfileScreen(
                        username = username,
                        onBackClick = { navController.popBackStack() },
                        onStoryClick = { story ->
                            navController.navigate("story/${story.id}")
                        }
                    )
                }
            }
            
            composable("browser/{url}/{title}") { backStackEntry ->
                val encodedUrl = backStackEntry.arguments?.getString("url")
                val encodedTitle = backStackEntry.arguments?.getString("title")
                
                if (encodedUrl != null && encodedTitle != null) {
                    val url = java.net.URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
                    val title = java.net.URLDecoder.decode(encodedTitle, StandardCharsets.UTF_8.toString())
                    
                    RetroWebViewScreen(
                        url = url,
                        title = title,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)