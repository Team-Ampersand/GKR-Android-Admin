package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.equpiment.response.EquipmentListResponse
import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface EquipmentDataSource {
    suspend fun getAllEquipments(): EquipmentListResponse

    suspend fun getEquipmentsByState(equipmentStatus: String): EquipmentListResponse

    suspend fun getEquipmentsByType(equipmentType: String): EquipmentListResponse

    suspend fun getEquipmentsByFilter(name: String): EquipmentListResponse

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponse

    suspend fun modifyEquipment(productNumber: String, file: MultipartBody.Part, equipment: RequestBody)

    suspend fun changeEquipmentToRepairing(productNumber: String)

    suspend fun completeEquipmentRepair(productNumber: String)
}
