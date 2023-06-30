package com.mpersand.gkr_android_admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mpersand.presentation.view.auth.navigation.signInScreen

@Composable
fun GKRAdminNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        signInScreen()
    }
}