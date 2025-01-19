package com.dicoding.asclepius.remote.repository

import com.dicoding.asclepius.remote.retrofit.ApiService
import com.dicoding.asclepius.remote.response.NewsResponse
import retrofit2.Response

class NewsRepository(private val apiService: ApiService) {

    suspend fun getCancerNews(): NewsResponse? {
        return try {
            val response = apiService.getCancerNews()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}