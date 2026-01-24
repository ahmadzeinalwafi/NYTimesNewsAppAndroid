package com.example.newsapp.core.domain.usecase

import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.domain.repository.INewsRepository
import com.example.newsapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository): NewsUseCase {

    override fun getTopStories(section: Section): Flow<Resource<List<News>>> {
        return newsRepository.getTopStories(section)
    }

    override suspend fun setFavoriteNews(news: News, state: Boolean) {
        newsRepository.setFavoriteNews(news, state)
    }
}