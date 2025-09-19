package com.hackernews.futuristic.di

import android.content.Context
import androidx.room.Room
import com.hackernews.futuristic.data.local.HackerNewsDatabase
import com.hackernews.futuristic.data.local.dao.BookmarkDao
import com.hackernews.futuristic.data.local.dao.CommentDao
import com.hackernews.futuristic.data.local.dao.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideHackerNewsDatabase(@ApplicationContext context: Context): HackerNewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HackerNewsDatabase::class.java,
            "hackernews_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }
    
    @Provides
    fun provideStoryDao(database: HackerNewsDatabase): StoryDao {
        return database.storyDao()
    }
    
    @Provides
    fun provideCommentDao(database: HackerNewsDatabase): CommentDao {
        return database.commentDao()
    }
    
    @Provides
    fun provideBookmarkDao(database: HackerNewsDatabase): BookmarkDao {
        return database.bookmarkDao()
    }
}