package com.example.data.repository

import com.example.core.model.Course
import com.example.data.api.CoursesApi

/**
 * Репозиторий для работы с курсами.
 *
 * Сейчас используется как заготовка для будущей интеграции с сетью.
 * В текущей версии проекта данные загружаются из локального JSON,
 * поэтому этот класс пока не применяется.
 *
 * В дальнейшем может быть расширен для обработки состояний через Resource<T>,
 * а также для кэширования данных в локальной БД.
 */
class CoursesRepositoryImpl(
    private val api: CoursesApi
) {
    suspend fun getCourses(): List<Course> {
        return try {
            api.getCourses().courses
        } catch (e: Exception) {
            emptyList()
        }
    }
}
