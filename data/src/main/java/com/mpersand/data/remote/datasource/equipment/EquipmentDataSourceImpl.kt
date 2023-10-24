package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.response.EquipmentListResponse
import com.mpersand.data.network.api.EquipmentApi
import com.mpersand.data.remote.util.safeApiCall
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class EquipmentDataSourceImpl @Inject constructor(
    private val equipmentApi: EquipmentApi
) : EquipmentDataSource {
    override suspend fun getAllEquipments(): EquipmentListResponse = safeApiCall { equipmentApi.getAllEquipments() }

    override suspend fun getEquipmentsByState(equipmentStatus: String): EquipmentListResponse =
        safeApiCall { equipmentApi.getEquipmentsByState(equipmentStatus) }

    override suspend fun getEquipmentsByType(equipmentType: String): EquipmentListResponse =
        safeApiCall { equipmentApi.getEquipmentsByType(equipmentType) }

    override suspend fun getEquipmentsByFilter(name: String): EquipmentListResponse = safeApiCall { equipmentApi.getEquipmentsByFilter(name) }

    override suspend fun getEquipmentDetail(productNumber: String) = safeApiCall { equipmentApi.getEquipmentDetail(productNumber) }

    override suspend fun modifyEquipment(productNumber: String, file: MultipartBody.Part, equipment: RequestBody) =
        safeApiCall { equipmentApi.modifyEquipment(productNumber, file, equipment) }

    override suspend fun changeEquipmentToRepairing(productNumber: String) {
        safeApiCall { equipmentApi.changeEquipmentToRepairing(productNumber) }
    }

    override suspend fun completeEquipmentRepair(productNumber: String) {
        safeApiCall { equipmentApi.completeEquipmentRepair(productNumber) }
    }
}
