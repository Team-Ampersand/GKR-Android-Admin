package com.mpersand.domain.model.equipment.response

data class EquipmentResponseModel(
    val productNumber: String,
    val name: String,
    val image: String,
    val description: String,
    val rentStatus: String
)

