package com.miladisaei.githubusers.data.db

import androidx.room.*
import com.miladisaei.githubusers.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(user: User)

    @Delete
    suspend fun deleteFavorite(user: User)

    @Query("SELECT * FROM users")
    fun getFavoriteUsers(): Flow<List<User>>

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE username = :username)")
    fun isExistInFavorites(username: String): Flow<Boolean>
}