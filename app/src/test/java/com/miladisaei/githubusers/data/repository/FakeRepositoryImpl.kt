package com.miladisaei.githubusers.data.repository

import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryImpl : UserRepository {

    private val userList = mutableListOf(
        User(
            id = 1,
            username = "user1",
            name = "name user",
            avatarUrl = "https://avatars.githubusercontent.com/u/101623633?v=4",
            bio = "userBio",
            company = "userCompany",
            location = "userLocation",
            followersCount = 4,
            followingCount = 6,
            repositoryCount = 10,
            htmlUrl = "https://github.com/miladisaei"
        )
    )

    private val searchResponse = SearchResponse(
        users = userList,
        totalCount = 1
    )

    private val emptySearchResponse = SearchResponse(
        users = emptyList(),
        totalCount = 0
    )

    private val userListResponse: UserListResponse = UserListResponse()

    override suspend fun getSearchedUsers(
        searchQuery: String,
        page: Int,
        count: Int
    ): Flow<Resource<SearchResponse>> {
        return if (searchQuery.contains("user"))
            flowOf(Resource.Success(data = searchResponse))
        else
            flowOf(Resource.Success(data = emptySearchResponse))
    }

    override suspend fun getUserDetails(username: String): Flow<Resource<User>> {
        return flowOf(Resource.Success(data = userList[0]))
    }

    override suspend fun getFollowersUser(username: String): Flow<Resource<UserListResponse>> {
        if (username != "user1")
            userListResponse.add(userList[0])
        return flowOf(Resource.Success(data = userListResponse))
    }

    override suspend fun getFollowingUser(username: String): Flow<Resource<UserListResponse>> {
        if (username != "user1")
            userListResponse.add(userList[0])
        return flowOf(Resource.Success(data = userListResponse))
    }

    override suspend fun addFavoriteUser(user: User) {
    }

    override suspend fun deleteFavoriteUser(user: User) {
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        return flowOf(userList)
    }

    override suspend fun isExistInFavorites(username: String): Flow<Boolean> {
        return if (username == "user1")
            flowOf(true)
        else
            flowOf(false)
    }
}