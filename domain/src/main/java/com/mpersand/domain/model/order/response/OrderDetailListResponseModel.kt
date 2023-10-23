package com.mpersand.domain.model.order.response

import java.time.LocalDateTime

data class OrderDetailListResponseModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val orderType: String,
    val userName: String,
    val reason: String,
    val rentalStartDate: LocalDateTime,
    val rentalEndDate: LocalDateTime,
)
