package com.mpersand.data.dto.repair.request

import com.mpersand.domain.model.repair.request.RepairRequestModel

data class RepairRequest(
    val productNumber: String,
    val reason: String,
    val description: String,
    val repairDate: String,
    val cost: Int,
    val comment: String
)

fun RepairRequestModel.asRepairResult() = RepairRequest(
    productNumber = productNumber,
    reason = reason,
    description = description,
    repairDate = repairDate,
    cost = cost,
    comment = comment
)
