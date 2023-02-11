package com.miladisaei.githubusers.di

import com.miladisaei.githubusers.data.db.UserDAO
import com.miladisaei.githubusers.data.repository.dataSource.UserLocalDataSource
import com.miladisaei.githubusers.data.repository.dataSourceImpl.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(userDAO: UserDAO): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDAO)
    }
}