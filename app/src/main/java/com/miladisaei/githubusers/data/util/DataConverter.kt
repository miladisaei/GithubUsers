package com.miladisaei.githubusers.data.util

import retrofit2.Response

object DataConverter {

    fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(data = result)
            }
        }
        return Resource.Error(message = response.message())
    }
}