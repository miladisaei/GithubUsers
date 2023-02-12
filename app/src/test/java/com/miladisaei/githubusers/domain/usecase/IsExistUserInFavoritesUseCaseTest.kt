package com.miladisaei.githubusers.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.miladisaei.githubusers.data.repository.FakeRepositoryImpl
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class IsExistUserInFavoritesUseCaseTest {

    private lateinit var isExistUserInFavoritesUseCase: IsExistUserInFavoritesUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        isExistUserInFavoritesUseCase = IsExistUserInFavoritesUseCase(fakeRepository)
    }

    @Test
    fun isExistUserInFavorites_exist() = runBlocking {
        isExistUserInFavoritesUseCase.execute(
            username = "user1"
        ).collect {
            assertThat(it).isTrue()
        }
    }

    @Test
    fun isExistUserInFavorites_notExist() = runBlocking {
        isExistUserInFavoritesUseCase.execute(
            username = "user2"
        ).collect {
            assertThat(it).isFalse()
        }
    }
}