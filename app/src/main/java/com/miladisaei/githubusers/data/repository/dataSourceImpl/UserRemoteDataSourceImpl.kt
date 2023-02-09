package com.miladisaei.githubusers.data.repository.dataSourceImpl

import com.miladisaei.githubusers.data.api.UserAPIService
import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.repository.dataSource.UserRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl
@Inject
constructor(
    private val userAPIService: UserAPIService
) : UserRemoteDataSource {

    override suspend fun getSearchedUser(
        searchQuery: String,
        page: Int,
        count: Int
    ): Response<SearchResponse> {
        return userAPIService.getSearchedUser(
            searchQuery = searchQuery,
            page = page,
            count = count
        )
    }

    override suspend fun getUserDetails(username: String): Response<User> {
        return userAPIService.getUserDetails(username)
    }

    override suspend fun getFollowersUser(username: String): Response<UserListResponse> {
        return userAPIService.getFollowersUser(username)
    }

    override suspend fun getFollowingUser(username: String): Response<UserListResponse> {
        return userAPIService.getFollowingUser(username)
    }
}