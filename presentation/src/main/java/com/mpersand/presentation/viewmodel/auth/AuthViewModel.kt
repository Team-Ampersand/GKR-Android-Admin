package com.mpersand.presentation.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.usecase.auth.IsLoginUseCase
import com.mpersand.domain.usecase.auth.SaveTokenUseCase
import com.mpersand.domain.usecase.auth.SignInUseCase
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import com.mpersand.presentation.viewmodel.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val isLoginUseCase: IsLoginUseCase
) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<Boolean>>(UiState.Loading)
    val loginState: LiveData<UiState<Boolean>> = _loginState

    private val _uiState = MutableLiveData<UiState<Nothing>>()
    val uiState: LiveData<UiState<Nothing>> = _uiState

    fun isLogin() {
        viewModelScope.launch {
            isLoginUseCase()
                .onSuccess {
                    _loginState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        unknownAction = { _loginState.value = UiState.Unknown }
                    )
                }
        }
    }

    fun signIn(code: String) {
        viewModelScope.launch {
            signInUseCase(SignInRequestModel(code = code))
                .onSuccess {
                    saveTokenUseCase(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        accessTokenExp = it.accessTokenExp,
                        refreshTokenExp = it.refreshTokenExp
                    )
                    _uiState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _uiState.value = UiState.BadRequest
                        }
                    )
                }
        }
    }
}