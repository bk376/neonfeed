package com.hackernews.futuristic.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class DailySummaryWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: HackerNewsRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            // Fetch top stories for daily summary
            val topStoriesResult = repository.getTopStories(50)
            
            if (topStoriesResult.isSuccess) {
                val stories = topStoriesResult.getOrNull() ?: emptyList()
                
                // Pre-cache the top stories for faster access
                stories.forEach { story ->
                    // Cache any comments for popular stories (high score)
                    if (story.score > 100 && !story.kids.isNullOrEmpty()) {
                        repository.getTopLevelComments(story.kids, 20)
                    }
                }
                
                // Clean up old cached data (older than 24 hours)
                val cutoffTime = System.currentTimeMillis() - (24 * 60 * 60 * 1000L)
                // Note: We could add cleanup methods to the DAOs if needed
                
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
    
    companion object {
        const val WORK_NAME = "daily_summary_work"
    }
}