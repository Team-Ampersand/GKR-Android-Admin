package com.mpersand.presentation.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.domain.model.order.response.OrderEquipmentListResponseModel
import com.mpersand.domain.model.order.response.OrderListResponseModel
import com.mpersand.domain.usecase.order.AcceptRequestUseCase
import com.mpersand.domain.usecase.order.GetNoReturnListUseCase
import com.mpersand.domain.usecase.order.GetNowRentalListUseCase
import com.mpersand.domain.usecase.order.GetSelfStateListUseCase
import com.mpersand.domain.usecase.order.GetWaitListUseCase
import com.mpersand.domain.usecase.order.PostExtensionUseCase
import com.mpersand.domain.usecase.order.PostRentalCancelUseCase
import com.mpersand.domain.usecase.order.PostRentalUseCase
import com.mpersand.domain.usecase.order.PostReturnUseCase
import com.mpersand.domain.usecase.order.RejectRequestUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getSelfStateListUseCase: GetSelfStateListUseCase,
    private val getNowRentalListUseCase: GetNowRentalListUseCase,
    private val getNoReturnListUseCase: GetNoReturnListUseCase,
    private val getWaitListUseCase: GetWaitListUseCase,
    private val postRentalUseCase: PostRentalUseCase,
    private val postReturnUseCase: PostReturnUseCase,
    private val postExtensionUseCase: PostExtensionUseCase,
    private val postRentalCancelUseCase: PostRentalCancelUseCase,
    private val acceptRequestUseCase: AcceptRequestUseCase,
    private val rejectRequestUseCase: RejectRequestUseCase
): ViewModel() {
    private val _getSelfStateListUiState = MutableLiveData<UiState<OrderEquipmentListResponseModel>>()
    val getSelfStateListUiState: LiveData<UiState<OrderEquipmentListResponseModel>> = _getSelfStateListUiState

    private val _getNowRentalListUiState = MutableLiveData<UiState<OrderApplicationListResponseModel>>()
    val getNowRentalListUiState: LiveData<UiState<OrderApplicationListResponseModel>> = _getNowRentalListUiState

    private val _getNoReturnListUiState = MutableLiveData<UiState<OrderApplicationListResponseModel>>()
    val getNoReturnListUiState: LiveData<UiState<OrderApplicationListResponseModel>> = _getNoReturnListUiState

    private val _getWaitListUiState = MutableLiveData<UiState<OrderApplicationListResponseModel>>()
    val getWaitListUiState: LiveData<UiState<OrderApplicationListResponseModel>> = _getWaitListUiState

    private val _postRentalUiState = MutableLiveData<UiState<Nothing>>()
    val postRentalUiState: LiveData<UiState<Nothing>> = _postRentalUiState

    private val _postReturnUiState = MutableLiveData<UiState<Nothing>>()
    val postReturnUiState: LiveData<UiState<Nothing>> = _postReturnUiState

    private val _postExtensionUiState = MutableLiveData<UiState<Nothing>>()
    val postExtensionUiState: LiveData<UiState<Nothing>> = _postExtensionUiState

    private val _postRentalCancelUiState = MutableLiveData<UiState<Nothing>>()
    val postRentalCancelUiState: LiveData<UiState<Nothing>> = _postRentalCancelUiState

    private val _acceptRequestUiState = MutableLiveData<UiState<Nothing>>()
    val acceptRequestUiState: LiveData<UiState<Nothing>> = _acceptRequestUiState

    private val _rejectRequestUiState = MutableLiveData<UiState<Nothing>>()
    val rejectRequestUiState: LiveData<UiState<Nothing>> = _rejectRequestUiState

    fun getSelfStateList() = viewModelScope.launch {
        getSelfStateListUseCase()
            .onSuccess {
                _getSelfStateListUiState.value = UiState.Success(it)
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _getSelfStateListUiState.value = UiState.BadRequest },
                    serverAction = { _getSelfStateListUiState.value = UiState.Server }
                )
            }
    }

    fun getNowRentalList() = viewModelScope.launch {
        getNowRentalListUseCase()
            .onSuccess {
                _getNowRentalListUiState.value = UiState.Success(it)
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _getNowRentalListUiState.value = UiState.BadRequest },
                    forbiddenAction = { _getNowRentalListUiState.value = UiState.Forbidden },
                    serverAction = { _getNowRentalListUiState.value = UiState.Server }
                )
            }
    }

    fun getNoReturnList() = viewModelScope.launch {
        getNoReturnListUseCase()
            .onSuccess {
                _getNoReturnListUiState.value = UiState.Success(it)
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _getNoReturnListUiState.value = UiState.BadRequest },
                    forbiddenAction = { _getNoReturnListUiState.value = UiState.Forbidden },
                    serverAction = { _getNoReturnListUiState.value = UiState.Server }
                )
            }
    }

    fun getWaitList() = viewModelScope.launch {
        getWaitListUseCase()
            .onSuccess {
                _getWaitListUiState.value = UiState.Success(it)
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _getWaitListUiState.value = UiState.BadRequest },
                    forbiddenAction = { _getWaitListUiState.value = UiState.Forbidden },
                    serverAction = { _getWaitListUiState.value = UiState.Server }
                )
            }
    }

    fun postRental(id: Int, response: String) = viewModelScope.launch {
        postRentalUseCase(id, response)
            .onSuccess {
                _postRentalUiState.value = UiState.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _postRentalUiState.value = UiState.BadRequest },
                    forbiddenAction = { _postRentalUiState.value = UiState.Forbidden },
                    notFoundAction = { _postRentalUiState.value = UiState.NotFound },
                    conflictAction = { _postRentalUiState.value = UiState.Conflict },
                    serverAction = { _postRentalUiState.value = UiState.Server }
                )
            }
    }

    fun postReturn(id: Int) = viewModelScope.launch {
        postReturnUseCase(id)
            .onSuccess {
                _postReturnUiState.value = UiState.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _postReturnUiState.value = UiState.BadRequest },
                    forbiddenAction = { _postReturnUiState.value = UiState.Forbidden },
                    notFoundAction = { _postReturnUiState.value = UiState.NotFound },
                    conflictAction = { _postReturnUiState.value = UiState.Conflict },
                    serverAction = { _postReturnUiState.value = UiState.Server }
                )
            }
    }

    fun postExtension(id: Int, response: String) = viewModelScope.launch {
        postExtensionUseCase(id, response)
            .onSuccess {
                _postExtensionUiState.value = UiState.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _postExtensionUiState.value = UiState.BadRequest },
                    forbiddenAction = { _postExtensionUiState.value = UiState.Forbidden },
                    notFoundAction = { _postExtensionUiState.value = UiState.NotFound },
                    serverAction = { _postExtensionUiState.value = UiState.Server }
                )
            }
    }

    fun postRentalCancel(id: Int) = viewModelScope.launch {
        postRentalCancelUseCase(id)
            .onSuccess {
                _postRentalCancelUiState.value = UiState.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _postRentalCancelUiState.value = UiState.BadRequest },
                    forbiddenAction = { _postRentalCancelUiState.value = UiState.Forbidden },
                    notFoundAction = { _postRentalCancelUiState.value = UiState.NotFound },
                    serverAction = { _postRentalCancelUiState.value = UiState.Server }
                )
            }
    }

    fun acceptRequest(id: Int) = viewModelScope.launch {
        acceptRequestUseCase(id)
            .onSuccess {
                _acceptRequestUiState.value = UiState.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _acceptRequestUiState.value = UiState.BadRequest },
                    forbiddenAction = { _acceptRequestUiState.value = UiState.Forbidden },
                    notFoundAction = { _acceptRequestUiState.value = UiState.NotFound },
                    serverAction = { _acceptRequestUiState.value = UiState.Server }
                )
            }
    }

    fun rejectRequest(id: Int) = viewModelScope.launch {
        rejectRequestUseCase(id)
            .onSuccess {
                _rejectRequestUiState.value = UiState.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    badRequestAction = { _rejectRequestUiState.value = UiState.BadRequest },
                    forbiddenAction = { _rejectRequestUiState.value = UiState.Forbidden },
                    notFoundAction = { _rejectRequestUiState.value = UiState.NotFound },
                    serverAction = { _rejectRequestUiState.value = UiState.Server }
                )
            }
    }
}