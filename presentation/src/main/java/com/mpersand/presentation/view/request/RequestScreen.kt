package com.mpersand.presentation.view.request

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.request.component.RequestItem

@Composable
fun RequestScreen(navigateToMain: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        GKRToolbar(title = "요청") { navigateToMain() }

        RequestList()
    }
}

@Composable
fun RequestList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 13.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val list = listOf(1,2,3,4)

        items(list) {
            RequestItem(onCardClick = { /*TODO*/ })
        }
    }
}