package com.mpersand.presentation.view.repair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.repair.RepairScreen

const val repairRoute = "repair_route"

fun NavController.navigateToRepair(productNumber: String) {
    this.navigate("$repairRoute/$productNumber")
}

fun NavGraphBuilder.repairScreen(
    navigateToSignIn: () -> Unit,
    navigateToMain: () -> Unit
) {
    composable("$repairRoute/{productNumber}") { backStackEntry ->
        RepairScreen(
            productNumber = backStackEntry.arguments?.getString("productNumber"),
            navigateToSignIn = navigateToSignIn,
            navigateToMain = navigateToMain
        )
    }
}
