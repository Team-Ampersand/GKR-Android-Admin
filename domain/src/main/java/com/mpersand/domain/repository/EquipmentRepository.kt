package com.mpersand.domain.repository

import com.mpersand.domain.model.equipment.response.EquipmentResponseModel

interface EquipmentRepository {
    suspend fun getRentedEquipments(): List<EquipmentResponseModel>

    suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponseModel>

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponseModel
}