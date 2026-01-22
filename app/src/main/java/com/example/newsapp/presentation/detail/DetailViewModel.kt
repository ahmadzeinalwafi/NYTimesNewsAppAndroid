package com.example.newsapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.repository.INewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: INewsRepository
) : ViewModel() {

    fun setFavoriteNews(news: News, newStatus: Boolean) {
        viewModelScope.launch {
            newsRepository.setFavoriteNews(news, newStatus)
        }
    }
}