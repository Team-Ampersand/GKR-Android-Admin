package com.mpersand.data.dto.order.response

import com.mpersand.domain.model.order.response.RentalInfoResponseModel

data class RentalInfoResponse(
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
    val rentalStartDate: String,
    val rentalEndDate: String
)

fun RentalInfoResponse.asRentalInfoResponseModel(): RentalInfoResponseModel {
    return RentalInfoResponseModel(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        orderType = orderType,
        userName = userName,
        grade = grade,
        classNum = classNum,
        stuNum = stuNum,
        reason = reason,
        rentalStartDate = rentalStartDate,
        rentalEndDate = rentalEndDate
    )
}
