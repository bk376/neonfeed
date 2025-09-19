package com.hackernews.futuristic.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val storyId: Long,
    val title: String,
    val url: String?,
    val by: String,
    val time: Long,
    val score: Int,
    val descendants: Int,
    val bookmarkedAt: Long = System.currentTimeMillis()
)