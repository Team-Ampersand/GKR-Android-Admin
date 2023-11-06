package com.mpersand.presentation.view.rentDetail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.R
import com.mpersand.presentation.viewmodel.order.OrderViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun RentDetailScreen(
    productNumber: String?,
    orderViewModel: OrderViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
) {
    val context = LocalContext.current
    val rentalInfoApiResult by orderViewModel.getRentalRequestState.observeAsState()

    LaunchedEffect(Unit) {
        orderViewModel.getRentalRequestInfo(id = productNumber!!)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
    ) {
        when (val res = rentalInfoApiResult) {
            is UiState.Success -> {
                val rentalInfo = res.data

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(235.dp),
                    painter = rememberAsyncImagePainter(rentalInfo?.imageUrl),
                    contentDescription = "equipment",
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 26.dp, end = 26.dp, top = 17.dp)
                ) {
                    Text(
                        text = rentalInfo!!.name,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "${rentalInfo.grade}학년 ${rentalInfo.classNum}반 ${rentalInfo.stuNum}번 ${rentalInfo.userName}",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Black,
                        color = Color(0x99999999)
                    )

                    Text(
                        modifier = Modifier.padding(top = 30.dp),
                        text = "대여 이유",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = rentalInfo.reason,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC2C2C2)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color(0xFF865DFF),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        ),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            orderViewModel.rejectRequest(id = productNumber!!.toInt())
                            Toast.makeText(context, "요청 거절을 완료하였습니다.", Toast.LENGTH_SHORT).show()
                            navigateToMain()
                        }
                    ) { Text(text = "거절하기") }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF865DFF),
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            orderViewModel.acceptRequest(id = productNumber!!.toInt())
                            Toast.makeText(context, "요청 수락을 완료하였습니다.", Toast.LENGTH_SHORT).show()
                            navigateToMain()
                        }
                    ) { Text(text = "수락하기") }
                }
            }
            UiState.Unauthorized -> {}
            UiState.Forbidden -> {}
            UiState.NotFound -> {}
            UiState.Server -> {}
            else -> {}
        }
    }
}

@Preview
@Composable
fun RentDetailScreenPreview() {
    RentDetailScreen(productNumber = "", navigateToMain = {})
}