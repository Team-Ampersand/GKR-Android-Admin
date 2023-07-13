package com.mpersand.domain.model.order.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class WaitListResponseModel(
    val userId: UUID,
    val equipmentId: String,
    val reason: String,
    val state: String,
    val rentalDate: String,
    val returnDate: String
): Parcelable
