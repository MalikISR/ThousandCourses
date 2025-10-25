package com.example.feature_login.navigation

import android.content.Context

interface LoginNavigator {
    fun onLoginSuccess(context: Context)
    fun goToLogin(context: Context)
}