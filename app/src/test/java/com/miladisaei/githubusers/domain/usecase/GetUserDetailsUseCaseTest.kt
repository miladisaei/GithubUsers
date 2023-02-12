package com.miladisaei.githubusers.domain.usecase

import com.google.common.truth.Truth
import com.miladisaei.githubusers.data.repository.FakeRepositoryImpl
import com.miladisaei.githubusers.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserDetailsUseCaseTest {

    private lateinit var getUserDetailsUseCase: GetUserDetailsUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        getUserDetailsUseCase = GetUserDetailsUseCase(fakeRepository)
    }

    @Test
    fun getUserDetails_success() = runBlocking {
        getUserDetailsUseCase.execute(
            username = "user1"
        ).collect {
            when (it) {
                is Resource.Success -> {
                    Truth.assertThat(it.data?.id).isEqualTo(1)
                    Truth.assertThat(it.data?.username).isEqualTo("user1")
                    Truth.assertThat(it.data?.bio).isEqualTo("userBio")
                    Truth.assertThat(it.data?.followingCount).isEqualTo(6)
                }
                else -> {}
            }
        }
    }
}