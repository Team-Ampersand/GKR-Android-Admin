package com.mpersand.presentation.view.request

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.presentation.R
import com.mpersand.presentation.viewmodel.order.OrderViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun RequestDetailScreen(
    data: OrderDetailListResponseModel?,
    requestViewModel: OrderViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.temp_equipment_image),
            contentDescription = "request detail equipment image",
            contentScale = ContentScale.FillWidth
        )

        StudentInfo(data = data)

        ReasonView(data = data)

        Spacer(modifier = Modifier.weight(1f))

        val result by requestViewModel.postRentalUiState.observeAsState()
        val context = LocalContext.current

        AcceptOrNotView(
            acceptButtonClick = { requestViewModel.acceptRequest(data!!.id) },
            rejectButtonClick = { requestViewModel.rejectRequest(data!!.id) }
        )

        when (result) {
            UiState.Loading -> {}
            is UiState.Success -> Toast.makeText(context, "요청을 수락하였습니다.", Toast.LENGTH_SHORT).show()
            UiState.BadRequest -> Toast.makeText(context, "토큰 값이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            UiState.Unauthorized -> navigateToSignIn()
            UiState.NotFound -> Toast.makeText(context, "UUID를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(context, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun StudentInfo(data: OrderDetailListResponseModel?) {
    Text(
        modifier = Modifier.padding(start = 13.dp),
        text = data?.id.toString(),
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.inter_black)),
            fontSize = 20.sp
        ),
        softWrap = true
    )

    Text(
        modifier = Modifier.padding(start = 13.dp),
        text = data?.rentalStartDate.toString(),
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            fontSize = 15.sp,
            color = Color(0xFFC2C2C2)
        )
    )
}

@Composable
fun ReasonView(data: OrderDetailListResponseModel?) {
    Text(
        modifier = Modifier.padding(start = 13.dp, top = 20.dp),
        text = "대여 이유",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.inter_black)),
            fontSize = 16.sp,
            color = Color(0xFF1C1C1E)
        )
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp),
        text = data?.reason ?: "null reason",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.inter_black)),
            fontSize = 16.sp,
            color = Color(0xFFC2C2C2)
        ),
        softWrap = true
    )
}

@Composable
fun AcceptOrNotView(
    acceptButtonClick: () -> Unit,
    rejectButtonClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp)
            .border(width = 1.dp, color = Color(0xFF865DFF), shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = rejectButtonClick
    ) {
        Text(
            text = "거절하기",
            style = TextStyle(
                fontFamily = FontFamily(Font(resId = R.font.inter_light, weight = FontWeight.Medium)),
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
        onClick = acceptButtonClick
    ) {
        Text(
            text = "수락하기",
            style = TextStyle(
                fontFamily = FontFamily(Font(resId = R.font.inter_light, weight = FontWeight.Medium)),
                fontSize = 16.sp,
                color = Color.White
            )
        )
    }
}
