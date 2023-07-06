package com.mpersand.data.network.api

import retrofit2.http.DELETE

interface UserApi {
    @DELETE("user/logout")
    suspend fun logout()
}