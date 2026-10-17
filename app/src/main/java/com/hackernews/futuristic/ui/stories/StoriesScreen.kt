package com.hackernews.futuristic.ui.stories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.components.CenteredLoading
import com.hackernews.futuristic.ui.components.CenteredMessage
import com.hackernews.futuristic.ui.components.LoadMoreEffect
import com.hackernews.futuristic.ui.components.StoryCard
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoriesScreen(
    onStoryClick: (Story) -> Unit,
    onUrlClick: (String, String) -> Unit,
    onAuthorClick: (String) -> Unit,
    onThemeClick: () -> Unit,
    onScrollToTopCallback: ((suspend () -> Unit) -> Unit),
    viewModel: StoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedTimeRange by viewModel.selectedTimeRange.collectAsStateWithLifecycle()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val showScrollToTop by remember { derivedStateOf { listState.firstVisibleItemIndex > 2 } }

    LaunchedEffect(Unit) {
        onScrollToTopCallback { listState.animateScrollToItem(0) }
    }

    LoadMoreEffect(listState, onLoadMore = viewModel::loadMoreStories)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.surface)
                .padding(start = 16.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "NEONFEED",
                color = AppColors.accent,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MonospaceFont,
                modifier = Modifier.weight(1f)
            )
            TimeRangeDropdown(
                selectedTimeRange = selectedTimeRange,
                onTimeRangeChange = viewModel::setTimeRange
            )
            IconButton(onClick = onThemeClick) {
                Icon(Icons.Default.Palette, contentDescription = "Theme", tint = AppColors.textSecondary)
            }
        }

        when {
            uiState.isLoading && uiState.stories.isEmpty() -> CenteredLoading()

            uiState.error != null && uiState.stories.isEmpty() -> CenteredMessage(
                title = "CONNECTION ERROR",
                message = uiState.error,
                onRetry = viewModel::refreshStories
            )

            !uiState.isLoading && uiState.stories.isEmpty() -> CenteredMessage(
                title = "Nothing here",
                message = "No front-page stories from ${selectedTimeRange.displayName.lowercase()}."
            )

            else -> {
                val pullToRefreshState = rememberPullToRefreshState()
                LaunchedEffect(pullToRefreshState.isRefreshing) {
                    if (pullToRefreshState.isRefreshing) {
                        viewModel.refreshStories()
                        pullToRefreshState.endRefresh()
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(pullToRefreshState.nestedScrollConnection)
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(uiState.stories, key = { it.id }) { story ->
                            StoryCard(
                                story = story,
                                onStoryClick = { onStoryClick(story) },
                                onUrlClick = onUrlClick,
                                onAuthorClick = onAuthorClick,
                                isBookmarked = story.id in bookmarkedIds,
                                onBookmarkClick = { viewModel.toggleBookmark(story) }
                            )
                        }

                        if (uiState.isLoading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(color = AppColors.accent, modifier = Modifier.size(32.dp))
                                }
                            }
                        }
                    }

                    // Fully qualified: inside Column, the ColumnScope overload would otherwise win.
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showScrollToTop,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        FloatingActionButton(
                            onClick = { scope.launch { listState.animateScrollToItem(0) } },
                            containerColor = AppColors.accent,
                            contentColor = AppColors.onAccent
                        ) {
                            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Scroll to top")
                        }
                    }

                    PullToRefreshContainer(
                        state = pullToRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter),
                        containerColor = AppColors.surface,
                        contentColor = AppColors.accent
                    )
                }
            }
        }
    }
}

@Composable
private fun TimeRangeDropdown(
    selectedTimeRange: RelativeTimeRange,
    onTimeRangeChange: (RelativeTimeRange) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .border(1.dp, AppColors.link.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                Icons.Default.Schedule,
                contentDescription = null,
                tint = AppColors.link,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = selectedTimeRange.displayName,
                color = AppColors.link,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(AppColors.surface)
        ) {
            RelativeTimeRange.entries.forEach { timeRange ->
                val selected = timeRange == selectedTimeRange
                DropdownMenuItem(
                    text = {
                        Text(
                            text = timeRange.displayName,
                            color = if (selected) AppColors.link else AppColors.textPrimary,
                            fontSize = 13.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    onClick = {
                        onTimeRangeChange(timeRange)
                        expanded = false
                    }
                )
            }
        }
    }
}
