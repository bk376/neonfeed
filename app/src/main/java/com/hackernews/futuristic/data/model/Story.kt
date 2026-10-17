package com.hackernews.futuristic.data.model

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("by")
    val by: String?,
    @SerializedName("time")
    val time: Long,
    @SerializedName("score")
    val score: Int,
    @SerializedName("descendants")
    val descendants: Int?,
    @SerializedName("kids")
    val kids: List<Long>?,
    @SerializedName("type")
    val type: String?
)

/** A comment with its full reply tree, in HN display order. */
data class CommentNode(
    val id: Long,
    val by: String?,
    val time: Long,
    /** Raw HN HTML. */
    val text: String?,
    val children: List<CommentNode>
) {
    val isDeleted: Boolean get() = by == null && text.isNullOrBlank()

    fun descendantCount(): Int = children.sumOf { 1 + it.descendantCount() }
}

/** A comment found via search or on a user's page, with the story it belongs to. */
data class CommentHit(
    val id: Long,
    val by: String?,
    val time: Long,
    val text: String?,
    val storyId: Long?,
    val storyTitle: String?,
    val points: Int?
)

enum class RelativeTimeRange(val displayName: String, val hours: Long) {
    TODAY("Today", 24),
    LAST_WEEK("Last Week", 168),
    LAST_MONTH("Last Month", 720),
    LAST_YEAR("Last Year", 8760),
    ALL_TIME("All Time", -1)
}
