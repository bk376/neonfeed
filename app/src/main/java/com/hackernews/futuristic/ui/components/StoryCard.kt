package com.hackernews.futuristic.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont

@Composable
fun StoryCard(
    story: Story,
    onStoryClick: () -> Unit,
    modifier: Modifier = Modifier,
    onUrlClick: ((String, String) -> Unit)? = null,
    onAuthorClick: ((String) -> Unit)? = null,
    isBookmarked: Boolean = false,
    onBookmarkClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onStoryClick),
        colors = CardDefaults.cardColors(containerColor = AppColors.surface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = story.title ?: "Untitled",
                color = AppColors.accent,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MonospaceFont,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            story.url?.let { url ->
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .clickable(enabled = onUrlClick != null) {
                            onUrlClick?.invoke(url, story.title ?: "Untitled")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                        contentDescription = "Open link",
                        tint = AppColors.link.copy(alpha = 0.7f),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = domainOf(url),
                        color = AppColors.link.copy(alpha = 0.8f),
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                MetaText("▲ ${story.score}", AppColors.positive)
                MetaText("💬 ${story.descendants ?: 0}", AppColors.secondaryAccent)
                MetaText(formatTimeAgo(story.time), AppColors.highlight)

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    story.by?.let { author ->
                        Text(
                            text = author,
                            color = if (onAuthorClick != null) AppColors.link else AppColors.textSecondary,
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.clickable(enabled = onAuthorClick != null) {
                                onAuthorClick?.invoke(author)
                            }
                        )
                    }
                }

                onBookmarkClick?.let { onClick ->
                    IconButton(onClick = onClick, modifier = Modifier.size(24.dp)) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                            tint = if (isBookmarked) AppColors.bookmark else AppColors.textSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetaText(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = 12.sp,
        fontFamily = MonospaceFont,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}

@Composable
fun CenteredLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = AppColors.accent)
    }
}

@Composable
fun CenteredMessage(
    title: String,
    message: String? = null,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null
) {
    Box(modifier = modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                color = if (onRetry != null) AppColors.error else AppColors.textPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MonospaceFont,
                textAlign = TextAlign.Center
            )
            message?.let {
                Text(
                    text = it,
                    color = AppColors.textSecondary,
                    fontSize = 13.sp,
                    fontFamily = MonospaceFont,
                    textAlign = TextAlign.Center
                )
            }
            onRetry?.let {
                Button(
                    onClick = it,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.accent,
                        contentColor = AppColors.onAccent
                    )
                ) {
                    Text("RETRY", fontFamily = MonospaceFont)
                }
            }
        }
    }
}

fun domainOf(url: String): String =
    runCatching { java.net.URI(url).host?.removePrefix("www.") }.getOrNull() ?: "link"

/** Compact age for an epoch-seconds timestamp: "5m", "3h", "2d", "4w", "6mo", "1y". */
fun formatTimeAgo(epochSeconds: Long): String {
    val diff = System.currentTimeMillis() / 1000 - epochSeconds
    return when {
        diff < 60 -> "now"
        diff < 3600 -> "${diff / 60}m"
        diff < 86400 -> "${diff / 3600}h"
        diff < 604800 -> "${diff / 86400}d"
        diff < 2592000 -> "${diff / 604800}w"
        diff < 31536000 -> "${diff / 2592000}mo"
        else -> "${diff / 31536000}y"
    }
}
