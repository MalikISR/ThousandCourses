package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.AppDatabase
import com.example.data.repository.CoursesRepositoryMock
import com.example.data.repository.FavoritesRepositoryImpl
import com.example.data.userprefs.UserPrefs
import com.example.data.userprefs.UserRepositoryImpl
import com.example.domain.repository.CoursesRepository
import com.example.domain.repository.FavoritesRepository
import com.example.domain.user.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "thousand_courses_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().favoritesDao() }

    single { UserPrefs(get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<CoursesRepository> { CoursesRepositoryMock(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
}
