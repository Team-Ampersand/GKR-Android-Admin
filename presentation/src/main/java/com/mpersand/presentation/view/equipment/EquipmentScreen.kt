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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.mpersand.presentation.view.equipment.component.ModifyTextField

@Composable
fun EquipmentScreen(modifier: Modifier = Modifier) {
    var equipmentName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(235.dp),
            contentDescription = "equipment",
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_logo),
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
            ModifyTextField(modifier = modifier.fillMaxWidth()) {
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
                minLines = 15,
                maxLines = 15
            ) {
                description = it
            }
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
                contentPadding = PaddingValues(vertical = 16.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { }
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