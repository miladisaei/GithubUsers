package com.miladisaei.githubusers.di

import com.miladisaei.githubusers.domain.repository.UserRepository
import com.miladisaei.githubusers.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetSearchedUsersUseCase(
        userRepository: UserRepository
    ): GetSearchedUsersUseCase {
        return GetSearchedUsersUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailsUseCase(
        userRepository: UserRepository
    ): GetUserDetailsUseCase {
        return GetUserDetailsUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetFollowersUseCase(
        userRepository: UserRepository
    ): GetFollowersUseCase {
        return GetFollowersUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetFollowingUseCase(
        userRepository: UserRepository
    ): GetFollowingUseCase {
        return GetFollowingUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideAddFavoriteUserUseCase(
        userRepository: UserRepository
    ): AddFavoriteUserUseCase {
        return AddFavoriteUserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteFavoriteUserUseCase(
        userRepository: UserRepository
    ): DeleteFavoriteUserUseCase {
        return DeleteFavoriteUserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetFavoriteUsersUseCase(
        userRepository: UserRepository
    ): GetFavoriteUsersUseCase {
        return GetFavoriteUsersUseCase(userRepository)
    }
}