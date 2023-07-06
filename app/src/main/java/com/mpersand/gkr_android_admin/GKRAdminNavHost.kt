package com.mpersand.gkr_android_admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mpersand.presentation.view.auth.navigation.signInScreen
import com.mpersand.presentation.view.detail.navigation.detailScreen
import com.mpersand.presentation.view.detail.navigation.navigateToDetail
import com.mpersand.presentation.view.main.navigation.mainScreen
import com.mpersand.presentation.view.main.navigation.navigateToMain
import com.mpersand.presentation.view.violation.navigation.navigationToViolation
import com.mpersand.presentation.view.violation.navigation.violationScreen

@Composable
fun GKRAdminNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        signInScreen(navigateToMain = {
            navController.navigateToMain()
        })

        mainScreen(
          navigateToViolation = {
            navController.navigationToViolation()
          },
          navigateToDetail = {
            navController.navigateToDetail(it)
          }
        )
        
        violationScreen(navigateToMain = {
            navController.navigateToMain()
        })
        
        detailScreen()
    }
}