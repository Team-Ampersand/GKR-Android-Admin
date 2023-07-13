package com.mpersand.presentation.view.search.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.presentation.R

@Composable
fun SearchHistoryItem(
    title: String,
    loadTextLogic: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_history_icon),
                contentDescription = "search history"
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = TextStyle(
                fontFamily = FontFamily(Font(resId = R.font.fraunces_black, weight = FontWeight.SemiBold)),
                fontSize = 10.sp,
                color = Color(0xFFC3C3C3)
            )
        )

        IconButton(onClick = loadTextLogic) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_diagonal_arrow),
                contentDescription = "apply arrow"
            )
        }
    }
}