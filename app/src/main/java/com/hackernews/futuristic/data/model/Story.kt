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

data class Comment(
    @SerializedName("id")
    val id: Long,
    @SerializedName("by")
    val by: String?,
    @SerializedName("time")
    val time: Long,
    @SerializedName("text")
    val text: String?,
    @SerializedName("kids")
    val kids: List<Long>?,
    @SerializedName("parent")
    val parent: Long?,
    @SerializedName("type")
    val type: String?
)