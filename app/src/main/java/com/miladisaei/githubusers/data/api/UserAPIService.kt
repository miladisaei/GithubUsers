package com.miladisaei.githubusers.data.api

import com.miladisaei.githubusers.BuildConfig
import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.model.UserListResponse
import com.miladisaei.githubusers.util.USER_PAGINATION_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPIService {

    @GET("search/users")
    suspend fun getSearchedUser(
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int,
        @Query("per_page")
        count: Int = USER_PAGINATION_PAGE_SIZE
    ): Response<SearchResponse>

    @GET("users/{user}")
    suspend fun getUserDetails(
        @Path("user")
        username: String
    ): Response<User>

    @GET("users/{user}/followers")
    suspend fun getFollowersUser(
        @Path("user")
        username: String
    ): Response<UserListResponse>

    @GET("users/{user}/following")
    suspend fun getFollowingUser(
        @Path("user")
        username: String
    ): Response<UserListResponse>
}