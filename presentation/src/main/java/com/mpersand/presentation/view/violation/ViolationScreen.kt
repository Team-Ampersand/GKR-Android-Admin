package com.mpersand.presentation.view.violation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.violation.component.ViolationItem

@Composable
fun ViolationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        GKRToolbar(title = "제제 하기") {
        }
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 13.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 12.dp)
        ) {
            items(5) {
                ViolationItem(
                    grade = "3",
                    classNum = "2",
                    number =  "8",
                    productNumber = "박성현",
                    image = ""
                )
            }
        }
    }    
}

@Preview
@Composable
fun preview() {
    ViolationScreen()
}