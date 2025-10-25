package com.example.domain.usecase

import com.example.core.model.Course
import com.example.domain.repository.FavoritesRepository

class ToggleFavoriteUseCase(
    private val repo: FavoritesRepository
) {
    suspend fun add(course: Course) = repo.addFavorite(course)
    suspend fun remove(course: Course) = repo.removeFavorite(course)
}
