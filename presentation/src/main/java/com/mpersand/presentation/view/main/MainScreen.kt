package com.mpersand.presentation.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.presentation.view.main.component.AppBar
import com.mpersand.presentation.view.main.component.FilterItem
import com.mpersand.presentation.view.main.component.StudentItem
import com.mpersand.presentation.viewmodel.main.MainViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getRentedEquipments()
    }

    var selectedValue by remember { mutableStateOf(0) }

    val filter = listOf("전체", "모니터", "노트북", "키보드", "라즈베리파이")
    val uiState by viewModel.uiState.observeAsState()

    when (val state = uiState) {
        is UiState.Success -> {
            val rentedEquipments = state.data!!
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFFFAFAFA))
            ) {
                AppBar()
                LazyRow {
                    items(5) {
                        Spacer(modifier = modifier.width(10.dp))
                        FilterItem(
                            selected = it == selectedValue,
                            content = filter[it],
                            onClick = {
                                viewModel.getEquipmentsByFilter(filter[it])
                                selectedValue = it
                            }
                        )
                    }
                }
                Spacer(modifier = modifier.height(8.dp))
                LazyColumn(
                    modifier = modifier
                        .padding(horizontal = 13.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(top = 12.dp)
                ) {
                    items(rentedEquipments) {
                        StudentItem(
                            name = it.name,
                            status = it.rentStatus,
                            description = it.description,
                            image = it.image,
                            productNumber = it.productNumber
                        )
                    }
                }
            }
        }
        UiState.BadRequest -> {}
        UiState.Forbidden -> {}
        UiState.Loading -> {}
        UiState.NotFound -> {}
        UiState.Unauthorized -> {}
        else -> {}
    }
}