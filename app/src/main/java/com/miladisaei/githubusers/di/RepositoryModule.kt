package com.miladisaei.githubusers.di

import com.miladisaei.githubusers.data.repository.UserRepositoryImpl
import com.miladisaei.githubusers.data.repository.dataSource.UserRemoteDataSource
import com.miladisaei.githubusers.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource)
    }
}