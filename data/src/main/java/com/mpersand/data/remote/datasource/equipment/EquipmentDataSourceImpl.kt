package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import com.mpersand.data.network.api.EquipmentApi
import javax.inject.Inject

class EquipmentDataSourceImpl @Inject constructor(
    private val equipmentApi: EquipmentApi
): EquipmentDataSource {
    override suspend fun getRentedEquipments(): List<EquipmentResponse> = equipmentApi.getRentedEquipments()

    override suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponse> = equipmentApi.getEquipmentsByFilter(name)
}