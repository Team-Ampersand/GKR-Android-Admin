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
    @SerializedName("grade") val grade: Int,
    @SerializedName("classNum") val classNum: Int,
    @SerializedName("stuNum") val stuNum: Int,
    @SerializedName("rentalStartDate") val rentalStartDate: LocalDateTime?,
    @SerializedName("rentalEndDate") val rentalEndDate: LocalDateTime?,
)

fun OrderDetailListResponse.asOrderDetailListResponseModel() = OrderDetailListResponseModel(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description,
    orderType = orderType,
    userName = userName,
    reason = reason,
    grade = grade,
    classNum = classNum,
    stuNum = stuNum,
    rentalStartDate = rentalStartDate,
    rentalEndDate = rentalEndDate
)