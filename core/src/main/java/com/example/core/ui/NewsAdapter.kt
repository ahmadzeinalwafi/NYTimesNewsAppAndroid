package com.example.newsapp.core.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.databinding.ItemNewsBinding

class NewsAdapter(private val onItemClick: (News) -> Unit) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(DiffCallback) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.tvTitle.text = news.title
            binding.tvDate.text = news.publishedDate

            // Load image using Coil
            binding.ivNews.load(news.urlToImage) {
                crossfade(true)
            }

            binding.root.setOnClickListener { onItemClick(news) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    }
}