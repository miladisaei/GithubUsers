package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.domain.repository.UserRepository

class GetFavoriteUsersUseCase(
    private val userRepository: UserRepository
) {
}