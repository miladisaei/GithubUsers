package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailsUseCase
@Inject
constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(username: String): Resource<User> {
        return userRepository.getUserDetails(username)
    }
}