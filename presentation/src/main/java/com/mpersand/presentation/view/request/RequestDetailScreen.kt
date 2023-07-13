package com.mpersand.presentation.view.request

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.domain.model.order.response.WaitListResponseModel
import com.mpersand.presentation.R

@Composable
fun RequestDetailScreen(data: WaitListResponseModel?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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

        AcceptOrNotView(
            acceptButtonClick = {},
            rejectButtonClick = {}
        )
    }
}

@Composable
fun StudentInfo(data: WaitListResponseModel?) {
    Text(
        modifier = Modifier.padding(start = 13.dp),
        text = data?.equipmentId ?: "null equipment",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.inter_black)),
            fontSize = 20.sp
        ),
        softWrap = true
    )

    Text(
        modifier = Modifier.padding(start = 13.dp),
        text = data?.rentalDate ?: "null date",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            fontSize = 15.sp,
            color = Color(0xFFC2C2C2)
        )
    )
}

@Composable
fun ReasonView(data: WaitListResponseModel?) {
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