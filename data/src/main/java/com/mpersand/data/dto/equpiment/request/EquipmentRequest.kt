package com.mpersand.data.dto.equpiment.request

import com.mpersand.domain.model.equipment.request.EquipmentRequestModel

data class EquipmentRequest(
    val name: String,
    val image: String,
    val description: String
)

fun EquipmentRequestModel.asEquipmentRequest() = EquipmentRequest(
    name = name,
    image = image,
    description = description
)