package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowingUseCase
@Inject
constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(username: String): Flow<Resource<UserListResponse>> {
        return userRepository.getFollowingUser(username)
    }
}