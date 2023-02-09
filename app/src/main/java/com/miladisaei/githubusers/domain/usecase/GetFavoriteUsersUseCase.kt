package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteUsersUseCase(
    private val userRepository: UserRepository
) {

    fun execute(): Flow<List<User>> {
        return userRepository.getFavoriteUsers()
    }
}