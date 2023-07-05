package com.mpersand.domain.model.order.response

import java.util.UUID

data class OrderResponseModel(
    val id: UUID,
    val profileUrl: String?,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?
)
