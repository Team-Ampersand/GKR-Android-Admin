package com.mpersand.presentation.view.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.presentation.R
import com.mpersand.presentation.view.modifier.gkrClickable

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "GKR",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            fontWeight = FontWeight(900),
            color = Color(0xFF865DFF),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.weight(1f))
        Image(
            modifier = modifier.gkrClickable { onMenuClick() },
            painter = painterResource(id = R.drawable.ic_hamburger),
            contentDescription = "menu"
        )
        Spacer(modifier = modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search"
        )
        Spacer(modifier = modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "alarm"
        )
    }
}