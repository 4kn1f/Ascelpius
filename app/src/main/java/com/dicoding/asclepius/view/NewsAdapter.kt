package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.databinding.ItemNewsBinding
import com.dicoding.asclepius.remote.response.ArticlesItem

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<ArticlesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = newsList.size

    fun submitNews(news: List<ArticlesItem?>?) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: ArticlesItem) {
            binding.tvTitle.text = news.title
            binding.tvDescription.text = news.description
            Glide.with(binding.ivNewsImage.context)
                .load(news.urlToImage)
                .into(binding.ivNewsImage)
            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                binding.root.context.startActivity(intent)
            }
        }
    }
}
