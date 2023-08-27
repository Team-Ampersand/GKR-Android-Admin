package com.mpersand.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.user.response.UserResponseModel
import com.mpersand.domain.usecase.equipment.GetEquipmentsByFilterUseCase
import com.mpersand.domain.usecase.equipment.GetNotRentedEquipmentsUseCase
import com.mpersand.domain.usecase.equipment.GetRentedEquipmentsUseCase
import com.mpersand.domain.usecase.user.GetUserUseCase
import com.mpersand.domain.usecase.auth.LogoutUseCase
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import com.mpersand.presentation.viewmodel.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEquipmentsByFilterUseCase: GetEquipmentsByFilterUseCase,
    private val getRentedEquipmentsUseCase: GetRentedEquipmentsUseCase,
    private val getNotRentedEquipmentsUseCase: GetNotRentedEquipmentsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<List<EquipmentResponseModel>>>()
    val uiState: LiveData<UiState<List<EquipmentResponseModel>>> = _uiState

    private val _logoutState = MutableLiveData<UiState<Nothing>>(UiState.Loading)
    val logoutState: LiveData<UiState<Nothing>> = _logoutState

    private val _getUserInfoUiState = MutableLiveData<UiState<UserResponseModel>>()
    val getUserInfoUiState: LiveData<UiState<UserResponseModel>> = _getUserInfoUiState

    fun getRentedEquipments() {
        viewModelScope.launch {
            getRentedEquipmentsUseCase()
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _uiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _uiState.value = UiState.Unauthorized
                        },
                        notFoundAction = {
                            _uiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }

    fun getNotRentedEquipments() {
        viewModelScope.launch {
            getNotRentedEquipmentsUseCase().onSuccess {
                _uiState.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = {
                        _uiState.value = UiState.BadRequest
                    },
                    unauthorizedAction = {
                        _uiState.value = UiState.Unauthorized
                    },
                    notFoundAction = {
                        _uiState.value = UiState.NotFound
                    }
                )
            }
        }
    }

    fun getEquipmentsByFilter(name: String) {
        viewModelScope.launch {
            getEquipmentsByFilterUseCase(name)
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _uiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _uiState.value = UiState.Unauthorized
                        },
                        forbiddenAction = {
                            _uiState.value = UiState.Forbidden
                        },
                        notFoundAction = {
                            _uiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .onSuccess {
                    _logoutState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _logoutState.value = UiState.BadRequest },
                        unauthorizedAction = { _logoutState.value = UiState.Unauthorized },
                        notFoundAction = { _logoutState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            getUserUseCase()
                .onSuccess {
                    _getUserInfoUiState.value = UiState.Success(it)
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _getUserInfoUiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _getUserInfoUiState.value = UiState.Unauthorized
                        },
                        notFoundAction = {
                            _getUserInfoUiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }
}