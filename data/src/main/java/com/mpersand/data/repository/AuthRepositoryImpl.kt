package com.mpersand.data.repository

import com.mpersand.data.dto.auth.request.asSignInRequest
import com.mpersand.data.dto.auth.response.asSignInResponseModel
import com.mpersand.data.local.datasource.LocalDataSource
import com.mpersand.data.remote.datasource.auth.AuthDataSource
import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.model.auth.response.SignInResponseModel
import com.mpersand.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: LocalDataSource
) : AuthRepository {
    override suspend fun signIn(signInRequest: SignInRequestModel): SignInResponseModel =
        authDataSource.signIn(signInRequest.asSignInRequest()).asSignInResponseModel()

    override suspend fun logout() {
        localDataSource.logout()
        authDataSource.logout()
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String, accessTokenExp: String, refreshTokenExp: String) {
        localDataSource.saveToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExp = accessTokenExp,
            refreshTokenExp = refreshTokenExp
        )
    }

    override suspend fun isLogin(): Boolean = localDataSource.isLogin().first()
}
