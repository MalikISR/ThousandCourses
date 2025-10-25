package com.example.domain.usecase

import com.example.domain.user.UserRepository

class GetUserEmailUseCase(private val repo: UserRepository) {
    operator fun invoke(): String? = repo.getEmail()
}
