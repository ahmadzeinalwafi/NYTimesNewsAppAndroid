package com.example.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.domain.usecase.NewsUseCase
import com.example.newsapp.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<News>>>(Resource.Loading())
    val newsState: StateFlow<Resource<List<News>>> = _newsState

    init {
        fetchNews(Section.HOME)
    }

    fun fetchNews(section: Section) {
        viewModelScope.launch {
            newsUseCase.getTopStories(section).collect { resource ->
                _newsState.value = resource
            }
        }
    }

    fun setFavorite(news: News, newState: Boolean) {
        viewModelScope.launch {
            newsUseCase.setFavoriteNews(news, newState)
        }
    }
}