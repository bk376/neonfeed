package com.hackernews.futuristic.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.hackernews.futuristic.data.local.dao.CommentDao
import com.hackernews.futuristic.data.local.dao.StoryDao
import com.hackernews.futuristic.data.local.entities.CommentEntity
import com.hackernews.futuristic.data.local.entities.Converters
import com.hackernews.futuristic.data.local.entities.StoryEntity

@Database(
    entities = [StoryEntity::class, CommentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HackerNewsDatabase : RoomDatabase() {
    
    abstract fun storyDao(): StoryDao
    abstract fun commentDao(): CommentDao
    
    companion object {
        @Volatile
        private var INSTANCE: HackerNewsDatabase? = null
        
        fun getDatabase(context: Context): HackerNewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HackerNewsDatabase::class.java,
                    "hackernews_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}