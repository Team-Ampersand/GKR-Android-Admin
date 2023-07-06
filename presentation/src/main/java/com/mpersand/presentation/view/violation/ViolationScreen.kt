package com.mpersand.presentation.view.violation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.violation.component.ViolationDialog
import com.mpersand.presentation.view.violation.component.ViolationItem
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.violation.ViolationViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViolationScreen(
    modifier: Modifier = Modifier,
    viewModel: ViolationViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val getNoReturnStudentsUiState by viewModel.getNoReturnStudentsUiState.observeAsState()
    val postViolationUserUiState by viewModel.postViolationUserUiState.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.getNoReturnStudents()
    }

    Scaffold(scaffoldState = scaffoldState) {
        when (val state = getNoReturnStudentsUiState) {
            is UiState.Success -> {
                val violationItemList = state.data!!

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    GKRToolbar(title = "제제 하기") {
                        navigateToMain()
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
                            if (showDialog) {
                                ViolationDialog(
                                    userId = it.id,
                                    onDismissRequest = { showDialog = false }
                                ) {
                                    when (postViolationUserUiState) {
                                        is UiState.Success -> {
                                            coroutineScope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar("제제가 완료되었습니다.")
                                            }
                                        }
                                        else -> {
                                            coroutineScope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar("예상치 못 한 오류가 발생 했습니다.\n 개발자에게 문의 해주세요.")
                                            }
                                        }
                                    }
                                }
                            }

                            ViolationItem(
                                modifier = modifier.clickable { showDialog = true },
                                grade = it.grade.toString(),
                                classNum = it.classNum.toString(),
                                number = it.number.toString(),
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
}
