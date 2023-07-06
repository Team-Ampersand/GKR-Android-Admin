package com.mpersand.domain.model.repair.request

data class RepairRequestModel(
    val productNumber: String,
    val reason: String,
    val description: String,
    val repairDate: String,
    val cost: Int,
    val comment: String
)
