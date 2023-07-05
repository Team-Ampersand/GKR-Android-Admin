package com.mpersand.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.usecase.equipment.GetEquipmentsByFilterUseCase
import com.mpersand.domain.usecase.equipment.GetRentedEquipmentsUseCase
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import com.mpersand.presentation.viewmodel.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEquipmentsByFilterUseCase: GetEquipmentsByFilterUseCase,
    private val getRentedEquipmentsUseCase: GetRentedEquipmentsUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<List<EquipmentResponseModel>>>()
    val uiState: LiveData<UiState<List<EquipmentResponseModel>>> = _uiState

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
}