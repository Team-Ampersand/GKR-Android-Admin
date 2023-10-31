package com.mpersand.presentation.viewmodel.repair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.usecase.auth.RemoveLocalDataUseCase
import com.mpersand.domain.usecase.equipment.ChangeEquipmentToRepairingUseCase
import com.mpersand.domain.usecase.equipment.CompleteEquipmentRepairUseCase
import com.mpersand.domain.usecase.equipment.GetEquipmentDetailUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepairViewModel @Inject constructor(
    private val changeEquipmentToRepairingUseCase: ChangeEquipmentToRepairingUseCase,
    private val completeEquipmentRepairUseCase: CompleteEquipmentRepairUseCase,
    private val getEquipmentDetailUseCase: GetEquipmentDetailUseCase,
    private val removeLocalDataUseCase: RemoveLocalDataUseCase
) : ViewModel() {
    private val _detailState = MutableLiveData<UiState<EquipmentResponseModel>>()
    val detailState: LiveData<UiState<EquipmentResponseModel>> = _detailState

    private val _repairState = MutableLiveData<UiState<Nothing>>()
    val repairState: LiveData<UiState<Nothing>> = _repairState

    fun changeEquipmentToRepairing(productNumber: String) {
        viewModelScope.launch {
            changeEquipmentToRepairingUseCase(productNumber)
                .onSuccess {
                    _repairState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _repairState.value = UiState.Unauthorized
                        },
                        forbiddenAction = { _repairState.value = UiState.Forbidden },
                        notFoundAction = { _repairState.value = UiState.NotFound },
                        conflictAction = { _repairState.value = UiState.Conflict },
                        serverAction = { _repairState.value = UiState.Server }
                    )
                }
        }
    }

    fun completeEquipmentRepair(productNumber: String) {
        viewModelScope.launch {
            completeEquipmentRepairUseCase(productNumber)
                .onSuccess {
                    _repairState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _repairState.value = UiState.Unauthorized
                        },
                        forbiddenAction = { _repairState.value = UiState.Forbidden },
                        notFoundAction = { _repairState.value = UiState.NotFound },
                        conflictAction = { _repairState.value = UiState.Conflict },
                        serverAction = { _repairState.value = UiState.Server }
                    )
                }
        }
    }

    fun getRepairDetail(productNumber: String) {
        viewModelScope.launch {
            getEquipmentDetailUseCase(productNumber)
                .onSuccess {
                    _detailState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _detailState.value = UiState.BadRequest },
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _detailState.value = UiState.Unauthorized
                        },
                        notFoundAction = { _detailState.value = UiState.NotFound }
                    )
                }

        }
    }
}
