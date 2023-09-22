package com.mpersand.data.dto.auth.response

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.auth.response.SignInResponseModel

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    @SerializedName("accessExp") val accessTokenExp: String,
    @SerializedName("refreshExp") val refreshTokenExp: String
)

fun SignInResponse.asSignInResponseModel() = SignInResponseModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExp = accessTokenExp,
    refreshTokenExp = refreshTokenExp
)
