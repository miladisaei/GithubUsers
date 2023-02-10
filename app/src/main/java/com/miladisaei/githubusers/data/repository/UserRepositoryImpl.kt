package com.miladisaei.githubusers.data.repository

import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.repository.dataSource.UserRemoteDataSource
import com.miladisaei.githubusers.data.util.DataConverter
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UserRepositoryImpl
@Inject
constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getSearchedUsers(
        searchQuery: String,
        page: Int,
        count: Int
    ): Flow<Resource<SearchResponse>> {
        return flowOf(
            DataConverter.responseToResource(
                userRemoteDataSource.getSearchedUser(
                    searchQuery = searchQuery,
                    page = page,
                    count = count
                )
            )
        )
    }

    override suspend fun getUserDetails(username: String): Flow<Resource<User>> {
        return flowOf(
            DataConverter.responseToResource(
                userRemoteDataSource.getUserDetails(
                    username
                )
            )
        )
    }

    override suspend fun getFollowersUser(username: String): Flow<Resource<UserListResponse>> {
        return flowOf(
            DataConverter.responseToResource(
                userRemoteDataSource.getFollowersUser(
                    username
                )
            )
        )
    }

    override suspend fun getFollowingUser(username: String): Flow<Resource<UserListResponse>> {
        return flowOf(
            DataConverter.responseToResource(
                userRemoteDataSource.getFollowingUser(
                    username
                )
            )
        )
    }

    override suspend fun addFavoriteUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}