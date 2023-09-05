package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import com.mpersand.data.network.api.EquipmentApi
import com.mpersand.data.remote.util.safeApiCall
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class EquipmentDataSourceImpl @Inject constructor(
    private val equipmentApi: EquipmentApi
) : EquipmentDataSource {
    override suspend fun getAllEquipments(): List<EquipmentResponse> = safeApiCall { equipmentApi.getAllEquipments() }
    override suspend fun getEquipmentsByState(equipmentStatus: String): List<EquipmentResponse> =
        safeApiCall { equipmentApi.getEquipmentsByState(equipmentStatus) }

    override suspend fun getEquipmentsByType(equipmentType: String): List<EquipmentResponse> =
        safeApiCall { equipmentApi.getEquipmentsByType(equipmentType) }

    override suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponse> = safeApiCall { equipmentApi.getEquipmentsByFilter(name) }

    override suspend fun getEquipmentDetail(productNumber: String) = safeApiCall { equipmentApi.getEquipmentDetail(productNumber) }

    override suspend fun modifyEquipment(file: MultipartBody.Part, equipment: HashMap<String, RequestBody>) =
        safeApiCall { equipmentApi.modifyEquipment(file, equipment) }

    override suspend fun equipmentFilter(name: String): List<EquipmentResponse> = safeApiCall { equipmentApi.getEquipmentsByFilter(name) }
}