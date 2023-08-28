package com.mpersand.domain.repository

import com.mpersand.domain.model.equipment.request.EquipmentRequestModel
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel

interface EquipmentRepository {
    suspend fun getAllEquipments(): List<EquipmentResponseModel>

    suspend fun getEquipmentsByState(equipmentStatus: String): List<EquipmentResponseModel>

    suspend fun getEquipmentsByType(equipmentType: String): List<EquipmentResponseModel>

    suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponseModel>

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponseModel

    suspend fun modifyEquipment(productNumber: String, body: EquipmentRequestModel)
}