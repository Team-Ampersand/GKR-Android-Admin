package com.mpersand.data.network.api

import com.mpersand.data.dto.repair.request.RepairRequest
import com.mpersand.data.dto.repair.response.RepairResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface RepairApi {
    @GET("repair")
    suspend fun getRepairHistory(
        @Body body: RepairRequest
    ): RepairResponse
}