package com.mpersand.data.dto.order.response

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.order.response.OrderListResponseModel

data class OrderListResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("orderStatus") val orderStatus: String,
    @SerializedName("orderType") val orderType: String
)

fun OrderListResponse.asOrderListResponseModel() = OrderListResponseModel(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description,
    orderStatus = orderStatus,
    orderType = orderType
)