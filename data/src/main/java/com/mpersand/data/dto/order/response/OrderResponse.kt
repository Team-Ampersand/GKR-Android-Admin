package com.mpersand.data.dto.order.response

import com.mpersand.domain.model.order.response.OrderResponseModel
import java.util.UUID

data class OrderResponse(
    val id: UUID,
    val profileUrl: String?,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?
)

fun OrderResponse.asOrderResponseModel() = OrderResponseModel(
    id = id,
    profileUrl = profileUrl,
    name = name,
    grade = grade,
    classNum = classNum,
    number = number
)
