package com.example.newsapp.core.di

import com.example.newsapp.core.domain.repository.INewsRepository
import com.example.newsapp.core.domain.usecase.NewsInteractor
import com.example.newsapp.core.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: INewsRepository): NewsUseCase {
        return NewsInteractor(newsRepository)
    }
}