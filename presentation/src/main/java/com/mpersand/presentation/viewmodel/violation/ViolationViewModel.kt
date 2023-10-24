package com.mpersand.presentation.viewmodel.violation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel
import com.mpersand.domain.model.violation.request.ViolationRequestModel
import com.mpersand.domain.usecase.auth.RemoveLocalDataUseCase
import com.mpersand.domain.usecase.order.GetNoReturnListUseCase
import com.mpersand.domain.usecase.violation.PostViolationUserUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViolationViewModel @Inject constructor(
    private val getNoReturnStudentsUseCase: GetNoReturnListUseCase,
    private val postViolationUserUseCase: PostViolationUserUseCase,
    private val removeLocalDataUseCase: RemoveLocalDataUseCase
) : ViewModel() {
    private val _getNoReturnStudentsUiState = MutableLiveData<UiState<OrderApplicationListResponseModel>>()
    val getNoReturnStudentsUiState: LiveData<UiState<OrderApplicationListResponseModel>> = _getNoReturnStudentsUiState

    private val _postViolationUserUiState = MutableLiveData<UiState<Nothing>>(UiState.Loading)
    val postViolationUserUiState: LiveData<UiState<Nothing>> = _postViolationUserUiState

    fun getNoReturnStudents() {
        viewModelScope.launch {
            getNoReturnStudentsUseCase()
                .onSuccess {
                    _getNoReturnStudentsUiState.value = UiState.Success(it)
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _getNoReturnStudentsUiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _getNoReturnStudentsUiState.value = UiState.Unauthorized
                        }
                    )
                }
        }
    }

    fun postViolationUser(violationRequestModel: ViolationRequestModel) {
        viewModelScope.launch {
            postViolationUserUseCase(violationRequestModel)
                .onSuccess {
                    _postViolationUserUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = {
                            removeLocalDataUseCase()
                            _getNoReturnStudentsUiState.value = UiState.Unauthorized
                        },
                        forbiddenAction = {
                            _getNoReturnStudentsUiState.value = UiState.Forbidden
                        },
                        notFoundAction = {
                            _getNoReturnStudentsUiState.value = UiState.NotFound
                        },
                        conflictAction = {
                            _getNoReturnStudentsUiState.value = UiState.Conflict
                        },
                        serverAction = {
                            _getNoReturnStudentsUiState.value = UiState.Server
                        }
                    )
                }
        }
    }
}
