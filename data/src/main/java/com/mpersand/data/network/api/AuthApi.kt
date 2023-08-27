package com.mpersand.data.network.api

import com.mpersand.data.dto.auth.request.SignInRequest
import com.mpersand.data.dto.auth.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthApi {
    @POST("auth")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): SignInResponse

    @DELETE("auth")
    suspend fun logout()
}