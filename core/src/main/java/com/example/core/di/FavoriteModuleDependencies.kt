package com.example.newsapp.core.di

import com.example.newsapp.core.domain.repository.INewsRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun repository(): INewsRepository
}