package com.mpersand.presentation.view.main.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun LogoutDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    logoutAction: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = modifier
                .size(width = 250.dp, height = 130.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        ) {
            Text(
                text = "로그아웃",
                fontSize = 16.sp
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = "정말 로그아웃 하시겠습니까?",
                fontSize = 12.sp
            )
            Spacer(modifier = modifier.weight(1f))
            Row(modifier = modifier.fillMaxWidth()) {
                OutlinedButton(
                    modifier = modifier.weight(1f),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xFFCBCBCB)
                    ),
                    onClick = { onDismissRequest() }
                ) {
                    Text(
                        text = "취소하기",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFFCBCBCB)
                        )
                    )
                }
                Spacer(modifier = modifier.width(10.dp))
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
                    onClick = {
                        onDismissRequest()
                        logoutAction()
                    }
                ) {
                    Text(
                        text = "로그아웃",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}