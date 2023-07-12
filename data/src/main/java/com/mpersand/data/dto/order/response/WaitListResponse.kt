package com.mpersand.data.dto.order.response

import com.mpersand.domain.model.order.response.WaitListResponseModel
import java.util.UUID

data class WaitListResponse(
    val userId: UUID,
    val equipmentId: String,
    val reason: String,
    val state: String,
    val rentalDate: String,
    val returnDate: String
)

fun WaitListResponse.asWaitListResponseModel() = WaitListResponseModel(
    userId = userId,
    equipmentId = equipmentId,
    reason = reason,
    state = state,
    rentalDate = rentalDate,
    returnDate = returnDate
)
