package com.mpersand.domain.usecase.auth

import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class IsLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { repository.isLogin() }
}