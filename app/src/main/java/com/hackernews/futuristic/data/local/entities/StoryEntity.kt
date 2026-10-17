package com.hackernews.futuristic.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hackernews.futuristic.data.model.Story

@Entity(tableName = "stories")
@TypeConverters(Converters::class)
data class StoryEntity(
    @PrimaryKey
    val id: Long,
    val title: String?,
    val url: String?,
    val text: String?,
    val by: String?,
    val time: Long,
    val score: Int,
    val descendants: Int?,
    val kids: List<Long>?,
    val type: String?,
    val storyType: String,
    val fetchedAt: Long = System.currentTimeMillis()
)

class Converters {
    @TypeConverter
    fun fromLongList(value: List<Long>?): String? {
        return if (value == null) null else Gson().toJson(value)
    }

    @TypeConverter
    fun toLongList(value: String?): List<Long>? {
        return if (value == null) null else {
            val listType = object : TypeToken<List<Long>>() {}.type
            Gson().fromJson(value, listType)
        }
    }
}

// Extension functions to convert between entities and domain models
fun StoryEntity.toDomainModel(): Story = Story(
    id = id,
    title = title,
    url = url,
    text = text,
    by = by,
    time = time,
    score = score,
    descendants = descendants,
    kids = kids,
    type = type
)

fun Story.toEntity(storyType: String): StoryEntity = StoryEntity(
    id = id,
    title = title,
    url = url,
    text = text,
    by = by,
    time = time,
    score = score,
    descendants = descendants,
    kids = kids,
    type = type,
    storyType = storyType
)
