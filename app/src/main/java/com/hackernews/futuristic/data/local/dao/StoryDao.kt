package com.hackernews.futuristic.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackernews.futuristic.data.local.entities.StoryEntity

@Dao
interface StoryDao {

    @Query("SELECT * FROM stories WHERE id IN (:ids) AND fetchedAt >= :freshSince")
    suspend fun getFreshStoriesByIds(ids: List<Long>, freshSince: Long): List<StoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoryEntity>)

    @Query("DELETE FROM stories WHERE fetchedAt < :cutoffTime")
    suspend fun deleteStoriesOlderThan(cutoffTime: Long)
}
