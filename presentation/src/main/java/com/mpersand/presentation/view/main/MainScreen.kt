package com.mpersand.presentation.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
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
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.presentation.view.main.component.AppBar
import com.mpersand.presentation.view.main.component.EquipmentItem
import com.mpersand.presentation.view.main.component.FilterItem
import com.mpersand.presentation.view.main.component.LogoutDialog
import com.mpersand.presentation.view.main.component.NavigationDrawer
import com.mpersand.presentation.view.modifier.gkrClickable
import com.mpersand.presentation.viewmodel.main.MainViewModel
import com.mpersand.presentation.viewmodel.util.UiState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navigateToViolation: () -> Unit,
    navigateToDetail: (String) -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToRequest: () -> Unit,
    navigateToSearch: () -> Unit
) {
    var selectedValue by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var openDrawer by remember { mutableStateOf(false) }

    val filter = listOf("전체", "맥북", "갤럭시 북", "터치모니터", "대여함", "대여하지 않음")
    val uiState by viewModel.uiState.observeAsState()
    val logoutState by viewModel.logoutState.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val getUserInfoUiState by viewModel.getUserInfoUiState.observeAsState()

    when (logoutState) {
        is UiState.Success,  UiState.NoContent -> navigateToSignIn()
        UiState.Loading -> {}
        else -> {
            LaunchedEffect(logoutState) {
                scaffoldState.snackbarHostState.showSnackbar("알 수 없는 오류로 로그아웃 하지 못했습니다.")
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllEquipments()
        viewModel.getUserInfo()
    }

    if (showDialog) {
        LogoutDialog(onDismissRequest = { showDialog = false }) {
            viewModel.logout()
        }
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    if (openDrawer) {
        LaunchedEffect(drawerState) {
            drawerState.open()
        }
    }

    when(val state = getUserInfoUiState) {
        is UiState.Success -> {
            NavigationDrawer(
                drawerState = drawerState,
                userInfo = state.data!!,
                logoutAction = {
                    coroutineScope.launch { drawerState.close() }
                    showDialog = true
                },
                navigateToViolation = navigateToViolation,
                navigateToRequest = navigateToRequest
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFFAFAFA))
                        .systemBarsPadding()
                ) {
                    AppBar(
                        onMenuClick = { openDrawer = !openDrawer },
                        onSearchClick = navigateToSearch
                    )

                    LazyRow(
                        modifier = modifier.padding(horizontal = 13.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(filter.size) {
                            FilterItem(
                                selected = it == selectedValue,
                                content = filter[it],
                                onClick = {
                                    when (it) {
                                        filter.lastIndex -1 -> viewModel.getEquipmentsByState("RENTING")
                                        filter.lastIndex -> viewModel.getEquipmentsByState("NOT_RENT")
                                        else -> if (it == 0) viewModel.getAllEquipments() else viewModel.getEquipmentsByType(filter[it])
                                    }
                                    selectedValue = it
                                }
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(8.dp))

                    var equipmentResult: List<EquipmentResponseModel> by remember { mutableStateOf(emptyList()) }
                    when (val state = uiState) {
                        is UiState.Success -> equipmentResult = state.data!!.equipmentList
                        UiState.BadRequest -> {}
                        UiState.Forbidden -> {}
                        UiState.Loading -> {}
                        UiState.NotFound -> equipmentResult = emptyList()
                        UiState.Unauthorized -> {}
                        else -> {}
                    }

                    EquipmentListView(
                        equipments = equipmentResult,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
        UiState.Unauthorized -> navigateToSignIn()
        else -> {}
    }
}

@Composable
fun EquipmentListView(
    equipments: List<EquipmentResponseModel>,
    navigateToDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 13.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 12.dp)
    ) {
        items(equipments) {
            EquipmentItem(
                modifier = Modifier.gkrClickable { navigateToDetail(it.productNumber) },
                name = it.name,
                status = it.equipmentStatus,
                description = it.description,
                image = it.image
            )
        }
    }
}
