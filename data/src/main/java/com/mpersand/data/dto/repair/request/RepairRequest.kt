package com.mpersand.data.dto.repair.request

import com.mpersand.domain.model.repair.request.RepairRequestModel

data class RepairRequest(
    val productNumber: String
)

fun RepairRequestModel.asRepairRequest() = RepairRequest(
    productNumber = productNumber
)