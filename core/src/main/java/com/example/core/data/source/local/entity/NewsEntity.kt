package com.example.newsapp.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.Keep

@Keep
@Entity(tableName = "news_favorites")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false) // URL is unique, so no auto-generate
    val url: String,
    val title: String,
    val abstract: String,
    val publishedDate: String,
    val imageUrl: String?,
    val section: String
)