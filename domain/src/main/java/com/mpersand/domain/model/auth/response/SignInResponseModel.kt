package com.mpersand.domain.model.auth.response

import java.time.ZonedDateTime

data class SignInResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: ZonedDateTime,
    val refreshTokenExp: ZonedDateTime
)