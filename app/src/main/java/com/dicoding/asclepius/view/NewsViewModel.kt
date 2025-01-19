package com.dicoding.asclepius.view

import com.dicoding.asclepius.remote.repository.NewsRepository
import com.dicoding.asclepius.remote.response.NewsResponse

class NewsViewModel(private val newsRepository: NewsRepository) {

    suspend fun getCancerNews(): NewsResponse? {
        return newsRepository.getCancerNews()
    }
}