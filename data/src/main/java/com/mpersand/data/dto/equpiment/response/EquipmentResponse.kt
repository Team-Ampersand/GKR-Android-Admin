package com.mpersand.data.dto.equpiment.response

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.equipment.response.EquipmentListResponseModel
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel

data class EquipmentResponse(
    @SerializedName("id") val productNumber: String,
    val name: String,
    @SerializedName("imageUrl") val image: String,
    val description: String,
    val equipmentStatus: String,
    val equipmentType: String
)

fun EquipmentListResponse.asEquipmentListModel() = EquipmentListResponseModel(
    equipmentList = equipmentList.map { it.asEquipmentResponseModel() }
)

fun EquipmentResponse.asEquipmentResponseModel() = EquipmentResponseModel(
    productNumber = productNumber,
    name = name,
    image = image,
    description = description,
    equipmentStatus = equipmentStatus,
    equipmentType = equipmentType
)
