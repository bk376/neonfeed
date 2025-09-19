package com.hackernews.futuristic.data.local.dao

import androidx.room.*
import com.hackernews.futuristic.data.local.entities.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    
    @Query("SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>
    
    @Query("SELECT * FROM bookmarks WHERE storyId = :storyId")
    suspend fun getBookmark(storyId: Long): BookmarkEntity?
    
    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE storyId = :storyId)")
    suspend fun isBookmarked(storyId: Long): Boolean
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)
    
    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)
    
    @Query("DELETE FROM bookmarks WHERE storyId = :storyId")
    suspend fun deleteBookmarkById(storyId: Long)
    
    @Query("SELECT COUNT(*) FROM bookmarks")
    suspend fun getBookmarkCount(): Int
    
    @Query("DELETE FROM bookmarks")
    suspend fun deleteAllBookmarks()
}