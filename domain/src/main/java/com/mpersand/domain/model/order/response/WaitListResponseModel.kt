package com.mpersand.domain.model.order.response

import java.util.UUID

data class WaitListResponseModel(
    val userId: UUID,
    val equipmentId: String,
    val reason: String,
    val state: String,
    val rentalDate: String,
    val returnDate: String
)
