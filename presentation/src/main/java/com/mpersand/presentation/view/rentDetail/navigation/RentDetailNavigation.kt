 package com.mpersand.presentation.view.rentDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.rentDetail.RentDetailScreen

const val rentDetailRoute = "rentDetail_route"

fun NavController.navigateToRentDetail(productNumber: String) {
    this.navigate("$rentDetailRoute/$productNumber")
}

fun NavGraphBuilder.rentDetailScreen() {
    composable("$rentDetailRoute/{productNumber}") { backStackEntry ->
        RentDetailScreen(
            productNumber = backStackEntry.arguments?.getString("productNumber")
        )
    }
}