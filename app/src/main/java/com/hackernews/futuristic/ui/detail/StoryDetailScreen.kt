package com.hackernews.futuristic.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.components.CenteredLoading
import com.hackernews.futuristic.ui.components.CenteredMessage
import com.hackernews.futuristic.ui.components.HtmlText
import com.hackernews.futuristic.ui.components.domainOf
import com.hackernews.futuristic.ui.components.formatTimeAgo
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont
import kotlinx.coroutines.launch

/** Items above the first comment: header, optional story text, comments heading. */
private const val HEADER_ITEMS = 3
private const val MAX_INDENT_DEPTH = 8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryDetailScreen(
    storyId: Long,
    onBackClick: () -> Unit,
    onUrlClick: (String, String) -> Unit,
    onAuthorClick: (String) -> Unit,
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(storyId) { viewModel.loadStory(storyId) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
    ) {
        TopAppBar(
            title = { Text("Discussion", fontFamily = MonospaceFont) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                uiState.story?.let { story ->
                    val isBookmarked = story.id in bookmarkedIds
                    IconButton(onClick = viewModel::toggleBookmark) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                            tint = if (isBookmarked) AppColors.bookmark else AppColors.textPrimary
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = AppColors.surface,
                titleContentColor = AppColors.textPrimary,
                navigationIconContentColor = AppColors.textPrimary
            )
        )

        val story = uiState.story
        when {
            uiState.isLoading -> CenteredLoading()
            uiState.error != null || story == null -> CenteredMessage(
                title = "COULDN'T LOAD STORY",
                message = uiState.error,
                onRetry = viewModel::retry
            )
            else -> Box(modifier = Modifier.fillMaxSize()) {
                val openLink = { url: String -> onUrlClick(url, url) }

                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    item { StoryHeader(story, onUrlClick, onAuthorClick) }

                    item {
                        if (!story.text.isNullOrBlank()) {
                            HtmlText(
                                html = story.text,
                                modifier = Modifier.padding(16.dp),
                                onLinkClick = openLink
                            )
                        }
                    }

                    item {
                        CommentsHeading(
                            count = story.descendants ?: 0,
                            isLoading = uiState.isLoadingComments,
                            error = uiState.commentsError,
                            onRetry = viewModel::retry
                        )
                    }

                    items(uiState.comments, key = { it.node.id }) { row ->
                        CommentRow(
                            row = row,
                            isOp = row.node.by != null && row.node.by == story.by,
                            onToggle = { viewModel.toggleCollapsed(row.node.id) },
                            onAuthorClick = onAuthorClick,
                            onLinkClick = openLink
                        )
                    }
                }

                if (uiState.comments.isNotEmpty()) {
                    // Jump to the next top-level thread, like HN readers' "next" button.
                    SmallFloatingActionButton(
                        onClick = {
                            val first = listState.firstVisibleItemIndex - HEADER_ITEMS
                            val next = uiState.comments.withIndex()
                                .firstOrNull { (index, row) -> index > first && row.depth == 0 }
                                ?.index
                            if (next != null) {
                                scope.launch { listState.animateScrollToItem(next + HEADER_ITEMS) }
                            }
                        },
                        containerColor = AppColors.accent,
                        contentColor = AppColors.onAccent,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Next thread")
                    }
                }
            }
        }
    }
}

@Composable
private fun StoryHeader(
    story: Story,
    onUrlClick: (String, String) -> Unit,
    onAuthorClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = story.title ?: "Untitled",
            color = AppColors.textPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 26.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("▲ ${story.score}", color = AppColors.positive, fontSize = 13.sp, fontFamily = MonospaceFont)
            story.by?.let { author ->
                Text(
                    text = author,
                    color = AppColors.link,
                    fontSize = 13.sp,
                    fontFamily = MonospaceFont,
                    modifier = Modifier.clickable { onAuthorClick(author) }
                )
            }
            Text(formatTimeAgo(story.time), color = AppColors.highlight, fontSize = 13.sp, fontFamily = MonospaceFont)
        }

        story.url?.let { url ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onUrlClick(url, story.title ?: url) },
                colors = CardDefaults.cardColors(containerColor = AppColors.accent.copy(alpha = 0.12f)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.OpenInNew,
                        contentDescription = null,
                        tint = AppColors.accent,
                        modifier = Modifier.size(18.dp)
                    )
                    Column {
                        Text(
                            text = "Read article",
                            color = AppColors.accent,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MonospaceFont
                        )
                        Text(
                            text = domainOf(url),
                            color = AppColors.textSecondary,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CommentsHeading(
    count: Int,
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "$count COMMENTS",
                color = AppColors.accent,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MonospaceFont,
                modifier = Modifier.weight(1f)
            )
            if (isLoading) {
                CircularProgressIndicator(
                    color = AppColors.accent,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(16.dp)
                )
            }
            if (error != null) {
                TextButton(onClick = onRetry) {
                    Text("RETRY", color = AppColors.error, fontFamily = MonospaceFont, fontSize = 12.sp)
                }
            }
        }
        HorizontalDivider(color = AppColors.border)
    }
}

@Composable
private fun CommentRow(
    row: VisibleComment,
    isOp: Boolean,
    onToggle: () -> Unit,
    onAuthorClick: (String) -> Unit,
    onLinkClick: (String) -> Unit
) {
    val comment = row.node
    val indentLevel = row.depth.coerceAtMost(MAX_INDENT_DEPTH)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(start = 8.dp + (indentLevel * 12).dp, end = 12.dp)
    ) {
        if (row.depth > 0) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
                    .background(depthColor(row.depth).copy(alpha = 0.6f))
            )
            Spacer(modifier = Modifier.width(10.dp))
        }

        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            // Header: tap anywhere on it to collapse/expand the thread.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggle),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val author = comment.by
                Text(
                    text = author ?: "[deleted]",
                    color = if (author != null) AppColors.link else AppColors.textDim,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont,
                    modifier = if (author != null) Modifier.clickable { onAuthorClick(author) } else Modifier
                )
                if (isOp) {
                    Text(
                        text = "OP",
                        color = AppColors.onAccent,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MonospaceFont,
                        modifier = Modifier
                            .background(AppColors.accent, RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 1.dp)
                    )
                }
                Text(
                    text = formatTimeAgo(comment.time),
                    color = AppColors.textDim,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    modifier = Modifier.weight(1f)
                )
                if (row.isCollapsed) {
                    Text(
                        text = "[+${row.hiddenReplies + 1}]",
                        color = AppColors.secondaryAccent,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont
                    )
                }
            }

            if (!row.isCollapsed && !comment.text.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                HtmlText(
                    html = comment.text,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
                    onLinkClick = onLinkClick
                )
            }
        }
    }
}

@Composable
private fun depthColor(depth: Int): Color = when (depth % 4) {
    1 -> AppColors.accent
    2 -> AppColors.secondaryAccent
    3 -> AppColors.positive
    else -> AppColors.highlight
}
