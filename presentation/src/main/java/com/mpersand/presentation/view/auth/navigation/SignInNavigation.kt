package com.mpersand.presentation.view.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.auth.SignInScreen

const val signInRoute = "signin_route"

fun NavGraphBuilder.signInScreen(navigateToMain: () -> Unit) {
    composable(signInRoute) {
        SignInScreen(navigateToMain = navigateToMain)
    }
}