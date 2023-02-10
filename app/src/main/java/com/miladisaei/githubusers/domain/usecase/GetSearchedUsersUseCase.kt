package com.miladisaei.githubusers.domain.usecase

import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchedUsersUseCase
@Inject
constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(
        searchQuery: String,
        page: Int,
        count: Int
    ): Flow<Resource<SearchResponse>> {
        return userRepository.getSearchedUsers(
            searchQuery = searchQuery,
            page = page,
            count = count
        )
    }
}