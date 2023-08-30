package com.mpersand.data.network.api

import com.mpersand.data.dto.equpiment.request.EquipmentRequest
import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface EquipmentApi {
    @GET("equipment")
    suspend fun getAllEquipments(): List<EquipmentResponse>

    @GET("equipment/state")
    suspend fun getEquipmentsByState(
        @Query("equipmentStatus") equipmentStatus: String
    ): List<EquipmentResponse>

    @GET("equipment/type")
    suspend fun getEquipmentsByType(
        @Query("equipmentType") equipmentType: String
    ): List<EquipmentResponse>

    @GET("equipment")
    suspend fun getEquipmentsByFilter(
        @Query("name") name: String
    ): List<EquipmentResponse>

    @GET("equipment/{id}")
    suspend fun getEquipmentDetail(
        @Path("id") productNumber: String
    ): EquipmentResponse

    @PATCH("equipment/{productNumber}")
    suspend fun modifyEquipment(
        @Path("productNumber") productNumber: String,
        @Body body: EquipmentRequest
    )
}