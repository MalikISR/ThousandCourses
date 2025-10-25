package com.example.domain.usecase

import com.example.core.model.Course
import com.example.domain.repository.CoursesRepository

class GetCoursesUseCase(private val repo: CoursesRepository) {
    suspend operator fun invoke(): List<Course> = repo.getCourses()
}
