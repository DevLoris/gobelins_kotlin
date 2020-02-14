package dev.pinna.news.models

data class ApiResult(val status:String, val totalResults:Int, val articles:List<Article>)