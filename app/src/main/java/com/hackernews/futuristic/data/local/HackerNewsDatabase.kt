package com.hackernews.futuristic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hackernews.futuristic.data.local.dao.BookmarkDao
import com.hackernews.futuristic.data.local.dao.StoryDao
import com.hackernews.futuristic.data.local.entities.BookmarkEntity
import com.hackernews.futuristic.data.local.entities.Converters
import com.hackernews.futuristic.data.local.entities.StoryEntity

@Database(
    entities = [StoryEntity::class, BookmarkEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HackerNewsDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        /** Comments now come from the HN Search API in one request, so their cache table goes. */
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DROP TABLE IF EXISTS comments")
            }
        }
    }
}
