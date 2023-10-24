package com.mpersand.presentation.view.request

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.request.component.RequestItem
import com.mpersand.presentation.viewmodel.order.OrderViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun RequestScreen(
    requestViewModel: OrderViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToRequestDetail: (OrderDetailListResponseModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .systemBarsPadding()
    ) {
        GKRToolbar(title = "요청") { navigateToMain() }

        requestViewModel.getWaitList()
        val waitList by requestViewModel.getWaitListUiState.observeAsState()

        RequestList(
            waitState = waitList,
            navigateToRequestDetail = navigateToRequestDetail,
            navigateToSignIn = navigateToSignIn
        )
    }
}

@Composable
fun RequestList(
    waitState: UiState<OrderApplicationListResponseModel>?,
    navigateToRequestDetail: (OrderDetailListResponseModel) -> Unit,
    navigateToSignIn: () -> Unit
) {
    when (waitState) {
        UiState.Loading -> {}
        is UiState.Success -> {
            RequestItemList(
                list = waitState.data!!,
                navigateToRequestDetail = navigateToRequestDetail
            )
        }
        UiState.BadRequest -> {}
        UiState.Unauthorized -> navigateToSignIn()
        UiState.NotFound -> {}
        else -> {}
    }
}

@Composable
fun RequestItemList(
    list: OrderApplicationListResponseModel,
    navigateToRequestDetail: (OrderDetailListResponseModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 13.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list.applicationList) {
            RequestItem(
                data = it,
                onCardClick = navigateToRequestDetail
            )
        }
    }
}
