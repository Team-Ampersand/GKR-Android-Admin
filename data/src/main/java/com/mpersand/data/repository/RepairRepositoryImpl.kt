package com.mpersand.data.repository

import com.mpersand.data.dto.repair.response.asRepairResponseModel
import com.mpersand.data.remote.datasource.repair.RepairDataSource
import com.mpersand.domain.model.repair.response.RepairResponseModel
import com.mpersand.domain.repository.RepairRepository
import javax.inject.Inject

class RepairRepositoryImpl @Inject constructor(
    private val repairDataSource: RepairDataSource
) : RepairRepository {
    override suspend fun getRepairHistory(productNumber: String): List<RepairResponseModel> =
        repairDataSource.getRepairHistory(productNumber).map { it.asRepairResponseModel() }
}