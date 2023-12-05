package com.mpersand.presentation.view.request.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.presentation.view.request.RequestDetailScreen
import com.mpersand.presentation.view.request.data.RequestInfoNavType

const val requestDetailRoute = "request_detail_route"

fun NavController.navigateToRequestDetail(applicationId: String) {
    this.navigate("$requestDetailRoute/$applicationId")
}

fun NavGraphBuilder.requestDetailScreen(navigateToSignIn: () -> Unit) {
    composable(
        route = "$requestDetailRoute/{applicationId}",
        arguments = listOf(
            navArgument("applicationId") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        RequestDetailScreen(
            applicationId = backStackEntry.arguments?.getString("applicationId"),
            navigateToSignIn = navigateToSignIn
        )
    }
}
