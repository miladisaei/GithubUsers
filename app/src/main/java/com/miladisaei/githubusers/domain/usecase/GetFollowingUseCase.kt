package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.domain.repository.UserRepository

class GetFollowingUseCase(
    private val userRepository: UserRepository
) {
}