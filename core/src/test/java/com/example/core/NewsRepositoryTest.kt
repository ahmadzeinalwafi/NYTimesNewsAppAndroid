package com.example.newsapp.core.data.repository

import com.example.newsapp.core.data.source.local.room.NewsDao
import com.example.newsapp.core.data.source.remote.network.ApiService
import com.example.newsapp.core.data.source.remote.response.MultimediaResponse
import com.example.newsapp.core.data.source.remote.response.NewsItemResponse
import com.example.newsapp.core.data.source.remote.response.NewsResponse
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.utils.Resource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
// Use this import for safer suspend function mocking
import org.mockito.kotlin.whenever

class NewsRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var newsDao: NewsDao

    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        newsRepository = NewsRepository(apiService, newsDao)
    }

    @Test
    fun `getTopStories should emit Loading then Success when API returns data`() = runTest {
        // GIVEN
        val dummySection = Section.HOME
        val dummyResponse = generateDummyNewsResponse()

        // FIX: Use 'whenever' (from mockito-kotlin) for suspend functions
        // Also use any() without 'Mockito.' prefix if possible, or keep explicit if imports conflict
        whenever(apiService.getTopStories(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(dummyResponse)

        whenever(newsDao.isNewsFavorite(Mockito.anyString()))
            .thenReturn(0)

        // WHEN
        val flow = newsRepository.getTopStories(dummySection)
        val resultList = flow.toList()

        // THEN
        assertEquals(2, resultList.size)
        assertTrue(resultList[0] is Resource.Loading)

        // DEBUGGING HELP:
        // If this fails, we want to know exactly what the error message is
        val actualState = resultList[1]
        assertTrue(
            "Expected Success but got Error: ${(actualState as? Resource.Error)?.message}",
            actualState is Resource.Success
        )

        val successState = actualState as Resource.Success
        assertEquals(1, successState.data?.size)
        assertEquals("Test Title", successState.data?.first()?.title)
    }

    private fun generateDummyNewsResponse(): NewsResponse {
        val newsList = ArrayList<NewsItemResponse>()
        newsList.add(
            NewsItemResponse(
                title = "Test Title",
                abstract = "Test Abstract",
                url = "https://test.com",
                publishedDate = "2024-01-01",
                section = "home",
                multimedia = listOf(MultimediaResponse(url = "http://image.url"))
            )
        )
        return NewsResponse(status = "OK", results = newsList)
    }
}