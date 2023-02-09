package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository

class GetFollowingUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(id: Int): Resource<UserListResponse> {
        return userRepository.getFollowingUser(id)
    }
}