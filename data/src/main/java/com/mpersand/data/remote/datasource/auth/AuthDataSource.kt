package com.mpersand.data.remote.datasource.auth

import com.mpersand.data.dto.auth.request.SignInRequest
import com.mpersand.data.dto.auth.response.SignInResponse

interface AuthDataSource {
    suspend fun signIn(signInRequest: SignInRequest): SignInResponse

    suspend fun logout()
}