package com.mpersand.presentation.view.request.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.presentation.view.request.RequestScreen

const val requestRoute = "request_route"

fun NavController.navigateToRequest() {
    this.navigate(requestRoute)
}

fun NavGraphBuilder.requestScreen(
    navigateToMain: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToRequestDetail: (Long) -> Unit
) {
    composable(requestRoute) {
        RequestScreen(
            navigateToMain = navigateToMain,
            navigateToSignIn = navigateToSignIn,
            navigateToRequestDetail = navigateToRequestDetail
        )
    }
}
