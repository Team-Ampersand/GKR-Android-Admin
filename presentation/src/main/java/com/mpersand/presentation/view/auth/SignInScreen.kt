package com.mpersand.presentation.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.msg.gauthsignin.component.GAuthButton
import com.msg.gauthsignin.component.utils.Types

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(250.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo"
            )
            Spacer(modifier = modifier.width(15.dp))
            Text(
                text = "GKR",
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontSize = 32.sp,
                fontWeight = FontWeight.Thin,
                color = Color(0xFFFFA559),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = modifier.height(15.dp))
        Text(
            text = "GSM의 기자재\n대여 서비스를 이용해보세요",
            textAlign = TextAlign.Center,
            color = Color(0xDEFFA559),
        )
        Spacer(modifier = modifier.weight(1f))
        GAuthButton(
            style = Types.Style.DEFAULT,
            actionType = Types.ActionType.SIGNIN,
            colors = Types.Colors.OUTLINE,
            horizontalPaddingValue = 85.dp
        ) {

        }
        Spacer(modifier = modifier.height(70.dp))
    }
}