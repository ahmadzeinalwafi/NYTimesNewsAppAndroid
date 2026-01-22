package com.example.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.model.Section
import com.example.newsapp.core.domain.repository.INewsRepository
import com.example.newsapp.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: INewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<News>>>(Resource.Loading())
    val newsState: StateFlow<Resource<List<News>>> = _newsState

    init {
        // Load Home section by default
        fetchNews(Section.HOME)
    }

    fun fetchNews(section: Section) {
        viewModelScope.launch {
            newsRepository.getTopStories(section).collect { resource ->
                _newsState.value = resource
            }
        }
    }

    fun setFavorite(news: News, newState: Boolean) {
        viewModelScope.launch {
            newsRepository.setFavoriteNews(news, newState)
        }
    }
}