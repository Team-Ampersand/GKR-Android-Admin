package com.mpersand.data.repository

import com.mpersand.data.dto.equpiment.response.asEquipmentListModel
import com.mpersand.data.dto.equpiment.response.asEquipmentResponseModel
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.domain.model.equipment.response.EquipmentListResponseModel
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.repository.EquipmentRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val equipmentDataSource: EquipmentDataSource
) : EquipmentRepository {
    override suspend fun getAllEquipments(): EquipmentListResponseModel =
        equipmentDataSource.getAllEquipments().asEquipmentListModel()

    override suspend fun getEquipmentsByState(equipmentStatus: String): EquipmentListResponseModel =
        equipmentDataSource.getEquipmentsByState(equipmentStatus).asEquipmentListModel()

    override suspend fun getEquipmentsByType(equipmentType: String): EquipmentListResponseModel =
        equipmentDataSource.getEquipmentsByType(equipmentType).asEquipmentListModel()

    override suspend fun getEquipmentsByFilter(name: String): EquipmentListResponseModel =
        equipmentDataSource.getEquipmentsByFilter(name).asEquipmentListModel()

    override suspend fun getEquipmentDetail(productNumber: String): EquipmentResponseModel =
        equipmentDataSource.getEquipmentDetail(productNumber).asEquipmentResponseModel()

    override suspend fun modifyEquipment(file: MultipartBody.Part, equipment: HashMap<String, RequestBody>) =
        equipmentDataSource.modifyEquipment(file, equipment)

    override suspend fun changeEquipmentToRepairing(productNumber: String) =
        equipmentDataSource.changeEquipmentToRepairing(productNumber)


    override suspend fun completeEquipmentRepair(productNumber: String) =
        equipmentDataSource.completeEquipmentRepair(productNumber)

}
