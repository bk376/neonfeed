package com.hackernews.futuristic.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.CommentHit
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont

/** A comment shown out of its thread (search results, user pages). Tapping opens its story. */
@Composable
fun CommentHitCard(
    comment: CommentHit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onAuthorClick: ((String) -> Unit)? = null
) {
    val text = remember(comment.text) { htmlToPlainText(comment.text) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = AppColors.surface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            comment.storyTitle?.let { title ->
                Text(
                    text = "on: $title",
                    color = AppColors.accent,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = text,
                color = AppColors.textPrimary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                comment.by?.let { author ->
                    Text(
                        text = author,
                        color = AppColors.link,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MonospaceFont,
                        modifier = Modifier.clickable(enabled = onAuthorClick != null) {
                            onAuthorClick?.invoke(author)
                        }
                    )
                }
                Text(
                    text = formatTimeAgo(comment.time),
                    color = AppColors.textDim,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont
                )
            }
        }
    }
}
