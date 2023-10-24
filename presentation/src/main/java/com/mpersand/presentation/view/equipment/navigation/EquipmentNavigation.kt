package com.mpersand.presentation.view.equipment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.equipment.EquipmentScreen

const val equipmentRoute = "equipment_route"

fun NavController.navigateToEquipment(productNumber: String) {
    this.navigate("$equipmentRoute/$productNumber")
}

fun NavGraphBuilder.equipmentScreen(
    navigateToSignIn: () -> Unit,
    navigateToRepair: (productNumber: String) -> Unit
) {
    composable("$equipmentRoute/{productNumber}") { backStackEntry ->
        EquipmentScreen(
            productNumber = backStackEntry.arguments?.getString("productNumber"),
            navigateToSignIn = navigateToSignIn,
            navigateToRepair = navigateToRepair
        )
    }
}
