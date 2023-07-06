package com.mpersand.data.repository

import com.mpersand.data.dto.equpiment.response.asEquipmentResponseModel
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val equipmentDataSource: EquipmentDataSource
) : EquipmentRepository {
    override suspend fun getRentedEquipments(): List<EquipmentResponseModel> =
        equipmentDataSource.getRentedEquipments().map { it.asEquipmentResponseModel() }

    override suspend fun getEquipmentsByFilter(name: String): List<EquipmentResponseModel> =
        equipmentDataSource.getEquipmentsByFilter(name).map { it.asEquipmentResponseModel() }

    override suspend fun getEquipmentDetail(productNumber: String): EquipmentResponseModel =
        equipmentDataSource.getEquipmentDetail(productNumber).asEquipmentResponseModel()

    override suspend fun modifyEquipment(productNumber: String) =
        equipmentDataSource.modifyEquipment(productNumber)
}