package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository

class GetUserDetailsUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(id: Int): Resource<User> {
        return userRepository.getUserDetails(id)
    }
}