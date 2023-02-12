package com.miladisaei.githubusers.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.miladisaei.githubusers.data.repository.FakeRepositoryImpl
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSearchedUsersUseCaseTest {

    private lateinit var getSearchedUsersUseCase: GetSearchedUsersUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        getSearchedUsersUseCase = GetSearchedUsersUseCase(fakeRepository)
    }

    @Test
    fun getSearchedUsers_success() = runBlocking {
        getSearchedUsersUseCase.execute(
            searchQuery = "user",
            page = 1,
            count = 10
        ).collect {
            when (it) {
                is Resource.Success -> {
                    assertThat(it.data?.users).isNotEmpty()
                    assertThat(it.data?.totalCount).isEqualTo(1)
                    assertThat(it.data?.users?.size).isEqualTo(1)
                    assertThat(it.data?.users?.get(0)?.id).isEqualTo(1)
                    assertThat(it.data?.users?.get(0)?.username).isEqualTo("user1")
                    assertThat(it.data?.users?.get(0)?.bio).isEqualTo("userBio")
                    assertThat(it.data?.users?.get(0)?.followingCount).isEqualTo(6)
                }
                else -> {}
            }
        }
    }

    @Test
    fun getSearchedUsers_empty() = runBlocking {
        getSearchedUsersUseCase.execute(
            searchQuery = "test",
            page = 1,
            count = 10
        ).collect {
            when (it) {
                is Resource.Success -> {
                    assertThat(it.data?.users).isEmpty()
                    assertThat(it.data?.totalCount).isEqualTo(0)
                }
                else -> {}
            }
        }
    }
}