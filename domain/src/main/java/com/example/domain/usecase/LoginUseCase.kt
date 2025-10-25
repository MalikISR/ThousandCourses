package com.example.domain.usecase

import com.example.domain.user.UserRepository

class LoginUseCase(private val repo: UserRepository) {

    fun login(email: String) {
        repo.saveEmail(email)
    }

    fun isLoggedIn(): Boolean = repo.isLoggedIn()
}