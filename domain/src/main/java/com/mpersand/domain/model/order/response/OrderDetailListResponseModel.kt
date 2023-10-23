package com.mpersand.domain.model.order.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class OrderDetailListResponseModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val orderType: String,
    val userName: String,
    val reason: String,
    val grade: Int,
    val classNum: Int,
    val stuNum: Int,
    val rentalStartDate: LocalDateTime,
    val rentalEndDate: LocalDateTime,
) : Parcelable
