package com.mpersand.data.network.api

import com.mpersand.data.dto.user.response.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.GET

interface UserApi {
    @DELETE("user/logout")
    suspend fun logout()

    @GET("user")
    suspend fun getUser(): UserResponse
}