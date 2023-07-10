package com.mpersand.presentation.viewmodel.repair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.repair.request.RepairRequestModel
import com.mpersand.domain.usecase.equipment.GetEquipmentDetailUseCase
import com.mpersand.domain.usecase.repair.AddRepairHistoryUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepairViewModel @Inject constructor(
    private val addRepairHistoryUseCase: AddRepairHistoryUseCase,
    private val getEquipmentDetailUseCase: GetEquipmentDetailUseCase
): ViewModel() {
    private val _detailState = MutableLiveData<UiState<EquipmentResponseModel>>()
    val detailState: LiveData<UiState<EquipmentResponseModel>> = _detailState

    private val _repairState = MutableLiveData<UiState<Nothing>>()
    val repairState: LiveData<UiState<Nothing>> = _repairState

    fun addRepairHistory(body: RepairRequestModel) {
        viewModelScope.launch {
            addRepairHistoryUseCase(body)
                .onSuccess {
                    _repairState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _repairState.value = UiState.BadRequest
                        }
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
                        unauthorizedAction = { _detailState.value = UiState.Unauthorized },
                        notFoundAction = { _detailState.value = UiState.NotFound }
                    )
                }

        }
    }
}