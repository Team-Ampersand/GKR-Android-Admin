package com.mpersand.gkr_android_admin

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.gson.Gson
import com.mpersand.presentation.view.auth.navigation.navigateToSignIn
import com.mpersand.presentation.view.auth.navigation.signInScreen
import com.mpersand.presentation.view.detail.navigation.detailScreen
import com.mpersand.presentation.view.detail.navigation.navigateToDetail
import com.mpersand.presentation.view.main.navigation.mainScreen
import com.mpersand.presentation.view.main.navigation.navigateToMain
import com.mpersand.presentation.view.request.navigation.navigateToRequest
import com.mpersand.presentation.view.request.navigation.navigateToRequestDetail
import com.mpersand.presentation.view.request.navigation.requestDetailScreen
import com.mpersand.presentation.view.request.navigation.requestScreen
import com.mpersand.presentation.view.equipment.navigation.equipmentScreen
import com.mpersand.presentation.view.equipment.navigation.navigateToEquipment
import com.mpersand.presentation.view.repair.navigation.navigateToRepair
import com.mpersand.presentation.view.repair.navigation.repairScreen
import com.mpersand.presentation.view.search.navigation.navigateToSearch
import com.mpersand.presentation.view.search.navigation.searchScreen
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
            navigateToViolation = { navController.navigationToViolation() },
            navigateToDetail = { navController.navigateToDetail(it) },
            navigateToSignIn = { navController.navigateToSignIn() },
            navigateToRequest = { navController.navigateToRequest() },
            navigateToSearch = { navController.navigateToSearch() }
        )
        violationScreen(navigateToMain = {
            navController.navigateToMain()
        })
        
        detailScreen(navigateToEquipment = {
            navController.navigateToEquipment(it)
        })
        equipmentScreen(navigateToRepair = {
            navController.navigateToRepair(it)
        })
        repairScreen(navigateToMain = {
            navController.navigateToMain()
        })
        
        requestScreen(
            navigateToMain = { navController.navigateToMain() },
            navigateToRequestDetail = { data ->
                val json = Uri.encode(Gson().toJson(data))
                navController.navigateToRequestDetail(json)
            }
        )

        requestDetailScreen()

        searchScreen(
            navigateToMain = { navController.navigateToMain() },
            navigateToDetail = { navController.navigateToDetail(it) }
        )
    }
}