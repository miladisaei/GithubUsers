package com.miladisaei.githubusers.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.miladisaei.githubusers.data.repository.FakeRepositoryImpl
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFollowingUseCaseTest {

    private lateinit var getFollowingUseCase: GetFollowingUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        getFollowingUseCase = GetFollowingUseCase(fakeRepository)
    }

    @Test
    fun getFollowingUsers_success() = runBlocking {
        getFollowingUseCase.execute(
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
    fun getFollowingUsers_empty() = runBlocking {
        getFollowingUseCase.execute(
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