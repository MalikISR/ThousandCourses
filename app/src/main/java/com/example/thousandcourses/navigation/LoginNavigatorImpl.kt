package com.example.thousandcourses.navigation


import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.feature_login.LoginActivity
import com.example.feature_login.navigation.LoginNavigator
import com.example.thousandcourses.MainActivity

class LoginNavigatorImpl : LoginNavigator {
    override fun onLoginSuccess(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        if (context is Activity) context.finish()
    }

    override fun goToLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        if (context is Activity) context.finish()
    }
}
