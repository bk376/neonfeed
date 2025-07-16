package com.hackernews.futuristic.data.local.dao

import androidx.room.*
import com.hackernews.futuristic.data.local.entities.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    
    @Query("SELECT * FROM comments WHERE id IN (:ids)")
    suspend fun getCommentsByIds(ids: List<Long>): List<CommentEntity>
    
    @Query("SELECT * FROM comments WHERE id = :id")
    suspend fun getCommentById(id: Long): CommentEntity?
    
    @Query("SELECT * FROM comments WHERE parent = :parentId")
    suspend fun getCommentsByParent(parentId: Long): List<CommentEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)
    
    @Query("DELETE FROM comments WHERE fetchedAt < :olderThan")
    suspend fun deleteOldComments(olderThan: Long)
    
    @Query("DELETE FROM comments WHERE id IN (:ids)")
    suspend fun deleteComments(ids: List<Long>)
    
    // Get comments for a specific story
    @Query("""
        WITH RECURSIVE comment_tree AS (
            SELECT * FROM comments WHERE parent IS NULL AND id IN (:rootCommentIds)
            UNION ALL
            SELECT c.* FROM comments c
            INNER JOIN comment_tree ct ON c.parent = ct.id
        )
        SELECT * FROM comment_tree ORDER BY time ASC
    """)
    suspend fun getCommentTree(rootCommentIds: List<Long>): List<CommentEntity>
    
    @Query("SELECT COUNT(*) FROM comments")
    suspend fun getCommentCount(): Int
}