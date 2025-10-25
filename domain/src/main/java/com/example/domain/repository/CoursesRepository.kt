package com.example.domain.repository

import com.example.core.model.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}