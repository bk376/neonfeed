package com.hackernews.futuristic.data.local.dao

import androidx.room.*
import com.hackernews.futuristic.data.local.entities.StoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    
    @Query("SELECT * FROM stories WHERE storyType = :type ORDER BY time DESC")
    fun getStoriesByType(type: String): Flow<List<StoryEntity>>
    
    @Query("SELECT * FROM stories WHERE storyType = :type ORDER BY time DESC LIMIT :limit")
    suspend fun getStoriesByTypeSync(type: String, limit: Int): List<StoryEntity>
    
    @Query("SELECT * FROM stories WHERE id = :id")
    suspend fun getStoryById(id: Long): StoryEntity?
    
    @Query("SELECT * FROM stories WHERE id IN (:ids)")
    suspend fun getStoriesByIds(ids: List<Long>): List<StoryEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoryEntity>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: StoryEntity)
    
    @Query("DELETE FROM stories WHERE storyType = :type AND fetchedAt < :olderThan")
    suspend fun deleteOldStories(type: String, olderThan: Long)
    
    @Query("DELETE FROM stories WHERE storyType = :type")
    suspend fun deleteStoriesByType(type: String)
    
    @Query("SELECT COUNT(*) FROM stories WHERE storyType = :type")
    suspend fun getStoryCount(type: String): Int
    
    // Cache management
    @Query("SELECT fetchedAt FROM stories WHERE storyType = :type ORDER BY fetchedAt DESC LIMIT 1")
    suspend fun getLastFetchTime(type: String): Long?
    
    @Query("DELETE FROM stories WHERE fetchedAt < :cutoffTime")
    suspend fun cleanupOldStories(cutoffTime: Long)
}