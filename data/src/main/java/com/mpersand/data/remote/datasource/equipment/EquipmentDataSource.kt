package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.request.EquipmentRequest
import com.mpersand.data.dto.equpiment.response.EquipmentResponse

interface EquipmentDataSource {
    suspend fun getRentedEquipments(): List<EquipmentResponse>

    suspend fun getNotRentedEquipments(): List<EquipmentResponse>

    suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponse>

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponse

    suspend fun modifyEquipment(productNumber: String, body: EquipmentRequest)

    suspend fun equipmentFilter(name: String): List<EquipmentResponse>
}