package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.domain.repository.UserRepository
import javax.inject.Inject

class AddFavoriteUserUseCase
@Inject
constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(user: User) {
        userRepository.addFavoriteUser(user)
    }
}