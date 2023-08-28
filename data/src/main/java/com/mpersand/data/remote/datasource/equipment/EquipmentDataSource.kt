package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.request.EquipmentRequest
import com.mpersand.data.dto.equpiment.response.EquipmentResponse

interface EquipmentDataSource {
    suspend fun getAllEquipments(): List<EquipmentResponse>

    suspend fun getEquipmentsByState(equipmentStatus: String): List<EquipmentResponse>

    suspend fun getEquipmentsByType(equipmentType: String): List<EquipmentResponse>

    suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponse>

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponse

    suspend fun modifyEquipment(productNumber: String, body: EquipmentRequest)

    suspend fun equipmentFilter(name: String): List<EquipmentResponse>
}