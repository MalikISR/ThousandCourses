package com.example.feature_main.di

import com.example.feature_main.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}
