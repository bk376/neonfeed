package com.hackernews.futuristic.data.repository

import com.hackernews.futuristic.data.api.HackerNewsApi
import com.hackernews.futuristic.data.local.dao.CommentDao
import com.hackernews.futuristic.data.local.dao.StoryDao
import com.hackernews.futuristic.data.local.entities.toDomainModel
import com.hackernews.futuristic.data.local.entities.toEntity
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.Story
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HackerNewsRepository @Inject constructor(
    private val api: HackerNewsApi,
    private val storyDao: StoryDao,
    private val commentDao: CommentDao
) {
    
    companion object {
        private const val CACHE_EXPIRY_TIME = 5 * 60 * 1000L // 5 minutes
    }
    
    suspend fun getTopStories(limit: Int = 30): Result<List<Story>> {
        return getStoriesWithCache("TOP", limit) { api.getTopStories() }
    }
    
    suspend fun getNewStories(limit: Int = 30): Result<List<Story>> {
        return getStoriesWithCache("NEW", limit) { api.getNewStories() }
    }
    
    suspend fun getBestStories(limit: Int = 30): Result<List<Story>> {
        return getStoriesWithCache("BEST", limit) { api.getBestStories() }
    }
    
    // Unified caching method
    private suspend fun getStoriesWithCache(
        type: String,
        limit: Int,
        apiCall: suspend () -> retrofit2.Response<List<Long>>
    ): Result<List<Story>> {
        return try {
            // Check if we have fresh cached data
            val lastFetch = storyDao.getLastFetchTime(type)
            val currentTime = System.currentTimeMillis()
            
            if (lastFetch != null && (currentTime - lastFetch) < CACHE_EXPIRY_TIME) {
                // Return cached data
                val cachedStories = storyDao.getStoriesByTypeSync(type, limit)
                if (cachedStories.isNotEmpty()) {
                    return Result.success(cachedStories.map { it.toDomainModel() })
                }
            }
            
            // Fetch fresh data from API
            val response = apiCall()
            if (response.isSuccessful) {
                val storyIds = response.body()?.take(limit) ?: emptyList()
                val stories = fetchStoriesFromApi(storyIds)
                
                // Cache the stories
                val storyEntities = stories.map { it.toEntity(type) }
                storyDao.deleteStoriesByType(type) // Clear old cache
                storyDao.insertStories(storyEntities)
                
                Result.success(stories)
            } else {
                // Try to return stale cache data if API fails
                val cachedStories = storyDao.getStoriesByTypeSync(type, limit)
                if (cachedStories.isNotEmpty()) {
                    Result.success(cachedStories.map { it.toDomainModel() })
                } else {
                    Result.failure(Exception("Failed to fetch $type stories and no cache available"))
                }
            }
        } catch (e: Exception) {
            // Return cached data on network error
            try {
                val cachedStories = storyDao.getStoriesByTypeSync(type, limit)
                if (cachedStories.isNotEmpty()) {
                    Result.success(cachedStories.map { it.toDomainModel() })
                } else {
                    Result.failure(e)
                }
            } catch (dbError: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun getStory(id: Long): Result<Story> {
        return try {
            val response = api.getItem(id)
            if (response.isSuccessful) {
                response.body()?.let { story ->
                    Result.success(story)
                } ?: Result.failure(Exception("Story not found"))
            } else {
                Result.failure(Exception("Failed to fetch story"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getComments(commentIds: List<Long>): Result<List<Comment>> {
        return try {
            val comments = fetchCommentsWithCache(commentIds)
            Result.success(comments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun fetchCommentsWithCache(commentIds: List<Long>): List<Comment> {
        // Check cache first
        val cachedComments = commentDao.getCommentsByIds(commentIds)
        val cachedIds = cachedComments.map { it.id }.toSet()
        val missingIds = commentIds.filterNot { it in cachedIds }
        
        // Fetch missing comments from API
        val newComments = if (missingIds.isNotEmpty()) {
            fetchCommentsFromApi(missingIds)
        } else {
            emptyList()
        }
        
        // Cache new comments
        if (newComments.isNotEmpty()) {
            val commentEntities = newComments.map { it.toEntity() }
            commentDao.insertComments(commentEntities)
        }
        
        // Return all comments in original order
        val allCommentsMap = (cachedComments.map { it.toDomainModel() } + newComments).associateBy { it.id }
        return commentIds.mapNotNull { allCommentsMap[it] }
    }
    
    // Fast loading - only top-level comments initially
    suspend fun getTopLevelComments(commentIds: List<Long>, limit: Int = 50): Result<List<Comment>> {
        return try {
            val limitedIds = commentIds.take(limit)
            val comments = fetchCommentsWithCache(limitedIds)
            Result.success(comments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Load children for specific comment
    suspend fun getChildComments(parentComment: Comment): Result<List<Comment>> {
        return try {
            val childIds = parentComment.kids ?: emptyList()
            val children = fetchCommentsWithCache(childIds.take(20)) // Limit children too
            Result.success(children)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // New methods for infinite scroll
    suspend fun getTopStoryIds(): Result<List<Long>> {
        return try {
            val response = api.getTopStories()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch top story IDs"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getNewStoryIds(): Result<List<Long>> {
        return try {
            val response = api.getNewStories()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch new story IDs"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getBestStoryIds(): Result<List<Long>> {
        return try {
            val response = api.getBestStories()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch best story IDs"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getStoriesByIds(storyIds: List<Long>): Result<List<Story>> {
        return try {
            // First check cache for existing stories
            val cachedStories = storyDao.getStoriesByIds(storyIds)
            val cachedIds = cachedStories.map { it.id }.toSet()
            val missingIds = storyIds.filterNot { it in cachedIds }
            
            // Fetch missing stories from API
            val newStories = if (missingIds.isNotEmpty()) {
                fetchStoriesFromApi(missingIds)
            } else {
                emptyList()
            }
            
            // Cache new stories
            if (newStories.isNotEmpty()) {
                val newEntities = newStories.map { it.toEntity("FETCH") }
                storyDao.insertStories(newEntities)
            }
            
            // Combine cached and new stories in original order
            val allStoriesMap = (cachedStories.map { it.toDomainModel() } + newStories).associateBy { it.id }
            val orderedStories = storyIds.mapNotNull { allStoriesMap[it] }
            
            Result.success(orderedStories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun fetchStoriesFromApi(storyIds: List<Long>): List<Story> = coroutineScope {
        storyIds.map { id ->
            async {
                try {
                    val response = api.getItem(id)
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }.awaitAll().filterNotNull()
    }
    
    private suspend fun fetchCommentsRecursively(commentIds: List<Long>): List<Comment> = coroutineScope {
        val allComments = mutableListOf<Comment>()
        val visitedIds = mutableSetOf<Long>()
        
        suspend fun fetchCommentsDeep(ids: List<Long>) {
            val comments = fetchCommentsWithCache(ids.filter { it !in visitedIds })
            allComments.addAll(comments)
            visitedIds.addAll(ids)
            
            // Recursively fetch children
            val childIds = comments.flatMap { it.kids ?: emptyList() }
            if (childIds.isNotEmpty()) {
                fetchCommentsDeep(childIds)
            }
        }
        
        fetchCommentsDeep(commentIds)
        allComments.toList()
    }
    
    private suspend fun fetchCommentsFromApi(commentIds: List<Long>): List<Comment> = coroutineScope {
        commentIds.map { id ->
            async {
                try {
                    val response = api.getComment(id)
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }.awaitAll().filterNotNull()
    }
}