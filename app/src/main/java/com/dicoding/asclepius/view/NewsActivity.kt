package com.dicoding.asclepius.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.asclepius.ViewModelFactory
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.dicoding.asclepius.remote.repository.NewsRepository
import com.dicoding.asclepius.remote.retrofit.ApiService

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private val newsViewModel: NewsViewModel by viewModels {
        ViewModelFactory(NewsRepository(ApiService.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCancerNews()
    }

    private fun getCancerNews() {
        try {
            val result = newsViewModel.getCancerNews()
            if (result != null) {
            } else {
                Toast.makeText(this, "Error: Tidak ada data", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
