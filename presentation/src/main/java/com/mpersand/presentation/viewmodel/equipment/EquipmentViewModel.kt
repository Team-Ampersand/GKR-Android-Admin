package com.mpersand.presentation.viewmodel.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentListResponseModel
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.usecase.auth.RemoveLocalDataUseCase
import com.mpersand.domain.usecase.equipment.GetEquipmentDetailUseCase
import com.mpersand.domain.usecase.equipment.GetEquipmentsByFilterUseCase
import com.mpersand.domain.usecase.equipment.ModifyEquipmentUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val modifyEquipmentUseCase: ModifyEquipmentUseCase,
    private val getEquipmentDetailUseCase: GetEquipmentDetailUseCase,
    private val getEquipmentsByFilterUseCase: GetEquipmentsByFilterUseCase,
    private val removeLocalDataUseCase: RemoveLocalDataUseCase
) : ViewModel() {
    private val _equipmentState = MutableLiveData<UiState<EquipmentResponseModel>>()
    val equipmentState: LiveData<UiState<EquipmentResponseModel>> = _equipmentState

    private val _modifyState = MutableLiveData<UiState<Nothing>>()
    val modifyState: LiveData<UiState<Nothing>> = _modifyState

    private val _equipmentFilter = MutableLiveData<UiState<EquipmentListResponseModel>>()
    val equipmentFilter: LiveData<UiState<EquipmentListResponseModel>> = _equipmentFilter

    fun modifyEquipment(productNumber: String, file: MultipartBody.Part, name: String, description: String, equipmentType: String) {
        val jsonObject = JSONObject()
        jsonObject.apply {
            put("name", name)
            put("description", description)
            put("equipmentType", equipmentType)
        }
        val equipment = jsonObject.toString().toRequestBody("application/json".toMediaType())

        viewModelScope.launch {
            modifyEquipmentUseCase(productNumber, file, equipment)
                .onSuccess {
                    _modifyState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        noContentAction = { _modifyState.value = UiState.NoContent },
                        badRequestAction = { _modifyState.value = UiState.BadRequest },
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _modifyState.value = UiState.Unauthorized
                        },
                        forbiddenAction = { _modifyState.value = UiState.Forbidden },
                        notFoundAction = { _modifyState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun getEquipmentDetail(productNumber: String) {
        viewModelScope.launch {
            getEquipmentDetailUseCase(productNumber)
                .onSuccess {
                    _equipmentState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _equipmentState.value = UiState.BadRequest },
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _equipmentState.value = UiState.Unauthorized
                        },
                        notFoundAction = { _equipmentState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun searchEquipment(name: String) {
        viewModelScope.launch {
            getEquipmentsByFilterUseCase(name).onSuccess {
                _equipmentFilter.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { _equipmentFilter.value = UiState.BadRequest },
                    unauthorizedAction = {
                        removeLocalDataUseCase()
                        _equipmentFilter.value = UiState.Unauthorized
                    },
                    notFoundAction = { _equipmentFilter.value = UiState.NotFound }
                )
            }
        }
    }
}
