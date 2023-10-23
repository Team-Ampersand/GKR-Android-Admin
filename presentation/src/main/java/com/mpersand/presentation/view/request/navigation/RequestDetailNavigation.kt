package com.mpersand.presentation.view.request.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.domain.model.order.response.WaitListResponseModel
import com.mpersand.presentation.view.request.RequestDetailScreen
import com.mpersand.presentation.view.request.data.RequestInfoNavType

const val requestDetailRoute = "request_detail_route"

fun NavController.navigateToRequestDetail(json: String) {
    this.navigate("$requestDetailRoute/$json")
}

fun NavGraphBuilder.requestDetailScreen() {
    composable(
        route = "$requestDetailRoute/{data}",
        arguments = listOf(
            navArgument("data") {
                type = RequestInfoNavType()
            }
        )
    ) {
        val data = it.arguments?.getParcelable<OrderDetailListResponseModel>("data")
        RequestDetailScreen(data)
    }
}