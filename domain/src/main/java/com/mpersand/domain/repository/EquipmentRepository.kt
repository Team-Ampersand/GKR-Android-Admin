package com.mpersand.domain.repository

import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface EquipmentRepository {
    suspend fun getAllEquipments(): List<EquipmentResponseModel>

    suspend fun getEquipmentsByState(equipmentStatus: String): List<EquipmentResponseModel>

    suspend fun getEquipmentsByType(equipmentType: String): List<EquipmentResponseModel>

    suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponseModel>

    suspend fun getEquipmentDetail(productNumber: String): EquipmentResponseModel

    suspend fun modifyEquipment(file: MultipartBody.Part, equipment: HashMap<String, RequestBody>)
}