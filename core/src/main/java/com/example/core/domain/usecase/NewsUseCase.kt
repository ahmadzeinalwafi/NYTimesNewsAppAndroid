package com.example.newsapp.core.domain.usecase

import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getTopStories(section: Section): Flow<Resource<List<News>>>
    suspend fun setFavoriteNews(news: News, state: Boolean)
}