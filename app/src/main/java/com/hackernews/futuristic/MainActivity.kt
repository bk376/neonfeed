package com.hackernews.futuristic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackernews.futuristic.data.repository.SettingsRepository
import com.hackernews.futuristic.ui.bookmarks.BookmarksScreen
import com.hackernews.futuristic.ui.browser.BrowserScreen
import com.hackernews.futuristic.ui.detail.StoryDetailScreen
import com.hackernews.futuristic.ui.search.SearchScreen
import com.hackernews.futuristic.ui.settings.ThemePickerDialog
import com.hackernews.futuristic.ui.stories.StoriesScreen
import com.hackernews.futuristic.ui.theme.AppTheme
import com.hackernews.futuristic.ui.theme.NeonFeedTheme
import com.hackernews.futuristic.ui.user.UserProfileScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme by settingsRepository.theme.collectAsStateWithLifecycle()
            NeonFeedTheme(theme = theme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NeonFeedApp(
                        theme = theme,
                        onThemeChange = settingsRepository::setTheme
                    )
                }
            }
        }
    }
}

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem("stories", "Home", Icons.Default.Home),
    BottomNavItem("search", "Search", Icons.Default.Search),
    BottomNavItem("bookmarks", "Bookmarks", Icons.Default.Bookmark)
)

@Composable
private fun NeonFeedApp(
    theme: AppTheme,
    onThemeChange: (AppTheme) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scope = rememberCoroutineScope()

    var scrollStoriesToTop: (suspend () -> Unit)? by remember { mutableStateOf(null) }
    var showThemePicker by remember { mutableStateOf(false) }

    val showBottomNav = currentDestination?.route in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            selected = selected,
                            onClick = {
                                if (selected) {
                                    if (item.route == "stories") {
                                        scope.launch { scrollStoriesToTop?.invoke() }
                                    }
                                } else {
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
                StoriesScreen(
                    onStoryClick = { story -> navController.navigate("story/${story.id}") },
                    onUrlClick = navController::openBrowser,
                    onAuthorClick = { username -> navController.navigate("user/$username") },
                    onThemeClick = { showThemePicker = true },
                    onScrollToTopCallback = { callback -> scrollStoriesToTop = callback }
                )
            }

            composable("search") {
                SearchScreen(
                    onStoryClick = { id -> navController.navigate("story/$id") },
                    onUrlClick = navController::openBrowser,
                    onAuthorClick = { username -> navController.navigate("user/$username") }
                )
            }

            composable("bookmarks") {
                BookmarksScreen(
                    onStoryClick = { id -> navController.navigate("story/$id") },
                    onUrlClick = navController::openBrowser,
                    onAuthorClick = { username -> navController.navigate("user/$username") }
                )
            }

            composable("story/{storyId}") { backStackEntry ->
                val storyId = backStackEntry.arguments?.getString("storyId")?.toLongOrNull()
                if (storyId != null) {
                    StoryDetailScreen(
                        storyId = storyId,
                        onBackClick = { navController.popBackStack() },
                        onUrlClick = navController::openBrowser,
                        onAuthorClick = { username -> navController.navigate("user/$username") }
                    )
                }
            }

            composable("user/{username}") { backStackEntry ->
                val username = backStackEntry.arguments?.getString("username")
                if (username != null) {
                    UserProfileScreen(
                        username = username,
                        onBackClick = { navController.popBackStack() },
                        onStoryClick = { id -> navController.navigate("story/$id") },
                        onUrlClick = navController::openBrowser
                    )
                }
            }

            composable("browser/{url}/{title}") { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url")
                val title = backStackEntry.arguments?.getString("title")
                if (url != null && title != null) {
                    BrowserScreen(
                        url = URLDecoder.decode(url, StandardCharsets.UTF_8.name()),
                        title = URLDecoder.decode(title, StandardCharsets.UTF_8.name()),
                        onClose = { navController.popBackStack() }
                    )
                }
            }
        }
    }

    if (showThemePicker) {
        ThemePickerDialog(
            selected = theme,
            onSelect = onThemeChange,
            onDismiss = { showThemePicker = false }
        )
    }
}

private fun NavController.openBrowser(url: String, title: String) {
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.name())
    val encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.name())
    navigate("browser/$encodedUrl/$encodedTitle")
}
