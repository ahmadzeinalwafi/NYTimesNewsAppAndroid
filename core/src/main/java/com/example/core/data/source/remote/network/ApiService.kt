package com.example.newsapp.core.data.source.remote.network

import com.example.newsapp.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{section}.json")
    suspend fun getTopStories(
        @Path("section") section: String,
        @Query("api-key") apiKey: String
    ): NewsResponse
}