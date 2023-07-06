package com.mpersand.presentation.view.violation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.violation.ViolationScreen

const val violationRoute = "violation_route"

fun NavController.navigationToViolation() {
    this.navigate(violationRoute)
}

fun NavGraphBuilder.violationScreen(navigateToMain: () -> Unit) {
    composable(violationRoute) {
        ViolationScreen(navigateToMain = navigateToMain)
    }
}