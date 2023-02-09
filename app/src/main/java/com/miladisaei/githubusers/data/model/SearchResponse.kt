package com.miladisaei.githubusers.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("items")
    val items: List<User>,

    @SerializedName("total_count")
    val totalCount: Int
)