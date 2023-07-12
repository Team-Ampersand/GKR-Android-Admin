package com.mpersand.presentation.viewmodel.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.order.response.WaitListResponseModel
import com.mpersand.domain.usecase.order.GetWaitListUseCase
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val getWaitListUseCase: GetWaitListUseCase
): ViewModel() {

    private val _waitList: MutableLiveData<UiState<List<WaitListResponseModel>>> = MutableLiveData()
    val waitList: LiveData<UiState<List<WaitListResponseModel>>> = _waitList

    fun getWaitList() {
        viewModelScope.launch {
            getWaitListUseCase().onSuccess {
                _waitList.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { _waitList.value = UiState.BadRequest },
                    unauthorizedAction = { _waitList.value = UiState.Unauthorized },
                    notFoundAction = { _waitList.value = UiState.NotFound }
                )
            }
        }
    }
}