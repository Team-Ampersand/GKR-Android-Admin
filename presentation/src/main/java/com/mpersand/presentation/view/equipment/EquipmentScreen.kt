package com.mpersand.presentation.view.equipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mpersand.domain.model.equipment.request.EquipmentRequestModel
import com.mpersand.presentation.R
import com.mpersand.presentation.view.component.ModifyTextField
import com.mpersand.presentation.viewmodel.equipment.EquipmentViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun EquipmentScreen(
    modifier: Modifier = Modifier,
    productNumber: String?,
    viewModel: EquipmentViewModel = hiltViewModel(),
    navigateToRepair: (productNumber: String) -> Unit
) {
    val equipmentState by viewModel.equipmentState.observeAsState()
    val modifyState by viewModel.modifyState.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getEquipmentDetail(checkNotNull(productNumber))
    }

    when (modifyState) {
        is UiState.Success, UiState.NoContent -> navigateToRepair(checkNotNull(productNumber))
        UiState.BadRequest -> {}
        UiState.Conflict -> {}
        UiState.Forbidden -> {}
        UiState.Loading -> {}
        UiState.NotFound -> {}
        UiState.Unauthorized -> {}
        else -> {}
    }

    when (val state = equipmentState) {
        is UiState.Success -> {
            val equipment = state.data!!

            var equipmentName by remember { mutableStateOf(equipment.name) }
            var description by remember { mutableStateOf(equipment.description) }

            Column {
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(235.dp),
                    contentDescription = "equipment",
                    contentScale = ContentScale.Crop,
                    painter = rememberAsyncImagePainter(equipment.image)
                )
                Spacer(modifier = modifier.height(17.dp))
                Column(modifier = modifier.padding(horizontal = 26.dp)) {
                    Text(
                        text = "물품이름",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    ModifyTextField(
                        value = equipment.name,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        equipmentName = it
                    }
                    Spacer(modifier = modifier.height(15.dp))
                    Text(
                        text = "설명",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    ModifyTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = equipment.description,
                        minLines = 10,
                        maxLines = 10
                    ) {
                        description = it
                    }
                    Spacer(modifier = modifier.weight(1f))
                    Button(
                        modifier = modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            viewModel.modifyEquipment(
                                productNumber = checkNotNull(productNumber),
                                body = EquipmentRequestModel(
                                    name = equipmentName,
                                    image = equipment.image,
                                    description = description
                                )
                            )
                        }
                    ) {
                        Text(
                            text = "다음",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = modifier.height(46.dp))
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