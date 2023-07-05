package com.mpersand.data.dto.repair.response

import com.mpersand.domain.model.repair.response.RepairResponseModel

data class RepairResponse(
    val productNumber: String,
    val reason: String,
    val description: String,
    val repairDate: String,
    val cost: Int,
    val comment: String
)

fun RepairResponse.asRepairResponseModel() = RepairResponseModel(
    productNumber = productNumber,
    reason = reason,
    description = description,
    repairDate = repairDate,
    cost = cost,
    comment = comment
)
