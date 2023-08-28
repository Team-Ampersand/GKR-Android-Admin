package com.mpersand.data.dto.auth.response

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.auth.response.SignInResponseModel
import java.time.ZonedDateTime

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    @SerializedName("accessExp") val accessTokenExp: ZonedDateTime,
    @SerializedName("refreshExp") val refreshTokenExp: ZonedDateTime
)

fun SignInResponse.asSignInResponseModel() = SignInResponseModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExp = accessTokenExp,
    refreshTokenExp = refreshTokenExp
)