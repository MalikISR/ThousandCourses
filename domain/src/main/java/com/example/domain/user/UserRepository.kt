package com.example.domain.user

interface UserRepository {
    fun isLoggedIn(): Boolean
    fun saveEmail(email: String)
    fun getEmail(): String?
    fun logout()
}