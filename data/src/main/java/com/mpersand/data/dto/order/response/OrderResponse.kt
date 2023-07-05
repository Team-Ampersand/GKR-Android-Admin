package com.mpersand.data.dto.order.response

import java.util.UUID

data class OrderResponse(
    val id: UUID,
    val profileUrl: String?,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?
)
