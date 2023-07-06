package com.mpersand.data.network.api

import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface EquipmentApi {
    @GET("equipment/isrent")
    suspend fun getRentedEquipments(): List<EquipmentResponse>

    @GET("equipment")
    suspend fun getEquipmentsByFilter(
        @Query("name") name: String
    ): List<EquipmentResponse>

    @GET("equipment/{productNumber}")
    suspend fun getEquipmentDetail(
        @Path("productNumber") productNumber: String
    ): EquipmentResponse

    @PATCH("equipment/{productNumber}")
    suspend fun modifyEquipment(
        @Path("productNumber") productNumber: String
    )
}