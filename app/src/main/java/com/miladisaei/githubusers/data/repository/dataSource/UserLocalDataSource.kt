package com.miladisaei.githubusers.data.repository.dataSource

import com.miladisaei.githubusers.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun addToFavoriteUsers(user: User)
    suspend fun deleteFromFavoriteUsers(user: User)
    fun getFavoriteUsers(): Flow<List<User>>
    suspend fun isExistInFavorites(username: String): Flow<Boolean>
}