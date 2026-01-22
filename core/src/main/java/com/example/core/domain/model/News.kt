package com.example.newsapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val publishedDate: String,
    val urlToImage: String?,
    val url: String,
    val description: String?,
    val isFavorite: Boolean = false
) : Parcelable