package com.mpersand.presentation.view.rentDetail

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.R

@Composable
fun RentDetailScreen(productNumber: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(235.dp),
            painter = rememberAsyncImagePainter(""),
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
                text = "name",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Black
            )

            Text(
                text = "학번",
                fontSize = 8.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Black,
                color = Color(0x99999999)
            )

            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = "연장 이유",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "대충 연장 사유",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC2C2C2)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth()
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
                onClick = {}
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
                onClick = {}
            ) { Text(text = "수락하기") }
        }
    }
}

@Preview
@Composable
fun RentDetailScreenPreview() {
    RentDetailScreen(productNumber = "")
}