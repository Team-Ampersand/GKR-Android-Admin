package com.mpersand.presentation.view.main.component

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.R

@Composable
fun EquipmentItem(
    modifier: Modifier = Modifier,
    name: String,
    status: String,
    description: String,
    image: String,
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
            painter = rememberAsyncImagePainter(image),
            contentDescription = "profile"
        )
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF656565)
                )
                Spacer(modifier = modifier.width(3.dp))
                Text(
                    text = name,
                    fontSize = 8.sp,
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF656565)
                )
            }
            Text(
                text = status,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFC2C2C2)
            )
            Text(
                text = description,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFC2C2C2)
            )
        }
    }
}
