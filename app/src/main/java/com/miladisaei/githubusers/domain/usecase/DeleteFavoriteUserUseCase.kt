package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.domain.repository.UserRepository

class DeleteFavoriteUserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(user: User) {
        userRepository.deleteFavoriteUser(user)
    }
}