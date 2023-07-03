package com.mpersand.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.usecase.auth.SaveTokenUseCase
import com.mpersand.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
): ViewModel() {
    fun signIn(code: String) {
        viewModelScope.launch {
            signInUseCase(SignInRequestModel(code = code))
                .onSuccess {
                    Log.d("signIn", it.toString())
                    saveTokenUseCase(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        accessTokenExp = it.accessTokenExp,
                        refreshTokenExp = it.refreshTokenExp
                    )
                }.onFailure {
                    Log.d("signIn", it.message.toString())
                }
        }
    }
}