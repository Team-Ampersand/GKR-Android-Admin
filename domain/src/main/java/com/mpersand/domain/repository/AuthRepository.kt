package com.mpersand.domain.repository

import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.model.auth.response.SignInResponseModel

interface AuthRepository {
    suspend fun signIn(signInRequest: SignInRequestModel): SignInResponseModel
}