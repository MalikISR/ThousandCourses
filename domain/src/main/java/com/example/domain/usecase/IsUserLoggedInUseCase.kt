package com.example.domain.usecase

import com.example.domain.user.UserRepository

class IsUserLoggedInUseCase(private val repo: UserRepository) {
    operator fun invoke(): Boolean = repo.isLoggedIn()
}
