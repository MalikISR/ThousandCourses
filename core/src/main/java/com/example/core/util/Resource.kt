package com.example.core.util

/**
 * Заготовка для универсальной обертки над состояниями данных (успех, загрузка, ошибка).
 * Пока не используется, так как проект работает с локальными JSON и БД.
 *
 * В дальнейшем можно использовать для унификации ответов при работе с сетью.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
