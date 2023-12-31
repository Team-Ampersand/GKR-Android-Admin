package com.mpersand.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentListResponseModel
import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel
import com.mpersand.domain.model.user.response.UserResponseModel
import com.mpersand.domain.usecase.equipment.GetEquipmentsByFilterUseCase
import com.mpersand.domain.usecase.user.GetUserUseCase
import com.mpersand.domain.usecase.auth.LogoutUseCase
import com.mpersand.domain.usecase.auth.RemoveLocalDataUseCase
import com.mpersand.domain.usecase.equipment.GetAllEquipmentsUseCase
import com.mpersand.domain.usecase.equipment.GetEquipmentsByStateUseCase
import com.mpersand.domain.usecase.equipment.GetEquipmentsByTypeUseCase
import com.mpersand.domain.usecase.order.GetWaitListUseCase
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import com.mpersand.presentation.viewmodel.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEquipmentsByFilterUseCase: GetEquipmentsByFilterUseCase,
    private val getAllEquipmentsUseCase: GetAllEquipmentsUseCase,
    private val getEquipmentsByStateUseCase: GetEquipmentsByStateUseCase,
    private val getEquipmentsByTypeUseCase: GetEquipmentsByTypeUseCase,
    private val getWaitListUseCase: GetWaitListUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val removeLocalDataUseCase: RemoveLocalDataUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<EquipmentListResponseModel>>()
    val uiState: LiveData<UiState<EquipmentListResponseModel>> = _uiState

    private val _logoutState = MutableLiveData<UiState<Nothing>>(UiState.Loading)
    val logoutState: LiveData<UiState<Nothing>> = _logoutState

    private val _getUserInfoUiState = MutableLiveData<UiState<UserResponseModel>>()
    val getUserInfoUiState: LiveData<UiState<UserResponseModel>> = _getUserInfoUiState

    private val _waitingUiState = MutableLiveData<UiState<OrderApplicationListResponseModel>>()
    val waitingUiState: LiveData<UiState< OrderApplicationListResponseModel>> = _waitingUiState

    fun getAllEquipments() {
        viewModelScope.launch {
            getAllEquipmentsUseCase()
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _uiState.value = UiState.Unauthorized
                        },
                        notFoundAction = { _uiState.value = UiState.NotFound },
                        serverAction = { _uiState.value = UiState.Server }
                    )
                }
        }
    }

    fun getEquipmentsByState(equipmentStatus: String) {
        viewModelScope.launch {
            getEquipmentsByStateUseCase(equipmentStatus)
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _uiState.value = UiState.Unauthorized
                        },
                        serverAction = { _uiState.value = UiState.Server }
                    )
                }
        }
    }

    fun getEquipmentsByType(equipmentType: String) {
        viewModelScope.launch {
            getEquipmentsByTypeUseCase(equipmentType)
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _uiState.value = UiState.Unauthorized
                        },
                        serverAction = { _uiState.value = UiState.Server }
                    )
                }
        }
    }

    fun getWaitingList() {
        viewModelScope.launch {
            getWaitListUseCase()
                .onSuccess {
                    _waitingUiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _waitingUiState.value = UiState.BadRequest },
                        forbiddenAction = { _waitingUiState.value = UiState.Forbidden },
                        serverAction = { _waitingUiState.value = UiState.Server }
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
                            removeLocalDataUseCase()
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
                        noContentAction = { _logoutState.value = UiState.NoContent },
                        badRequestAction = { _logoutState.value = UiState.BadRequest }
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
                            removeLocalDataUseCase()
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
