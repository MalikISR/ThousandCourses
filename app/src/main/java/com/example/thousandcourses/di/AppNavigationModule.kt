package com.example.thousandcourses.di

import com.example.feature_login.navigation.LoginNavigator
import com.example.thousandcourses.navigation.LoginNavigatorImpl
import org.koin.dsl.module

val appNavigationModule = module {
    single<LoginNavigator> { LoginNavigatorImpl() }
}
