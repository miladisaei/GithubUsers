package com.miladisaei.githubusers.di

import android.app.Application
import androidx.room.Room
import com.miladisaei.githubusers.data.db.UserDAO
import com.miladisaei.githubusers.data.db.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideUserDataBase(app: Application): UserDataBase {
        return Room.databaseBuilder(app, UserDataBase::class.java, "github_users_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDataBase: UserDataBase): UserDAO {
        return userDataBase.getUserDAO()
    }
}