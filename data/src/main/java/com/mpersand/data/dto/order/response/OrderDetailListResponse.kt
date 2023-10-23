package com.mpersand.data.dto.order.response

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import java.time.LocalDateTime

data class OrderDetailListResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("orderType") val orderType: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("reason") val reason: String,
    @SerializedName("rentalStartDate") val rentalStartDate: LocalDateTime,
    @SerializedName("rentalEndDate") val rentalEndDate: LocalDateTime,
)

fun OrderDetailListResponse.asOrderDetailListResponseModel() = OrderDetailListResponseModel(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description,
    orderType = orderType,
    userName = userName,
    reason = reason,
    rentalStartDate = rentalStartDate,
    rentalEndDate = rentalEndDate
)