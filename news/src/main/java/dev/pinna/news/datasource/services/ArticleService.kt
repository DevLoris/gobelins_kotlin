package dev.pinna.news.datasource.services

import dev.pinna.news.models.ApiResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("top-headlines")
    fun getArticles(@Query("apiKey") apiKey:String, @Query("country") country:String = "fr"): Call<ApiResult>
}