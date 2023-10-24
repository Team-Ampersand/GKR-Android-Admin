package com.mpersand.presentation.view.violation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.modifier.gkrClickable
import com.mpersand.presentation.view.violation.component.ViolationDialog
import com.mpersand.presentation.view.violation.component.ViolationItem
import com.mpersand.presentation.viewmodel.util.UiState
import com.mpersand.presentation.viewmodel.violation.ViolationViewModel

@Composable
fun ViolationScreen(
    modifier: Modifier = Modifier,
    viewModel: ViolationViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val getNoReturnStudentsUiState by viewModel.getNoReturnStudentsUiState.observeAsState()
    val postViolationUserUiState by viewModel.postViolationUserUiState.observeAsState()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.getNoReturnStudents()
    }

    Scaffold(
        modifier = modifier.padding(bottom = 30.dp),
        scaffoldState = scaffoldState
    ) { paddingValues ->
        LaunchedEffect(postViolationUserUiState) {
            when (postViolationUserUiState) {
                UiState.Loading -> {}
                is UiState.Success -> scaffoldState.snackbarHostState.showSnackbar("제재가 완료되었습니다.")
                UiState.Unauthorized -> scaffoldState.snackbarHostState.showSnackbar("토큰이 존재하지 않습니다.\n다시 로그인 해주세요.")
                UiState.Forbidden -> scaffoldState.snackbarHostState.showSnackbar("권한이 존재하지 않습니다.\n개발자에게 문의 해주세요.")
                UiState.NotFound -> scaffoldState.snackbarHostState.showSnackbar("해당 유저를 찾기를 실패했습니다.")
                UiState.Conflict -> scaffoldState.snackbarHostState.showSnackbar("해당 유저가 이미 제재 중인 상태입니다.")
                else -> scaffoldState.snackbarHostState.showSnackbar("서버 에러가 발생 했습니다.\n개발자에게 문의 해주세요.")
            }
        }

        when (val state = getNoReturnStudentsUiState) {
            is UiState.Success -> {
                val violationItemList = state.data!!

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(paddingValues)
                        .systemBarsPadding()
                ) {
                    GKRToolbar(title = "제재 하기") {
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
                        items(violationItemList.applicationList) {
                            if (showDialog) {
                                ViolationDialog(
                                    email = it.id.toString(),
                                    onDismissRequest = { showDialog = false }
                                )
                            }

                            ViolationItem(
                                modifier = modifier.gkrClickable { showDialog = true },
                                grade = it.grade,
                                classNum = it.classNum,
                                number = it.stuNum,
                                productNumber = it.userName,
                                image = it.imageUrl
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }
}
