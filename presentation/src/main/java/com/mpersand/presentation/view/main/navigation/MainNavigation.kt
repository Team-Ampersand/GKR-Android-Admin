package com.mpersand.presentation.view.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.main.MainScreen

const val mainRoute = "main_route"

fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainScreen(
    navigateToDetail: (String) -> Unit,
    navigateToViolation: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToRequest: () -> Unit,
    navigateToSearch: () -> Unit
) {
    composable(mainRoute) {
        MainScreen(
            navigateToDetail = navigateToDetail,
            navigateToViolation = navigateToViolation,
            navigateToSignIn = navigateToSignIn,
            navigateToRequest = navigateToRequest,
            navigateToSearch = navigateToSearch
        )
    }
}