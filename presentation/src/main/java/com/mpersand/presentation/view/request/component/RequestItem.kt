package com.mpersand.presentation.view.request.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.presentation.R
import com.mpersand.presentation.view.modifier.gkrClickable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun RequestItem(
    data: OrderDetailListResponseModel,
    onCardClick: (OrderDetailListResponseModel) -> Unit
) {
    var localDate = ""
    if (data.rentalStartDate != null) localDate = LocalDateTime.parse(data.rentalStartDate).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFD3D3D3),
                shape = RoundedCornerShape(8.dp)
            )
            .gkrClickable { onCardClick(data) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = data.userName,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_black)),
                        fontSize = 15.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = localDate,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(resId = R.font.inter_light, weight = FontWeight.Medium)),
                        fontSize = 12.sp
                    ),
                    maxLines = 1
                )
            }

            Text(
                text = data.reason,
                style = TextStyle(
                    fontFamily = FontFamily(Font(resId = R.font.inter_light, weight = FontWeight.Medium)),
                    fontSize = 12.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
