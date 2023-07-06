package com.mpersand.presentation.view.violation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.domain.model.violation.request.ViolationRequestModel
import com.mpersand.presentation.R
import com.mpersand.presentation.viewmodel.violation.ViolationViewModel
import java.util.UUID

@Composable
fun ViolationDialog(
    userId: UUID,
    viewModel: ViolationViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit,
    showSnackBar: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .size(width = 250.dp, height = 300.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            DetailDialogContent(
                value = text,
                onValueChange = { text = it },
                onDismissRequest = onDismissRequest,
                postRentalRequest = {
                    viewModel.postViolationUser(
                        ViolationRequestModel(
                            userId = userId,
                            reason = text
                        )
                    )
                    onDismissRequest()
                    showSnackBar()
                }
            )
        }
    }
}

@Composable
fun DetailDialogContent(
    modifier: Modifier = Modifier,
    value: String,
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    postRentalRequest: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "제제사유",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontSize = 16.sp
            )
        )
        Spacer(modifier = modifier.height(5.dp))
        BasicTextField(
            value = value,
            maxLines = 6,
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .background(
                            color = Color(0xFFF7F7F9),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(vertical = 10.dp)
                ) {
                    Box(modifier = modifier.padding(start = 10.dp)) {
                        if (value.isEmpty()) {
                            Text(
                                text = "제제 사유를 입력해주세요",
                                color = Color(0xFF999999)
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
        Spacer(modifier = modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF865DFF)),
                onClick = { postRentalRequest() }
            ) {
                Text(
                    text = "제제하기",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_light)),
                        fontSize = 9.sp,
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Button(
                modifier = modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFBCA5FE)),
                onClick = { onDismissRequest() }
            ) {
                Text(
                    text = "취소하기",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_light)),
                        fontSize = 9.sp,
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = modifier.height(10.dp))
        }
    }
}