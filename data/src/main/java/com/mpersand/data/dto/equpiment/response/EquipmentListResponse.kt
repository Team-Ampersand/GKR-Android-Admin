package com.mpersand.data.dto.equpiment.response

import com.mpersand.domain.model.equipment.response.EquipmentListResponseModel

data class EquipmentListResponse(
    val equipmentList: List<EquipmentResponse>
)

fun EquipmentListResponse.asEquipmentListModel() = EquipmentListResponseModel(
    equipmentList = equipmentList.map { it.asEquipmentResponseModel() }
)
