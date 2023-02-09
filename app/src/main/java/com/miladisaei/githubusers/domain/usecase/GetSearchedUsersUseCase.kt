package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository

class GetSearchedUsersUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(searchQuery: String, page: Int, count: Int): Resource<SearchResponse> {
        return userRepository.getSearchedUsers(
            searchQuery = searchQuery,
            page = page,
            count = count
        )
    }
}