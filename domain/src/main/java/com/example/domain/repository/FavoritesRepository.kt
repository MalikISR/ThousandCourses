package com.example.domain.repository

import com.example.core.model.Course

interface FavoritesRepository {
    suspend fun addFavorite(course: Course)
    suspend fun removeFavorite(course: Course)
    suspend fun getFavorites(): List<Course>
}
