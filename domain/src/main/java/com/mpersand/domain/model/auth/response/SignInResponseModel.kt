package com.mpersand.domain.model.auth.response

data class SignInResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)
