package com.miladisaei.githubusers.domain.repository

import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getSearchedUsers(
        searchQuery: String,
        page: Int,
        count: Int
    ): Flow<Resource<SearchResponse>>
    suspend fun getUserDetails(username: String): Flow<Resource<User>>
    suspend fun getFollowersUser(username: String): Flow<Resource<UserListResponse>>
    suspend fun getFollowingUser(username: String): Flow<Resource<UserListResponse>>
    suspend fun addFavoriteUser(user: User)
    suspend fun deleteFavoriteUser(user: User)
    fun getFavoriteUsers(): Flow<List<User>>

}