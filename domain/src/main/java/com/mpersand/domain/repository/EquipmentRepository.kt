package com.mpersand.domain.repository

import com.mpersand.domain.model.equipment.response.EquipmentListResponseModel
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface EquipmentRepository {
    suspend fun getAllEquipments(): EquipmentListResponseModel

    suspend fun getEquipmentsByState(equipmentStatus: String): EquipmentListResponseModel

    suspend fun getEquipmentsByType(equipmentType: String): EquipmentListResponseModel

    suspend fun getEquipmentsByFilter(name: String): EquipmentListResponseModel

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponseModel

    suspend fun modifyEquipment(file: MultipartBody.Part, equipment: HashMap<String, RequestBody>)
}
