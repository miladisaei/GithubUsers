package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsExistUserInFavoritesUseCase
@Inject
constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(username: String): Flow<Boolean> {
        return userRepository.isExistInFavorites(username)
    }
}