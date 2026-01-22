package com.example.newsapp.core.data.repository

import com.example.newsapp.core.data.source.local.room.NewsDao
import com.example.newsapp.core.data.source.remote.network.ApiService
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.domain.repository.INewsRepository
import com.example.newsapp.core.utils.DataMapper
import com.example.newsapp.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) : INewsRepository {

    private val apiKey = com.example.newsapp.core.BuildConfig.API_KEY

    override fun getTopStories(section: Section): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getTopStories(section.apiValue, apiKey)
            val newsList = DataMapper.mapResponseToDomain(response.results)

            val updatedList = newsList.map { news ->
                val count = newsDao.isNewsFavorite(news.url)
                news.copy(isFavorite = count > 0)
            }

            emit(Resource.Success(updatedList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Network Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteNews(): Flow<List<News>> {
        return newsDao.getFavoriteNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteNews(news: News, state: Boolean) {
        val entity = DataMapper.mapDomainToEntity(news)
        if (state) {
            newsDao.insertFavorite(entity)
        } else {
            newsDao.deleteFavorite(entity)
        }
    }
}