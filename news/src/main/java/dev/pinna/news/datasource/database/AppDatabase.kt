package dev.pinna.news.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.pinna.news.datasource.dao.ArticleDao
import dev.pinna.news.models.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}

object Database {
    lateinit var database : AppDatabase
    fun init(context:Context) {
        if(!::database.isInitialized) {
            database = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
        }
    }
    fun get() : AppDatabase {
        return database
    }
}