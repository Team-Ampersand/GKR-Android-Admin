package com.mpersand.presentation.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.repair.response.RepairResponseModel
import com.mpersand.domain.usecase.equipment.GetEquipmentDetailUseCase
import com.mpersand.domain.usecase.repair.GetRepairHistoryUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.combineResults
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEquipmentDetailUseCase: GetEquipmentDetailUseCase,
    private val getRepairHistoryUseCase: GetRepairHistoryUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<Pair<EquipmentResponseModel, List<RepairResponseModel>>>>()
    val uiState: LiveData<UiState<Pair<EquipmentResponseModel, List<RepairResponseModel>>>> = _uiState

    fun getEquipmentInfo(equipmentNumber: String) {
        viewModelScope.launch {
            combineResults(
                { getEquipmentDetailUseCase(equipmentNumber) },
                { getRepairHistoryUseCase(equipmentNumber) }
            ).onSuccess {
                _uiState.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    unauthorizedAction = { _uiState.value = UiState.Unauthorized },
                    notFoundAction = { _uiState.value = UiState.NotFound },
                    serverAction = { _uiState.value = UiState.Server }
                )
            }
        }
    }
}