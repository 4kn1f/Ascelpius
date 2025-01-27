package com.dicoding.asclepius.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.ViewModelFactory
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.dicoding.asclepius.remote.repository.NewsRepository
import com.dicoding.asclepius.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel: NewsViewModel by viewModels {
        ViewModelFactory(NewsRepository(ApiConfig.getApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getCancerNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = newsAdapter
    }

    private fun getCancerNews() {
        lifecycleScope.launch {
            try {
                val result = newsViewModel.getCancerNews()
                if (result != null && result.articles != null) {
                    newsAdapter.submitNews(result.articles)
                } else {
                    Toast.makeText(this@NewsActivity, "Error: Not found", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@NewsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
