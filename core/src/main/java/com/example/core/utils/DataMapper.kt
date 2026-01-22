package com.example.newsapp.core.utils

import com.example.newsapp.core.data.source.local.entity.NewsEntity
import com.example.newsapp.core.data.source.remote.response.NewsItemResponse
import com.example.newsapp.core.domain.model.News

object DataMapper {

    // Network -> Domain
    fun mapResponseToDomain(input: List<NewsItemResponse>): List<News> {
        return input.map {
            News(
                title = it.title ?: "No Title",
                description = it.abstract ?: "No Description",
                url = it.url ?: "",
                publishedDate = it.publishedDate ?: "",
                urlToImage = it.multimedia?.firstOrNull()?.url,
                isFavorite = false
            )
        }
    }

    // Entity -> Domain
    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> {
        return input.map {
            News(
                title = it.title,
                description = it.abstract,
                url = it.url,
                publishedDate = it.publishedDate,
                urlToImage = it.imageUrl,
                isFavorite = true
            )
        }
    }

    // Domain -> Entity
    fun mapDomainToEntity(input: News): NewsEntity {
        return NewsEntity(
            title = input.title,
            abstract = input.description ?: "No Description",
            url = input.url,
            publishedDate = input.publishedDate,
            imageUrl = input.urlToImage,
            section = "home"
        )
    }
}