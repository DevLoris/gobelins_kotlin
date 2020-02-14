package dev.pinna.news.datasource.dao

import androidx.room.*
import dev.pinna.news.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: Article)

    @Update
    fun update(user: Article)
}