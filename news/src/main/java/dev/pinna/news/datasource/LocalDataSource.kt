package dev.pinna.news.datasource

import dev.pinna.news.datasource.database.Database
import dev.pinna.news.models.Article

class LocalDataSource {
    fun getLocalArticles() : List<Article> {
        return Database.get().articleDao().getAll()
    }

    fun save(articles : List<Article>)  {
        Database.get().articleDao().insertAll(*articles.toTypedArray())
    }

    fun update(article : Article)  {
        Database.get().articleDao().update(article)
    }
}