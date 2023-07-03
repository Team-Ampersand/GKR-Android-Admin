package com.mpersand.data.repository

import com.mpersand.data.dto.auth.request.asSignInRequest
import com.mpersand.data.dto.auth.response.asSignInResponseModel
import com.mpersand.data.remote.datasource.auth.AuthDataSource
import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.model.auth.response.SignInResponseModel
import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun signIn(signInRequest: SignInRequestModel): SignInResponseModel =
        authDataSource.signIn(signInRequest = signInRequest.asSignInRequest()).asSignInResponseModel()
}