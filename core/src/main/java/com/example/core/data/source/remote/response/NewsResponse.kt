package com.example.newsapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class NewsResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("results")
    val results: List<NewsItemResponse>
)

@Keep
data class NewsItemResponse(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("abstract")
    val abstract: String? = null,

    @SerializedName("url")
    val url: String? = null, // We will use this as the Unique ID

    @SerializedName("published_date")
    val publishedDate: String? = null,

    @SerializedName("section")
    val section: String? = null,

    @SerializedName("multimedia")
    val multimedia: List<MultimediaResponse>? = null
)

@Keep
data class MultimediaResponse(
    @SerializedName("url")
    val url: String? = null
)