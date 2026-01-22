package com.example.newsapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newsapp.core.domain.repository.INewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    newsRepository: INewsRepository
) : ViewModel() {
    // Converts the Flow from repository into LiveData for the Fragment to observe
    val favoriteNews = newsRepository.getFavoriteNews().asLiveData()
}