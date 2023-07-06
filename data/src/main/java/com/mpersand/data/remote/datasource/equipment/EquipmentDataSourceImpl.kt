package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import com.mpersand.data.network.api.EquipmentApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class EquipmentDataSourceImpl @Inject constructor(
    private val equipmentApi: EquipmentApi
): EquipmentDataSource {
    override suspend fun getRentedEquipments(): List<EquipmentResponse> = safeApiCall { equipmentApi.getRentedEquipments() }

    override suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponse> = safeApiCall { equipmentApi.getEquipmentsByFilter(name) }

    override suspend fun getEquipmentDetail(productNumber: String) = safeApiCall { equipmentApi.getEquipmentDetail(productNumber) }

    override suspend fun modifyEquipment(productNumber: String) = safeApiCall { equipmentApi.modifyEquipment(productNumber) }
}