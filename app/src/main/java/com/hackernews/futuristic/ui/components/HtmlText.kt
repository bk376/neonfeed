package com.hackernews.futuristic.ui.components

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.text.style.URLSpan
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.text.HtmlCompat
import com.hackernews.futuristic.ui.theme.AppColors

private const val URL_TAG = "url"

/** Renders HN's comment/story HTML: paragraphs, italics, code and clickable links. */
@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = AppColors.textPrimary,
    maxLines: Int = Int.MAX_VALUE,
    /** Taps that don't land on a link; ClickableText would otherwise swallow them. */
    onClick: (() -> Unit)? = null,
    onLinkClick: (String) -> Unit
) {
    val linkColor = AppColors.link
    val text = remember(html, linkColor) { htmlToAnnotatedString(html, linkColor) }
    ClickableText(
        text = text,
        modifier = modifier,
        style = style.copy(color = color),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onClick = { offset ->
            val link = text.getStringAnnotations(URL_TAG, offset, offset).firstOrNull()
            if (link != null) onLinkClick(link.item) else onClick?.invoke()
        }
    )
}

/** Plain-text version of HN HTML, for previews and search. */
fun htmlToPlainText(html: String?): String =
    if (html.isNullOrBlank()) "" else HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim()

private fun htmlToAnnotatedString(html: String, linkColor: Color): AnnotatedString {
    val spanned: Spanned = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    val raw = spanned.toString()
    val end = raw.trimEnd().length
    return buildAnnotatedString {
        append(raw.substring(0, end))
        spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
            val start = spanned.getSpanStart(span).coerceAtMost(end)
            val spanEnd = spanned.getSpanEnd(span).coerceAtMost(end)
            if (start >= spanEnd) return@forEach
            when (span) {
                is URLSpan -> {
                    addStyle(SpanStyle(color = linkColor, textDecoration = TextDecoration.Underline), start, spanEnd)
                    addStringAnnotation(URL_TAG, span.url, start, spanEnd)
                }
                is StyleSpan -> when (span.style) {
                    Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, spanEnd)
                    Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, spanEnd)
                    Typeface.BOLD_ITALIC -> addStyle(
                        SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic), start, spanEnd
                    )
                }
                is TypefaceSpan -> if (span.family == "monospace") {
                    addStyle(SpanStyle(fontFamily = FontFamily.Monospace), start, spanEnd)
                }
            }
        }
    }
}
