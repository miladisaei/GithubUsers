package com.miladisaei.githubusers.di

import com.miladisaei.githubusers.data.api.UserAPIService
import com.miladisaei.githubusers.data.repository.dataSource.UserRemoteDataSource
import com.miladisaei.githubusers.data.repository.dataSourceImpl.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userAPIService: UserAPIService
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userAPIService)
    }
}