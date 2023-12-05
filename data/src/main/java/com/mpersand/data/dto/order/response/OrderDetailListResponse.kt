package com.mpersand.data.dto.order.response

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel

data class OrderDetailListResponse(
    @SerializedName("applicationId") val applicationId: Long,
    @SerializedName("equipmentId") val equipmentId: Long,
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("orderType") val orderType: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("reason") val reason: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("classNum") val classNum: Int,
    @SerializedName("stuNum") val stuNum: Int,
    @SerializedName("rentalStartDate") val rentalStartDate: String?,
    @SerializedName("rentalEndDate") val rentalEndDate: String?,
    @SerializedName("email") val email: String
)

fun OrderDetailListResponse.asOrderDetailListResponseModel() = OrderDetailListResponseModel(
    applicationId = applicationId,
    equipmentId = equipmentId,
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
    rentalEndDate = rentalEndDate,
    email = email
)