package com.mpersand.domain.usecase.auth

import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(signInRequestModel: SignInRequestModel) = repository.signIn(signInRequestModel)
}