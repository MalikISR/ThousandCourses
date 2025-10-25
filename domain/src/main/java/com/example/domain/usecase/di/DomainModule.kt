package com.example.domain.usecase.di

import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.GetFavoritesUseCase
import com.example.domain.usecase.GetUserEmailUseCase
import com.example.domain.usecase.IsUserLoggedInUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.LogoutUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCoursesUseCase(get()) }
    single { GetFavoritesUseCase(get()) }
    single { ToggleFavoriteUseCase(get()) }
    single { LoginUseCase(get()) }
    single { GetUserEmailUseCase(get()) }
    single { IsUserLoggedInUseCase(get()) }
    single { LogoutUseCase(get()) }
}

