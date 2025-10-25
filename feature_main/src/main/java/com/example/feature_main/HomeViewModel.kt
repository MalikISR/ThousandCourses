package com.example.feature_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Course
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.GetFavoritesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val allCourses = getCoursesUseCase()
            val favCourses = getFavoritesUseCase()

            val favIds = favCourses.map { it.id }.toSet()

            val merged = allCourses.map { course ->
                if (course.id in favIds) course.copy(hasLike = true)
                else course.copy(hasLike = course.hasLike)
            }

            _courses.value = merged.sortedByDescending { it.publishDate }
        }
    }

    fun toggleLike(course: Course) {
        viewModelScope.launch {
            if (course.hasLike) {
                toggleFavoriteUseCase.remove(course)
            } else {
                toggleFavoriteUseCase.add(course)
            }

            _courses.value = _courses.value.map {
                if (it.id == course.id) it.copy(hasLike = !it.hasLike) else it
            }
        }
    }

    fun filterByDate() {
        viewModelScope.launch {
            val sorted = _courses.value.sortedByDescending { it.publishDate }
            _courses.value = sorted
        }
    }
}
