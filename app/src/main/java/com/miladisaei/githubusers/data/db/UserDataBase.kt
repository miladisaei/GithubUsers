package com.miladisaei.githubusers.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miladisaei.githubusers.data.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDAO(): UserDAO
}