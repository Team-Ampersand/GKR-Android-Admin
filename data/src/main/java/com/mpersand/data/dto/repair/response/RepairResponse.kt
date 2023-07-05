package com.mpersand.data.dto.repair.response

data class RepairResponse(
    val productNumber: String,
    val reason: String,
    val description: String,
    val repairDate: String,
    val cost: Int,
    val comment: String
)
