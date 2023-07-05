package com.mpersand.data.remote.datasource.repair

import com.mpersand.data.dto.repair.request.RepairRequest
import com.mpersand.data.dto.repair.response.RepairResponse
import com.mpersand.data.network.api.RepairApi
import javax.inject.Inject

class RepairDataSourceImpl @Inject constructor(
    private val repairApi: RepairApi
): RepairDataSource {
    override suspend fun getRepairHistory(body: RepairRequest): RepairResponse = repairApi.getRepairHistory(body)
}