package com.miladisaei.githubusers.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.miladisaei.githubusers.data.repository.FakeRepositoryImpl
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavoriteUsersUseCaseTest {

    private lateinit var getFavoriteUsersUseCase: GetFavoriteUsersUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        getFavoriteUsersUseCase = GetFavoriteUsersUseCase(fakeRepository)
    }

    @Test
    fun getFavoriteUsers_success() = runBlocking {
        getFavoriteUsersUseCase.execute().collect {
            assertThat(it.size).isEqualTo(1)
            assertThat(it[0].id).isEqualTo(1)
            assertThat(it[0].username).isEqualTo("user1")
            assertThat(it[0].bio).isEqualTo("userBio")
            assertThat(it[0].followingCount).isEqualTo(6)
        }
    }
}