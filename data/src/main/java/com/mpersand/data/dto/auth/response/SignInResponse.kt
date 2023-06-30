package com.mpersand.data.dto.auth.response

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)
