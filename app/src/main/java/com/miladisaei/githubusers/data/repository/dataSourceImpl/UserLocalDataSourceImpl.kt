package com.miladisaei.githubusers.data.repository.dataSourceImpl

import com.miladisaei.githubusers.data.db.UserDAO
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.repository.dataSource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSourceImpl
@Inject
constructor(
    private val userDAO: UserDAO
) : UserLocalDataSource {

    override suspend fun addToFavoriteUsers(user: User) {
        userDAO.insertFavorite(user)
    }

    override suspend fun deleteFromFavoriteUsers(user: User) {
        userDAO.deleteFavorite(user)
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        return userDAO.getFavoriteUsers()
    }

    override suspend fun isExistInFavorites(username: String): Flow<Boolean> {
        return userDAO.isExistInFavorites(username)
    }

}