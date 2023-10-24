package com.mpersand.domain.usecase.auth

import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class RemoveLocalDataUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.removeLocalData()
}
