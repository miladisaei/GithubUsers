package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository

class GetFollowersUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(username: String): Resource<UserListResponse> {
        return userRepository.getFollowersUser(username)
    }
}