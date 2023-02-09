package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.domain.repository.UserRepository

class GetUserDetailsUseCase(
    private val userRepository: UserRepository
) {
}