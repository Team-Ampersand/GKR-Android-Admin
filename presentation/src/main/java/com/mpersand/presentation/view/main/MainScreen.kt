package com.mpersand.presentation.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mpersand.presentation.view.main.component.AppBar
import com.mpersand.presentation.view.main.component.FilterItem
import com.mpersand.presentation.view.main.component.StudentItem

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedValue by remember { mutableStateOf(0) }

    val filter = listOf("전체", "모니터", "노트북", "키보드", "라즈베리파이")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        AppBar()
        LazyRow {
            items(5) {
                Spacer(modifier = modifier.width(10.dp))
                FilterItem(
                    selected = it == selectedValue,
                    content = filter[it],
                    onClick = { selectedValue = it }
                )
            }
        }
        Spacer(modifier = modifier.height(8.dp))
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 13.dp)
                .background(Color.White)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 12.dp)
        ) {
            items(15) {
                StudentItem(
                    studentName = "테스트",
                    studentNumber = "0학년 0반 0번",
                    rentalAmount = "대여한 물품 0개",
                    rentalItems = "대여한 물품명 : 노트북"
                )
            }
        }
    }
}