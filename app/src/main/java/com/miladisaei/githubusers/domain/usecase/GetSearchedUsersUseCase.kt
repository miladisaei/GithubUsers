package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.domain.repository.UserRepository

class GetSearchedUsersUseCase(
    private val userRepository: UserRepository
) {

}