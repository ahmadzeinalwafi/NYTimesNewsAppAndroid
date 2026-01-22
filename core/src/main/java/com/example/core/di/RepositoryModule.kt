package com.example.newsapp.core.di

import com.example.newsapp.core.data.repository.NewsRepository
import com.example.newsapp.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(newsRepository: NewsRepository): INewsRepository
}