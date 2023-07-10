package com.mpersand.presentation.view.repair

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.domain.model.repair.request.RepairRequestModel
import com.mpersand.presentation.R
import com.mpersand.presentation.view.component.ModifyTextField
import com.mpersand.presentation.viewmodel.repair.RepairViewModel
import com.mpersand.presentation.viewmodel.util.UiState
import java.util.Calendar

@Composable
fun RepairScreen(
    modifier: Modifier = Modifier,
    productNumber: String?,
    viewModel: RepairViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
) {
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.observeAsState()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]
    val datePicker = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val monthValue = (selectedMonth + 1).let { if (it < 10) "0$it" else it }
            date = "${selectedYear}년 ${monthValue}월 ${selectedDay}일"
        }, year, month, day
    )

    when (uiState) {
        is UiState.Success -> navigateToMain()
        UiState.BadRequest -> {}
        UiState.Loading -> {}
        else -> {}
    }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(235.dp),
            contentDescription = "equipment",
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_logo),
        )
        Column(modifier = modifier.padding(horizontal = 26.dp)) {
            Spacer(modifier = modifier.height(17.dp))
            RepairItem(
                modifier = modifier.fillMaxWidth(),
                title = "수리 내용"
            ) {
                description = it
            }
            Spacer(modifier = modifier.height(15.dp))
            Text(
                text = "수리 일자",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD3D3D3),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        color = Color(0xFFF7F7F9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp
                    )
                    .clickable { datePicker.show() },
                text = date
            )
            Spacer(modifier = modifier.height(15.dp))
            RepairItem(
                modifier = modifier.fillMaxWidth(),
                title = "수리 비용",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            ) {
                cost = it
            }
            Spacer(modifier = modifier.height(15.dp))
            RepairItem(
                modifier = modifier.fillMaxWidth(),
                title = "수리 사유",
                minLines = 5,
                maxLines = 5
            ) {
                reason = it
            }
            Spacer(modifier = modifier.height(15.dp))
            RepairItem(
                modifier = modifier.fillMaxWidth(),
                title = "비고",
                minLines = 10,
                maxLines = 10
            ) {
                note = it
            }
            Spacer(modifier = modifier.height(90.dp))
            Button(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
                onClick = {
                    viewModel.addRepairHistory(
                        RepairRequestModel(
                            productNumber = checkNotNull(productNumber),
                            reason = reason,
                            description = description,
                            repairDate = date
                                .replace("\\s*([년월])\\s*".toRegex(), "-")
                                .replace("일", ""),
                            cost = cost.toInt(),
                            comment = note
                        )
                    )
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

@Composable
fun RepairItem(
    modifier: Modifier = Modifier,
    title: String,
    minLines: Int = 1,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChanged: (String) -> Unit
) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.fraunces_black)),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = modifier.height(6.dp))
    ModifyTextField(
        modifier = modifier,
        minLines = minLines,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        onValueChanged = { onValueChanged(it) }
    )
}