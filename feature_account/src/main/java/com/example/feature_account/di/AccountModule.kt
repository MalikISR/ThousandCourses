package com.example.feature_account.di

import com.example.feature_account.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {
    viewModel { AccountViewModel(
        getUserEmailUseCase = get(),
        isUserLoggedInUseCase = get(),
        logoutUseCase = get()
    ) }
}