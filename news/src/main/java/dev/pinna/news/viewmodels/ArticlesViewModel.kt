package dev.pinna.news.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinna.news.models.Article
import dev.pinna.news.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    private val repository: ArticleRepository = ArticleRepository()
    private val _listArticles = MutableLiveData<List<Article>>()
    val listArticles: LiveData<List<Article>>
        get() = _listArticles

    private val _actualArticle = MutableLiveData<Article>()
    val actualArticle: LiveData<Article>
        get() = _actualArticle

    private val _favoriteArticles = MutableLiveData<List<Article>>()
    val favoriteArticles: LiveData<List<Article>>
        get() = _favoriteArticles

    /**
     * Get all articles
     */
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getArticles()
            _listArticles.postValue(result)
        }
    }

    /**
     * Get an article from title
     */
    fun loadArticleFromTitle(title:String) {
        _actualArticle.value = repository.getArticleFromTitle(title)
    }

    /**
     * Load all favorites articles
     */
    fun loadFavorites() {
        _favoriteArticles.value = repository.getFavoritesArticles()
    }

    fun toggleFavoriteOnActual() {
        var actual = actualArticle.value!!
        actual.favorite = !actual.favorite


        viewModelScope.launch(Dispatchers.IO) {
            repository.updateArticle(actual)
        }

        _actualArticle.value = actual
    }
}