package com.mpersand.domain.repository

import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.model.auth.response.SignInResponseModel

interface AuthRepository {
    suspend fun signIn(signInRequest: SignInRequestModel): SignInResponseModel

    suspend fun logout()

    suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        accessTokenExp: String,
        refreshTokenExp: String
    )

    suspend fun isLogin(): Boolean

    suspend fun removeLocalData()
}
