package com.hackernews.futuristic.utils

import android.text.Html
import android.text.Spanned

object TextUtils {
    
    /**
     * Cleans HTML tags and entities from text content
     */
    fun cleanHtmlText(text: String?): String {
        if (text.isNullOrBlank()) return ""
        
        // Use Android's Html.fromHtml to convert HTML to plain text
        val spanned: Spanned = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(text)
        }
        
        return spanned.toString().trim()
    }
    
    /**
     * Cleans and formats comment text specifically
     */
    fun cleanCommentText(text: String?): String {
        if (text.isNullOrBlank()) return ""
        
        // First clean HTML
        val cleaned = cleanHtmlText(text)
        
        // Replace common HTML entities that might not be caught
        return cleaned
            .replace("&gt;", ">")
            .replace("&lt;", "<")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&#x27;", "'")
            .replace("&#x2F;", "/")
            .replace("&nbsp;", " ")
            .trim()
    }
    
    /**
     * Truncates text to a specified length with ellipsis
     */
    fun truncateText(text: String, maxLength: Int): String {
        return if (text.length <= maxLength) {
            text
        } else {
            text.take(maxLength - 3) + "..."
        }
    }
}