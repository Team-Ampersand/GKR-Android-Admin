package com.mpersand.domain.model.order.response

data class RentalInfoResponseModel(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val description: String,
    val orderType: String,
    val userName: String,
    val grade: Int,
    val classNum: Int,
    val stuNum: Int,
    val reason: String,
    val rentalStartDate: String?,
    val rentalEndDate: String?
)
