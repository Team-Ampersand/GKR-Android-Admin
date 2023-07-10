package com.mpersand.data.remote.datasource.repair

import com.mpersand.data.dto.repair.request.RepairRequest
import com.mpersand.data.dto.repair.response.RepairResponse
import com.mpersand.data.network.api.RepairApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class RepairDataSourceImpl @Inject constructor(
    private val repairApi: RepairApi
): RepairDataSource {
    override suspend fun getRepairHistory(productNumber: String): List<RepairResponse> = safeApiCall { repairApi.getRepairHistory(productNumber) }

    override suspend fun addRepairHistory(body: RepairRequest) = safeApiCall { repairApi.addRepairHistory(body = body) }
}