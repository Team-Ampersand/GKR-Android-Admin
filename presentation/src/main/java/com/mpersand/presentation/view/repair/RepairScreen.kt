package com.mpersand.presentation.view.repair

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.viewmodel.repair.RepairViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun RepairScreen(
    modifier: Modifier = Modifier,
    productNumber: String?,
    viewModel: RepairViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getRepairDetail(checkNotNull(productNumber))
    }

    var repaired by remember { mutableStateOf(false) }

    val repairState by viewModel.repairState.observeAsState()
    val detailState by viewModel.detailState.observeAsState()

    when (repairState) {
        is UiState.Success -> navigateToMain()
        UiState.BadRequest -> {}
        UiState.Loading -> {}
        else -> {}
    }

    when (val state = detailState) {
        is UiState.Success -> {
            repaired = state.data!!.equipmentStatus == "REPAIRING"

            Scaffold { paddingValues ->
                Column(
                    modifier = modifier
                        .verticalScroll(rememberScrollState())
                        .systemBarsPadding()
                        .padding(paddingValues)
                ) {
                    Image(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(235.dp),
                        contentDescription = "equipment",
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(state.data?.image)
                    )
                    Column(modifier = modifier.padding(horizontal = 26.dp)) {
                        Spacer(modifier = modifier.height(17.dp))
                        Checkbox(
                            checked = repaired,
                            onCheckedChange = { repaired = !repaired }
                        )
                        Spacer(modifier = modifier.height(90.dp))
                        Button(
                            modifier = modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
                            onClick = {
                                if (repaired) {
                                    viewModel.changeEquipmentToRepairing(checkNotNull(productNumber))
                                } else {
                                    viewModel.completeEquipmentRepair(checkNotNull(productNumber))
                                }
                            }
                        ) {
                            Text(
                                text = "수정하기",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = modifier.height(60.dp))
                    }
                }
            }
        }

        UiState.BadRequest -> {}
        UiState.Loading -> {}
        UiState.NotFound -> {}
        UiState.Unauthorized -> {}
        else -> {}
    }
}
