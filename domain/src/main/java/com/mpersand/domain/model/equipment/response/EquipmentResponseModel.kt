package com.mpersand.domain.model.equipment.response

data class EquipmentListResponseModel(
    val equipmentList: List<EquipmentResponseModel>
)

data class EquipmentResponseModel(
    val productNumber: String,
    val name: String,
    val image: String,
    val description: String,
    val equipmentStatus: String,
    val equipmentType: String
)

