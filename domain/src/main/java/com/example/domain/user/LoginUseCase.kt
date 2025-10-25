package com.example.domain.user

class LoginUseCase(private val repo: UserRepository) {

    fun login(email: String) {
        repo.saveEmail(email)
    }

    fun isLoggedIn(): Boolean = repo.isLoggedIn()
}