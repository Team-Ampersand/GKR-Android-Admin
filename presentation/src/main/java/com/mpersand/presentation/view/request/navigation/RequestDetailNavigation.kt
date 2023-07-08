package com.mpersand.presentation.view.request.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.request.RequestDetailScreen

const val requestDetailRoute = "request_detail_route"

fun NavController.navigateToRequestDetail() {
    this.navigate(requestDetailRoute)
}

fun NavGraphBuilder.requestDetailScreen() {
    composable(requestDetailRoute) {
        RequestDetailScreen()
    }
}