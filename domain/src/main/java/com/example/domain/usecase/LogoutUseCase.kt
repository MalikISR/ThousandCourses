package com.example.domain.usecase

import com.example.domain.user.UserRepository

class LogoutUseCase(private val repo: UserRepository) {
    operator fun invoke() = repo.logout()
}
