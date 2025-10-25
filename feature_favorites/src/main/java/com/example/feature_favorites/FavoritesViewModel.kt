package com.example.feature_favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Course
import com.example.domain.usecase.GetFavoritesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Course>>(emptyList())
    val favorites: StateFlow<List<Course>> = _favorites

    init {
        refreshFavorites()
    }

    fun refreshFavorites() {
        viewModelScope.launch {
            _favorites.value = getFavoritesUseCase()
        }
    }

    fun removeFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase.remove(course)
            _favorites.value = getFavoritesUseCase()
        }
    }
}
