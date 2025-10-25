package com.example.data.repository

import com.example.core.model.Course
import com.example.data.db.FavoriteEntity
import com.example.data.db.FavoritesDao
import com.example.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val dao: FavoritesDao
) : FavoritesRepository {

    override suspend fun addFavorite(course: Course) {
        val entity = FavoriteEntity(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            publishDate = course.publishDate
        )
        dao.addFavorite(entity)
    }

    override suspend fun removeFavorite(course: Course) {
        val entity = FavoriteEntity(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            publishDate = course.publishDate
        )
        dao.removeFavorite(entity)
    }

    override suspend fun getFavorites(): List<Course> =
        dao.getFavorites().map {
            Course(
                it.id,
                it.title,
                it.text,
                it.price,
                it.rate,
                it.startDate,
                true,
                it.publishDate
            )
        }
}
