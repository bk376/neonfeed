package com.hackernews.futuristic.ui.search

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.repository.SearchSort
import com.hackernews.futuristic.data.repository.SearchType
import com.hackernews.futuristic.ui.components.CenteredLoading
import com.hackernews.futuristic.ui.components.CenteredMessage
import com.hackernews.futuristic.ui.components.CommentHitCard
import com.hackernews.futuristic.ui.components.LoadMoreEffect
import com.hackernews.futuristic.ui.components.StoryCard
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont

@Composable
fun SearchScreen(
    onStoryClick: (Long) -> Unit,
    onUrlClick: (String, String) -> Unit,
    onAuthorClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val filters by viewModel.filters.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()

    LoadMoreEffect(listState, onLoadMore = viewModel::loadMore)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.surface)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 4.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = viewModel::onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search all of Hacker News", fontFamily = MonospaceFont) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = viewModel::clearQuery) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.accent,
                    unfocusedBorderColor = AppColors.border,
                    focusedTextColor = AppColors.textPrimary,
                    unfocusedTextColor = AppColors.textPrimary,
                    cursorColor = AppColors.accent,
                    focusedLeadingIconColor = AppColors.accent,
                    unfocusedLeadingIconColor = AppColors.textSecondary,
                    focusedTrailingIconColor = AppColors.textSecondary,
                    unfocusedTrailingIconColor = AppColors.textSecondary,
                    focusedPlaceholderColor = AppColors.textDim,
                    unfocusedPlaceholderColor = AppColors.textDim
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchChip("Stories", filters.type == SearchType.STORIES) { viewModel.setType(SearchType.STORIES) }
                SearchChip("Comments", filters.type == SearchType.COMMENTS) { viewModel.setType(SearchType.COMMENTS) }
                SearchChip(filters.sort.displayName, selected = filters.sort == SearchSort.NEWEST) {
                    viewModel.setSort(if (filters.sort == SearchSort.RELEVANCE) SearchSort.NEWEST else SearchSort.RELEVANCE)
                }
                TimeRangeChip(filters.timeRange, viewModel::setTimeRange)
            }
        }

        when {
            query.isBlank() -> CenteredMessage(
                title = "Search Hacker News",
                message = "Stories and comments from all time, ranked by relevance and popularity."
            )
            uiState.isSearching && !uiState.hasSearched -> CenteredLoading()
            uiState.error != null -> CenteredMessage(
                title = "SEARCH FAILED",
                message = uiState.error,
                onRetry = viewModel::retry
            )
            uiState.hasSearched && uiState.totalHits == 0 -> CenteredMessage(
                title = "No results",
                message = "Try other words or a wider time range."
            )
            else -> LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "%,d results".format(uiState.totalHits),
                            color = AppColors.textSecondary,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont
                        )
                        if (uiState.isSearching) {
                            CircularProgressIndicator(
                                color = AppColors.accent,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }

                if (filters.type == SearchType.STORIES) {
                    items(uiState.stories, key = { it.id }) { story ->
                        StoryCard(
                            story = story,
                            onStoryClick = { onStoryClick(story.id) },
                            onUrlClick = onUrlClick,
                            onAuthorClick = onAuthorClick,
                            isBookmarked = story.id in bookmarkedIds,
                            onBookmarkClick = { viewModel.toggleBookmark(story) }
                        )
                    }
                } else {
                    items(uiState.comments, key = { it.id }) { comment ->
                        CommentHitCard(
                            comment = comment,
                            onClick = { comment.storyId?.let(onStoryClick) },
                            onAuthorClick = onAuthorClick
                        )
                    }
                }

                if (uiState.isLoadingMore) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = AppColors.accent, modifier = Modifier.size(28.dp))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchChip(label: String, selected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label, fontFamily = MonospaceFont, fontSize = 12.sp) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = AppColors.surface,
            labelColor = AppColors.textSecondary,
            selectedContainerColor = AppColors.accent.copy(alpha = 0.18f),
            selectedLabelColor = AppColors.accent
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = AppColors.border,
            selectedBorderColor = AppColors.accent,
            enabled = true,
            selected = selected
        )
    )
}

@Composable
private fun TimeRangeChip(selected: RelativeTimeRange, onSelect: (RelativeTimeRange) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        SearchChip(
            label = if (selected == RelativeTimeRange.ALL_TIME) "Any time" else selected.displayName,
            selected = selected != RelativeTimeRange.ALL_TIME,
            onClick = { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(AppColors.surface)
        ) {
            RelativeTimeRange.entries.forEach { range ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = if (range == RelativeTimeRange.ALL_TIME) "Any time" else range.displayName,
                            color = if (range == selected) AppColors.accent else AppColors.textPrimary,
                            fontFamily = MonospaceFont,
                            fontSize = 13.sp
                        )
                    },
                    onClick = {
                        onSelect(range)
                        expanded = false
                    }
                )
            }
        }
    }
}
