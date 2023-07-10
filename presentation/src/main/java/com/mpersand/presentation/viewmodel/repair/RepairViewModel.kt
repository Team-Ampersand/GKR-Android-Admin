package com.mpersand.presentation.viewmodel.repair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.repair.request.RepairRequestModel
import com.mpersand.domain.usecase.repair.ModifyRepairHistoryUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepairViewModel @Inject constructor(
    private val modifyRepairHistoryUseCase: ModifyRepairHistoryUseCase
): ViewModel() {
    private val _uiState = MutableLiveData<UiState<Nothing>>()
    val uiState: LiveData<UiState<Nothing>> = _uiState

    fun modifyRepairHistory(body: RepairRequestModel) {
        viewModelScope.launch {
            modifyRepairHistoryUseCase(body)
                .onSuccess {
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