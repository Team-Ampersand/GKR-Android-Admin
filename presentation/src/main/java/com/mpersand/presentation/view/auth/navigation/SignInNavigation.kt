package com.mpersand.presentation.view.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.auth.SignInScreen

const val signInRoute = "signin_route"

fun NavController.navigateToSignIn() {
    this.navigate(signInRoute)
}

fun NavGraphBuilder.signInScreen(navigateToMain: () -> Unit) {
    composable(signInRoute) {
        SignInScreen(navigateToMain = navigateToMain)
    }
}