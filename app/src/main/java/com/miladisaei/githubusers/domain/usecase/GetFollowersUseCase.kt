package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.domain.repository.UserRepository

class GetFollowersUseCase(
    private val userRepository: UserRepository
) {
}