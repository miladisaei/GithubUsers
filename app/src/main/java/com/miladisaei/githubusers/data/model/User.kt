package com.miladisaei.githubusers.data.model


import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id: Int,

    @SerializedName("login")
    val username: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("company")
    val company: String?,

    @SerializedName("location")
    val location: String?,

    @SerializedName("followers")
    val followersCount: Int?,

    @SerializedName("following")
    val followingCount: Int?,

    @SerializedName("public_repos")
    val repositoryCount: Int?,

    @SerializedName("html_url")
    val htmlUrl: String,
)