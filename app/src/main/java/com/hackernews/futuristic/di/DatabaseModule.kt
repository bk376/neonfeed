package com.hackernews.futuristic.di

import android.content.Context
import androidx.room.Room
import com.hackernews.futuristic.data.local.HackerNewsDatabase
import com.hackernews.futuristic.data.local.dao.BookmarkDao
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
    fun provideHackerNewsDatabase(@ApplicationContext context: Context): HackerNewsDatabase =
        Room.databaseBuilder(context, HackerNewsDatabase::class.java, "hackernews_database")
            .addMigrations(HackerNewsDatabase.MIGRATION_2_3)
            // Only v1 predates bookmarks; never wipe the database from a later version.
            .fallbackToDestructiveMigrationFrom(1)
            .build()

    @Provides
    fun provideStoryDao(database: HackerNewsDatabase): StoryDao = database.storyDao()

    @Provides
    fun provideBookmarkDao(database: HackerNewsDatabase): BookmarkDao = database.bookmarkDao()
}
