package com.dicoding.asclepius.view

import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.remote.repository.NewsRepository
import com.dicoding.asclepius.remote.response.NewsResponse

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    suspend fun getCancerNews(): NewsResponse? {
        return newsRepository.getCancerNews()
    }
}
