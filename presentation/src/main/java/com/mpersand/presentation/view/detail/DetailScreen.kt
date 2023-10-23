package com.mpersand.presentation.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.mpersand.presentation.R
import com.mpersand.presentation.viewmodel.detail.DetailViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    productNumber: String?,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateToEquipment: (productNumber: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getEquipmentInfo(checkNotNull(productNumber))
    }

    val uiState by viewModel.uiState.observeAsState()

    when (val state = uiState) {
        is UiState.Success -> {
            val equipment = state.data!!

            Column(
                modifier = modifier
                    .background(Color(0xFFFAFAFA))
                    .verticalScroll(rememberScrollState())
                    .systemBarsPadding()
            ) {
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(235.dp),
                    painter = rememberAsyncImagePainter(equipment.image),
                    contentDescription = "equipment",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = modifier.height(17.dp))
                Column(modifier = modifier.padding(horizontal = 26.dp)) {
                    Text(
                        text = equipment.name,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = when (equipment.name) {
                            "맥북" -> "#맥북 #노트북"
                            "갤럭시 북" -> "#갤럭시 북 #노트북"
                            "터치모니터" -> "#터치모니터 #모니터"
                            else -> "#?"
                        },
                        fontSize = 8.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Black,
                        color = Color(0x99999999)
                    )
                    Text(
                        text = when (equipment.equipmentStatus) {
                            "NOT_RENT" -> "대여전"
                            "WAITING" -> "대기중"
                            "RENTING" -> "대여중"
                            else -> "수리중"
                        },
                        fontSize = 8.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Black,
                        color = Color(0x99999999)
                    )
                    Spacer(modifier = modifier.height(30.dp))
                    Text(
                        text = "기자재 상세",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = equipment.description,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Spacer(modifier = modifier.height(60.dp))
                    Button(
                        modifier = modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF865DFF),
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { navigateToEquipment(checkNotNull(productNumber)) }
                    ) {
                        Text(
                            text = "수정하기"
                        )
                    }
                    Spacer(modifier = modifier.height(46.dp))
                }
            }
        }
        UiState.BadRequest -> {}
        UiState.Forbidden -> {}
        UiState.Loading -> {}
        UiState.NotFound -> {}
        UiState.Unauthorized -> {}
        UiState.Unknown -> {}
        else -> {}
    }
}

@Composable
fun RepairDetail(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.fraunces_black)),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = modifier.height(6.dp))
    Text(
        text = content,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )
}
