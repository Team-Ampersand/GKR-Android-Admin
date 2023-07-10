package com.mpersand.data.remote.datasource.repair

import com.mpersand.data.dto.repair.request.RepairRequest
import com.mpersand.data.dto.repair.response.RepairResponse

interface RepairDataSource {
    suspend fun getRepairHistory(productNumber: String): List<RepairResponse>

    suspend fun addRepairHistory(body: RepairRequest)
}