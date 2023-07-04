package com.mpersand.presentation.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.presentation.R

@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(Color(0xFFFAFAFA))) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(235.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "equipment",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(17.dp))
        Column(modifier = modifier.padding(horizontal = 26.dp)) {
            Text(
                text = "Raspberry pi 4",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Black
            )
            Text(
                text = "# IOT",
                fontSize = 8.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Black,
                color = Color(0x99999999)
            )
            Spacer(modifier = modifier.height(30.dp))
            Text(
                text = "라즈베리파이 4는 IOT 통신을 하는데 필요한 기자재입니다.",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF865DFF),
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 16.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { }
            ) {
                Text(
                    text = "수정하기"
                )
            }
            Spacer(modifier = modifier.height(46.dp))
        }
    }
}