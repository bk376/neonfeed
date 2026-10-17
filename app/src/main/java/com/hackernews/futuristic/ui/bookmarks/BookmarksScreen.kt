package com.hackernews.futuristic.ui.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.ui.components.CenteredLoading
import com.hackernews.futuristic.ui.components.CenteredMessage
import com.hackernews.futuristic.ui.components.StoryCard
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont

@Composable
fun BookmarksScreen(
    onStoryClick: (Long) -> Unit,
    onUrlClick: (String, String) -> Unit,
    onAuthorClick: (String) -> Unit,
    viewModel: BookmarksViewModel = hiltViewModel()
) {
    val stories by viewModel.bookmarkedStories.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
    ) {
        Text(
            text = "BOOKMARKS",
            color = AppColors.accent,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MonospaceFont,
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.surface)
                .padding(16.dp)
        )

        val list = stories
        when {
            list == null -> CenteredLoading()
            list.isEmpty() -> CenteredMessage(
                title = "No bookmarks yet",
                message = "Tap the bookmark icon on a story to save it here."
            )
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(list, key = { it.id }) { story ->
                    StoryCard(
                        story = story,
                        onStoryClick = { onStoryClick(story.id) },
                        onUrlClick = onUrlClick,
                        onAuthorClick = onAuthorClick,
                        isBookmarked = true,
                        onBookmarkClick = { viewModel.removeBookmark(story) }
                    )
                }
            }
        }
    }
}
