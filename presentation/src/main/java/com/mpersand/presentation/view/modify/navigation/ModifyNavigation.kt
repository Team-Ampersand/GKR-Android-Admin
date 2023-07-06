package com.mpersand.presentation.view.modify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.modify.ModifyScreen

const val modifyRoute = "modify_route"

fun NavController.navigateToModify() {
    this.navigate(modifyRoute)
}

fun NavGraphBuilder.modifyScreen() {
    composable(modifyRoute) {
        ModifyScreen()
    }
}