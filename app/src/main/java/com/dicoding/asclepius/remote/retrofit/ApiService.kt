package com.dicoding.asclepius.remote.retrofit

import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("top-headlines")
    suspend fun getCancerNews(
        @Query("q") query: String = "cancer",
        @Query("category") category: String = "health",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_KEY
    ): Response<NewsResponse>
}
