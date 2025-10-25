package com.example.data.repository

import android.content.Context
import com.example.core.model.Course
import com.example.core.model.CourseResponse
import com.example.domain.repository.CoursesRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoursesRepositoryMock(
    private val context: Context
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> = withContext(Dispatchers.IO) {
        val json = context.assets.open("courses.json").bufferedReader().use { it.readText() }
        val response = Gson().fromJson(json, CourseResponse::class.java)
        response?.courses ?: emptyList()
    }
}