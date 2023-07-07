package com.mpersand.presentation.view.repair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.repair.RepairScreen

const val repairRoute = "repair_route"

fun NavController.navigateToRepair() {
    this.navigate(repairRoute)
}

fun NavGraphBuilder.repairScreen() {
    composable(repairRoute) {
        RepairScreen()
    }
}