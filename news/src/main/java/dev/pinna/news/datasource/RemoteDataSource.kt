package dev.pinna.news.datasource

import android.util.Log
import dev.pinna.news.datasource.services.ArticleService
import dev.pinna.news.models.ApiResult
import dev.pinna.news.models.Article
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    val API:String = "c8685321c98543e990d3bf0ea287842e";

    private val service: ArticleService

    init {
        val retrofit = Retrofit.Builder().apply {
            //Ajouter un converter pour JSON
            //Ici on utilise gson
            addConverterFactory(GsonConverterFactory.create())
            client(OkHttpClient())
//Ajouter l'url de base du web service
            baseUrl("https://newsapi.org/v2/")
        }.build()
//Créer une instance du service
        service = retrofit.create(ArticleService::class.java)
    }

    fun getRemoteArticles(): List<Article> {
        val result = service.getArticles(API).execute()
        return if(result.isSuccessful) {
            val body = result.body()
            if (body is ApiResult) {
                return body.articles
            }
            return emptyList()
        }else {
            emptyList()
        }
    }
}