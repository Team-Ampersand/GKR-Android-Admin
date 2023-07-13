package com.mpersand.presentation.view.request.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.domain.model.order.response.WaitListResponseModel
import com.mpersand.presentation.view.request.RequestScreen

const val requestRoute = "request_route"

fun NavController.navigateToRequest() {
    this.navigate(requestRoute)
}

fun NavGraphBuilder.requestScreen(
    navigateToMain: () -> Unit,
    navigateToRequestDetail: (WaitListResponseModel) -> Unit
) {
    composable(requestRoute) {
        RequestScreen(
            navigateToMain = navigateToMain,
            navigateToRequestDetail = navigateToRequestDetail
        )
    }
}