package com.miladisaei.githubusers.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.miladisaei.githubusers.data.repository.FakeRepositoryImpl
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFollowersUseCaseTest {

    private lateinit var getFollowersUseCase: GetFollowersUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        getFollowersUseCase = GetFollowersUseCase(fakeRepository)
    }

    @Test
    fun getFollowersUsers_success() = runBlocking {
        getFollowersUseCase.execute(
            username = "user2"
        ).collect {
            when (it) {
                is Resource.Success -> {
                    assertThat(it.data?.size).isEqualTo(1)
                    assertThat(it.data?.get(0)?.id).isEqualTo(1)
                    assertThat(it.data?.get(0)?.username).isEqualTo("user1")
                    assertThat(it.data?.get(0)?.bio).isEqualTo("userBio")
                    assertThat(it.data?.get(0)?.followingCount).isEqualTo(6)
                }
                else -> {}
            }
        }
    }

    @Test
    fun getFollowersUsers_empty() = runBlocking {
        getFollowersUseCase.execute(
            username = "user1"
        ).collect {
            when (it) {
                is Resource.Success -> {
                    assertThat(it.data).isEmpty()
                }
                else -> {}
            }
        }
    }
}