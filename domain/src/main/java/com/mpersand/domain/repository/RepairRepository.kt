package com.mpersand.domain.repository

import com.mpersand.domain.model.repair.request.RepairRequestModel
import com.mpersand.domain.model.repair.response.RepairResponseModel

interface RepairRepository {
    suspend fun getRepairHistory(body: RepairRequestModel): RepairResponseModel
}