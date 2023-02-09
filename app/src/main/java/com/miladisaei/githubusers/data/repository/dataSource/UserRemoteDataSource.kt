package com.miladisaei.githubusers.data.repository.dataSource

import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.model.UserListResponse
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun getSearchedUser(
        searchQuery: String,
        page: Int,
        count: Int
    ): Response<SearchResponse>
    suspend fun getUserDetails(username: String): Response<User>
    suspend fun getFollowersUser(username: String): Response<UserListResponse>
    suspend fun getFollowingUser(username: String): Response<UserListResponse>
}