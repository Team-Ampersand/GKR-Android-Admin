package com.mpersand.presentation.view.main.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.mpersand.presentation.R

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    content: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) Color.Transparent else Color(0xFFCECECE)
        ),
        backgroundColor = if (selected) Color(0xFF865DFF) else Color.White
    ) {
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = content,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.inter_light)),
                color = if (selected) Color.White else Color(0xFF865DFF)
            )
        )
    }
}