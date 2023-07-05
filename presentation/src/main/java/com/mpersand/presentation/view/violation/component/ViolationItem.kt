package com.mpersand.presentation.view.violation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.R

@Composable
fun ViolationItem(
    modifier: Modifier = Modifier,
    grade: String,
    classNum: String,
    number: String,
    image: String,
    productNumber: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFD3D3D3),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 18.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape),
//            painter = rememberAsyncImagePainter(image),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "profile"
        )
        Spacer(modifier = modifier.width(15.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = productNumber,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF656565)
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    text = grade +"학년 " + classNum + "반 " + number + "번",
                    fontSize = 8.sp,
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF656565)
                )
            }
        }
    }
}