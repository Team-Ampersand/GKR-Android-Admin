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
import androidx.compose.foundation.lazy.itemsIndexed
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
    navigateToRentDetail: (String) -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToRequest: () -> Unit,
    navigateToSearch: () -> Unit,
) {
    var selectedValue by remember { mutableStateOf(0) }
    var filterSelectedQuery by remember { mutableStateOf("RENTING") }

    var showDialog by remember { mutableStateOf(false) }
    var openDrawer by remember { mutableStateOf(false) }

    val filter = listOf("대여중", "대여하지 않음", "대여 대기 중", "수리 중")
    val filterResultState by viewModel.uiState.observeAsState()
    val logoutState by viewModel.logoutState.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val getUserInfoUiState by viewModel.getUserInfoUiState.observeAsState()
    val waitingUiState by viewModel.waitingUiState.observeAsState()

    LaunchedEffect(filterSelectedQuery) {
        if (filterSelectedQuery == "WAITING") viewModel.getWaitingList()
        else viewModel.getEquipmentsByState(filterSelectedQuery)
    }

    when (logoutState) {
        is UiState.Success, UiState.NoContent -> navigateToSignIn()
        UiState.Loading -> {}
        else -> {
            LaunchedEffect(logoutState) {
                scaffoldState.snackbarHostState.showSnackbar("알 수 없는 오류로 로그아웃 하지 못했습니다.")
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
        viewModel.getEquipmentsByState("RENTING")
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

    when (val state = getUserInfoUiState) {
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
                        itemsIndexed(filter) { index, item ->
                            FilterItem(
                                selected = index == selectedValue,
                                content = item,
                                onClick = {
                                    selectedValue = index
                                    filterSelectedQuery = filterItemStringToQueryString(item)
                                }
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(8.dp))

                    when (val filterResult = filterResultState) {
                        is UiState.Success -> {
                            if (filterSelectedQuery == "WAITING") {
                                when (val waitingState = waitingUiState) {
                                    is UiState.Success -> {
                                        val response = waitingState.data!!.applicationList
                                            .filter { it.orderType == "RENTAL" }
                                            .map {
                                                EquipmentResponseModel(
                                                    productNumber = it.id.toString(),
                                                    name = it.name,
                                                    image = it.imageUrl,
                                                    description = it.description,
                                                    equipmentStatus = "WAITING",
                                                    equipmentType = it.orderType
                                                )
                                            }
                                        EquipmentListView(
                                            equipments = response,
                                            navigateToDetail = navigateToDetail,
                                            navigateToRentDetail = navigateToRentDetail
                                        )
                                    }

                                    else -> {}
                                }
                            } else {
                                EquipmentListView(
                                    equipments = filterResult.data!!.equipmentList,
                                    navigateToDetail = navigateToDetail,
                                    navigateToRentDetail = navigateToRentDetail
                                )
                            }
                        }

                        UiState.BadRequest -> {}
                        UiState.Forbidden -> {}
                        UiState.Loading -> {}
                        UiState.NotFound -> {
                            EquipmentListView(
                                equipments = emptyList(),
                                navigateToDetail = navigateToDetail,
                                navigateToRentDetail = navigateToRentDetail
                            )
                        }

                        UiState.Unauthorized -> {}
                        else -> {}
                    }
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
    navigateToDetail: (String) -> Unit,
    navigateToRentDetail: (String) -> Unit,
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
                modifier = Modifier.gkrClickable {
                    if (it.equipmentStatus == "WAITING") navigateToRentDetail(it.productNumber)
                    else navigateToDetail(it.productNumber)
                },
                name = it.name,
                type = it.equipmentType,
                status = it.equipmentStatus,
                description = it.description,
                image = it.image
            )
        }
    }
}

private fun filterItemStringToQueryString(data: String): String {
    return when (data) {
        "대여중" -> "RENTING"
        "대여하지 않음" -> "NOT_RENT"
        "대여 대기 중" -> "WAITING"
        "수리 중" -> "REPAIRING"
        else -> ""
    }
}
