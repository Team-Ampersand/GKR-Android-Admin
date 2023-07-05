package com.mpersand.domain.model.repair.response

data class RepairResponseModel(
    val productNumber: String,
    val reason: String,
    val description: String,
    val repairDate: String,
    val cost: Int,
    val comment: String
)
