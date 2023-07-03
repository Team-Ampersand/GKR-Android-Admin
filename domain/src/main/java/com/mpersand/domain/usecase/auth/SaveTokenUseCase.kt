package com.mpersand.domain.usecase.auth

import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String, accessTokenExp: String, refreshTokenExp: String) =
        authRepository.saveToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExp = accessTokenExp,
            refreshTokenExp = refreshTokenExp
        )
}