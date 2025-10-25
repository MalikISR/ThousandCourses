package com.example.data.userprefs

import com.example.domain.user.UserRepository

class UserRepositoryImpl(private val prefs: UserPrefs) : UserRepository {

    override fun saveEmail(email: String) {
        prefs.email = email
        prefs.isLoggedIn = true
    }

    override fun isLoggedIn(): Boolean = prefs.isLoggedIn

    override fun getEmail(): String? = prefs.email

    override fun logout() {
        prefs.isLoggedIn = false
        prefs.email = null
    }
}

