package com.miladisaei.githubusers.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserAPIServiceTest {

    private lateinit var service: UserAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPIService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


    /*
     *
     Tests for getSearchedUser
     *
     */
    @Test
    fun getSearchedUser_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("searchUserResponse.json")
            val responseBody = service.getSearchedUser(
                searchQuery = "milad",
                page = 1,
                count = 10
            ).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/search/users?q=milad&page=1&per_page=10")
        }
    }

    @Test
    fun getSearchedUser_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("searchUserResponse.json")
            val responseBody = service.getSearchedUser(
                searchQuery = "milad",
                page = 1,
                count = 10
            ).body()
            val users = responseBody!!.users
            assertThat(users.size).isEqualTo(10)
        }
    }

    @Test
    fun getSearchedUser_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("searchUserResponse.json")
            val responseBody = service.getSearchedUser(
                searchQuery = "milad",
                page = 1,
                count = 10
            ).body()
            val usersList = responseBody!!.users
            val user = usersList[0]
            assertThat(user.id).isEqualTo(94392)
            assertThat(user.username).isEqualTo("Milad")
            assertThat(user.name).isNull()
        }
    }

    /*
     *
     Tests for getUserDetails
     *
     */
    @Test
    fun getUserDetails_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("userDetailsResponse.json")
            val responseBody = service.getUserDetails("Aditprayogo").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/users/Aditprayogo")
        }
    }

    @Test
    fun getUserDetails_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("userDetailsResponse.json")
            val responseBody = service.getUserDetails("Aditprayogo").body()
            assertThat(responseBody?.id).isEqualTo(44943442)
            assertThat(responseBody?.username).isEqualTo("Aditprayogo")
            assertThat(responseBody?.name).isEqualTo("Aditiya Prayogo")
            assertThat(responseBody?.bio).isEqualTo("Android Engineer")
            assertThat(responseBody?.company).isEqualTo("Learn Self")
        }
    }

    /*
     *
     Tests for getFollowersUser
     *
     */
    @Test
    fun getFollowersUser_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("followersUserResponse.json")
            val responseBody = service.getFollowersUser("Aditprayogo").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/users/Aditprayogo/followers")
        }
    }

    @Test
    fun getFollowersUser_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("followersUserResponse.json")
            val responseBody = service.getFollowersUser("Aditprayogo").body()
            assertThat(responseBody?.size).isEqualTo(30)
        }
    }

    @Test
    fun getFollowersUser_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("followersUserResponse.json")
            val responseBody = service.getFollowersUser("Aditprayogo").body()
            val user = responseBody!![0]
            assertThat(user.id).isEqualTo(4045224)
            assertThat(user.username).isEqualTo("cyberbnet")
            assertThat(user.name).isNull()
        }
    }

    /*
     *
     Tests for getFollowingUser
     *
     */
    @Test
    fun getFollowingUser_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("followingUserResponse.json")
            val responseBody = service.getFollowingUser("Aditprayogo").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/users/Aditprayogo/following")
        }
    }

    @Test
    fun getFollowingUser_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("followingUserResponse.json")
            val responseBody = service.getFollowingUser("Aditprayogo").body()
            assertThat(responseBody?.size).isEqualTo(30)
        }
    }

    @Test
    fun getFollowingUser_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("followingUserResponse.json")
            val responseBody = service.getFollowingUser("Aditprayogo").body()
            val user = responseBody!![0]
            assertThat(user.id).isEqualTo(6302)
            assertThat(user.username).isEqualTo("joreilly")
            assertThat(user.name).isNull()
        }
    }
}