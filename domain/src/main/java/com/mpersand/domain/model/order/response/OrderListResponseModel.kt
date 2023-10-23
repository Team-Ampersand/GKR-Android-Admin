package com.mpersand.domain.model.order.response

data class OrderListResponseModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val orderStatus: String,
    val orderType: String
)