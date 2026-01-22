package com.example.newsapp.core.domain.repository

import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    // Fetch data from API, save to DB, and emit source of truth from DB
    fun getTopStories(section: Section): Flow<Resource<List<News>>>

    // Get only the favorite items (from local DB)
    fun getFavoriteNews(): Flow<List<News>>

    // Add or Remove a favorite
    suspend fun setFavoriteNews(news: News, state: Boolean)
}