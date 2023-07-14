package com.mpersand.presentation.view.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.presentation.R
import com.mpersand.presentation.view.modifier.gkrClickable

@Composable
fun SearchResultItem(
    data: EquipmentResponseModel,
    navigateToDetail: (productNumber: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .gkrClickable { navigateToDetail(data.productNumber) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(width = 120.dp, height = 90.dp),
            painter = rememberAsyncImagePainter(model = data.image) ?: painterResource(id = R.drawable.ic_temp_equipment_image),
            contentDescription = "equipment image"
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${data.name}(${data.productNumber})",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    fontSize = 10.sp
                )
            )

            Text(
                text = data.description,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    fontSize = 7.sp,
                    color = Color(0xFF969696)
                )
            )

            Text(
                text = "대여 장소 - 전문교육부",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    fontSize = 7.sp,
                    color = Color(0xFF969696)
                )
            )
        }
    }
}