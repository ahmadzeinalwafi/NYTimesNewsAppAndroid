package com.example.newsapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_favorites")
    fun getFavoriteNews(): Flow<List<NewsEntity>>

    @Query("SELECT COUNT(*) FROM news_favorites WHERE url = :url")
    suspend fun isNewsFavorite(url: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(news: NewsEntity): Long

    @Delete
    suspend fun deleteFavorite(news: NewsEntity): Int
}