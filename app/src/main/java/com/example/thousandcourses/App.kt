package com.example.thousandcourses

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.usecase.di.domainModule
import com.example.feature_account.di.accountModule
import com.example.feature_favorites.di.favoritesModule
import com.example.feature_login.di.loginModule
import com.example.feature_main.di.mainModule
import com.example.thousandcourses.di.appModule
import com.example.thousandcourses.di.appNavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            printLogger()
            modules(
                appModule,
                appNavigationModule,
                dataModule,
                domainModule,
                loginModule,
                accountModule,
                mainModule,
                favoritesModule
            )
        }
    }
}