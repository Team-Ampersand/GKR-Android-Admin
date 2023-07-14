package com.mpersand.presentation.view.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpersand.presentation.R
import com.mpersand.presentation.view.search.component.SearchHistoryItem
import com.mpersand.presentation.view.search.component.SearchResultItem
import com.mpersand.presentation.viewmodel.equipment.EquipmentViewModel
import com.mpersand.presentation.viewmodel.util.UiState

@Composable
fun SearchScreen(
    equipmentViewModel: EquipmentViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToDetail: (productNumber: String) -> Unit
) {
    val textChange = remember { mutableStateOf(false) }
    val isTextChanged = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .systemBarsPadding()
    ) {
        val textState = remember { mutableStateOf("") }

        LaunchedEffect(textState.value) {
            textChange.value = true
        }

        SearchToolbar(
            value = textState.value,
            onValueChange = {
                textState.value = it
                isTextChanged.value = true
            },
            onSearchClick = {
                textChange.value = false
                isTextChanged.value = false
            },
            onBackIconClick = navigateToMain
        )
        Divider()

        if (!textChange.value && !isTextChanged.value && textState.value.isNotEmpty())
            SearchResultView(
                text = textState.value,
                equipmentViewModel = equipmentViewModel,
                navigateToDetail = navigateToDetail
            )
    }
}

@Composable
fun SearchToolbar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onBackIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFFAFAFA)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackIconClick) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "back right arrow",
                tint = Color(0xFFC3C3C3)
            )
        }

        BasicTextField(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "검색어를 입력해주세요",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(resId = R.font.fraunces_black, weight = FontWeight.SemiBold)),
                            fontSize = 12.sp,
                            color = Color(0xFFC3C3C3)
                        )
                    )
                }
                innerTextField()
            }
        )

        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = Color(0xFFC3C3C3)
            )
        }
    }
}

@Composable
fun SearchHistoryView(textState: MutableState<String>) {
    val searchHistoryList = listOf("노트북 대여", "라즈베리 파이", "노트북 대여")

    Text(
        modifier = Modifier.padding(15.dp),
        text = "검색 기록",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            fontSize = 10.sp,
            color = Color(0xFFC3C3C3)
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(searchHistoryList) {
            SearchHistoryItem(
                title = it,
                loadTextLogic = { textState.value = it }
            )
        }
    }
}

@Composable
fun SearchResultView(
    text: String,
    equipmentViewModel: EquipmentViewModel,
    navigateToDetail: (productNumber: String) -> Unit
) {
    Text(
        modifier = Modifier.padding(15.dp),
        text = "${text}(으)로 검색한 결과",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            fontSize = 10.sp,
            color = Color(0xFFC3C3C3)
        )
    )

    equipmentViewModel.searchEquipment(text)
    val searchResult by equipmentViewModel.equipmentFilter.observeAsState()

    when (val state = searchResult) {
        UiState.Loading -> {}
        is UiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(state.data!!) {
                    SearchResultItem(
                        data = it,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
        UiState.BadRequest -> {}
        UiState.NotFound -> {}
        else -> {}
    }
}