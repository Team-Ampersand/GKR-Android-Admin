package com.mpersand.presentation.view.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.search.SearchScreen

const val searchRoute = "search_route"

fun NavController.navigateToSearch() {
    this.navigate(searchRoute)
}

fun NavGraphBuilder.searchScreen(
    navigateToMain: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToDetail: (productNumber: String) -> Unit
) {
    composable(searchRoute) {
        SearchScreen(
            navigateToSignIn = navigateToSignIn,
            navigateToMain = navigateToMain,
            navigateToDetail = navigateToDetail
        )
    }
}
