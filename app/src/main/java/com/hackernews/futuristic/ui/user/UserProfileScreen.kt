package com.hackernews.futuristic.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.api.User
import com.hackernews.futuristic.ui.components.CenteredLoading
import com.hackernews.futuristic.ui.components.CenteredMessage
import com.hackernews.futuristic.ui.components.CommentHitCard
import com.hackernews.futuristic.ui.components.HtmlText
import com.hackernews.futuristic.ui.components.LoadMoreEffect
import com.hackernews.futuristic.ui.components.StoryCard
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    username: String,
    onBackClick: () -> Unit,
    onStoryClick: (Long) -> Unit,
    onUrlClick: (String, String) -> Unit,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(username) { viewModel.loadUserProfile(username) }
    LoadMoreEffect(listState, onLoadMore = viewModel::loadMore)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
    ) {
        TopAppBar(
            title = { Text(username, fontFamily = MonospaceFont, fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = AppColors.surface,
                titleContentColor = AppColors.textPrimary,
                navigationIconContentColor = AppColors.textPrimary
            )
        )

        val user = uiState.user
        when {
            uiState.isLoading -> CenteredLoading()
            uiState.error != null || user == null -> CenteredMessage(
                title = "COULDN'T LOAD USER",
                message = uiState.error,
                onRetry = viewModel::retry
            )
            else -> LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item { UserHeader(user, onLinkClick = { url -> onUrlClick(url, url) }) }

                item {
                    val tabs = UserTab.entries
                    TabRow(
                        selectedTabIndex = uiState.selectedTab.ordinal,
                        containerColor = AppColors.surface,
                        contentColor = AppColors.accent,
                        indicator = { positions ->
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(positions[uiState.selectedTab.ordinal]),
                                color = AppColors.accent
                            )
                        }
                    ) {
                        tabs.forEach { tab ->
                            val count = when (tab) {
                                UserTab.STORIES -> uiState.stories.totalHits
                                UserTab.COMMENTS -> uiState.comments.totalHits
                            }
                            Tab(
                                selected = tab == uiState.selectedTab,
                                onClick = { viewModel.selectTab(tab) },
                                selectedContentColor = AppColors.accent,
                                unselectedContentColor = AppColors.textSecondary,
                                text = {
                                    Text(
                                        text = if (count > 0) "${tab.label} (%,d)".format(count) else tab.label,
                                        fontFamily = MonospaceFont,
                                        fontSize = 13.sp
                                    )
                                }
                            )
                        }
                    }
                }

                val list = when (uiState.selectedTab) {
                    UserTab.STORIES -> uiState.stories
                    UserTab.COMMENTS -> uiState.comments
                }

                when (uiState.selectedTab) {
                    UserTab.STORIES -> items(uiState.stories.items, key = { "s${it.id}" }) { story ->
                        StoryCard(
                            story = story,
                            onStoryClick = { onStoryClick(story.id) },
                            onUrlClick = onUrlClick,
                            isBookmarked = story.id in bookmarkedIds,
                            onBookmarkClick = { viewModel.toggleBookmark(story) },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                        )
                    }
                    UserTab.COMMENTS -> items(uiState.comments.items, key = { "c${it.id}" }) { comment ->
                        CommentHitCard(
                            comment = comment,
                            onClick = { comment.storyId?.let(onStoryClick) },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            list.isLoading -> CircularProgressIndicator(
                                color = AppColors.accent,
                                modifier = Modifier.size(28.dp)
                            )
                            list.error != null -> TextButton(onClick = viewModel::loadMore) {
                                Text("Couldn't load more. RETRY", color = AppColors.error, fontFamily = MonospaceFont)
                            }
                            list.items.isEmpty() -> Text(
                                text = "Nothing yet",
                                color = AppColors.textDim,
                                fontFamily = MonospaceFont
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserHeader(user: User, onLinkClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            Stat(label = "KARMA", value = "%,d".format(user.karma), color = AppColors.positive)
            Stat(label = "JOINED", value = formatJoined(user.created), color = AppColors.highlight)
        }
        if (!user.about.isNullOrBlank()) {
            HtmlText(
                html = user.about,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.textSecondary,
                onLinkClick = onLinkClick
            )
        }
    }
}

@Composable
private fun Stat(label: String, value: String, color: Color) {
    Column {
        Text(value, color = color, fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = MonospaceFont)
        Text(label, color = AppColors.textDim, fontSize = 11.sp, fontFamily = MonospaceFont)
    }
}

/** "Mar 2014 · 10y" */
private fun formatJoined(epochSeconds: Long): String {
    val date = SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(Date(epochSeconds * 1000))
    val years = (System.currentTimeMillis() / 1000 - epochSeconds) / 31_536_000
    return if (years > 0) "$date · ${years}y" else date
}
