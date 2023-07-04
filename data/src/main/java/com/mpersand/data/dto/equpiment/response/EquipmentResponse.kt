package com.mpersand.data.dto.equpiment.response

import com.mpersand.domain.model.equipment.response.EquipmentResponseModel

data class EquipmentResponse(
    val productNumber: String,
    val name: String,
    val image: String,
    val description: String,
    val rentStatus: String
)

fun EquipmentResponse.asEquipmentResponseModel() = EquipmentResponseModel(
    productNumber = productNumber,
    name = name,
    image = image,
    description = description,
    rentStatus = rentStatus
)
