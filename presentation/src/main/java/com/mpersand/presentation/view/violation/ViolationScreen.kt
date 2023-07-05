package com.mpersand.presentation.view.violation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.violation.component.ViolationItem
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.violation.ViolationViewModel

@Composable
fun ViolationScreen(
    modifier: Modifier = Modifier,
    viewModel: ViolationViewModel = hiltViewModel()
) {
    val getNoReturnStudentsUiState by viewModel.getNoReturnStudentsUiState.observeAsState()

    when(val state = getNoReturnStudentsUiState) {
        is UiState.Success -> {
            val violationItemList = state.data!!

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                GKRToolbar(title = "제제 하기") {
                }
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
                    items(violationItemList) {
                        ViolationItem(
                            grade = it.grade.toString(),
                            classNum =it.classNum.toString(),
                            number =  it.number.toString(),
                            productNumber = it.name,
                            image = it.profileUrl ?: ""
                        )
                    }
                }
            }
        }
        else -> {}
    }
}

@Preview
@Composable
fun preview() {
    ViolationScreen()
}