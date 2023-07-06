package com.mpersand.presentation.view.modify.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ModifyTextField(
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    maxLines: Int = 1,
    onValueChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFD3D3D3),
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = Color(0xFFF7F7F9),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
        value = text,
        minLines = minLines,
        maxLines = maxLines,
        onValueChange = {
            text = it
            onValueChanged(it)
        }
    )
}