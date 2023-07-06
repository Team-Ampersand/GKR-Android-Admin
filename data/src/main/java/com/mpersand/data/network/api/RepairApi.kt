package com.mpersand.data.network.api

import com.mpersand.data.dto.repair.response.RepairResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepairApi {
    @GET("repair")
    suspend fun getRepairHistory(
        @Query("productNumber") productNumber: String
    ): List<RepairResponse>
}