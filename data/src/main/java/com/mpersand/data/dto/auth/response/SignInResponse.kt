package com.mpersand.data.dto.auth.response

import com.mpersand.domain.model.auth.response.SignInResponseModel

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)

fun SignInResponse.asSignInResponseModel() = SignInResponseModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExp = accessTokenExp,
    refreshTokenExp = refreshTokenExp
)