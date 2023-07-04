package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.response.EquipmentResponse

interface EquipmentDataSource {
    suspend fun getRentedEquipments(): List<EquipmentResponse>

    suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponse>
}