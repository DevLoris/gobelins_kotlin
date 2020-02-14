package dev.pinna.news.repositories

import dev.pinna.news.datasource.LocalDataSource
import dev.pinna.news.datasource.RemoteDataSource
import dev.pinna.news.models.Article

class ArticleRepository {
    private val online = RemoteDataSource()
    private val offline = LocalDataSource()
    private lateinit var articles : List<Article>
    fun getArticles(): List<Article> {
        if (!::articles.isInitialized) {
            articles =  ( offline.getLocalArticles() + online.getRemoteArticles()).distinctBy { it.title }
            offline.save(articles)
        }
        return articles
    }

    fun getArticleFromTitle(title:String) : Article? {
        return getArticles().find { it.title == title }
    }

    fun getFavoritesArticles() : List<Article> {
        return getArticles().filter { it.favorite }
    }

    fun updateArticle(article:Article) {
        offline.update(article)
    }
}