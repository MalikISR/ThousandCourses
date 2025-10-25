package com.example.domain.usecase

import com.example.core.model.Course
import com.example.domain.repository.FavoritesRepository

class GetFavoritesUseCase(
    private val repo: FavoritesRepository
) {
    suspend operator fun invoke(): List<Course> = repo.getFavorites()
}
