package com.mpersand.data.network.api

import com.mpersand.data.dto.equpiment.response.EquipmentListResponse
import com.mpersand.data.dto.equpiment.response.EquipmentResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface EquipmentApi {
    @GET("equipment")
    suspend fun getAllEquipments(): EquipmentListResponse

    @GET("equipment/state")
    suspend fun getEquipmentsByState(
        @Query("equipmentStatus") equipmentStatus: String
    ): EquipmentListResponse

    @GET("equipment/type")
    suspend fun getEquipmentsByType(
        @Query("equipmentType") equipmentType: String
    ): EquipmentListResponse

    @GET("equipment/search")
    suspend fun getEquipmentsByFilter(
        @Query("name") name: String
    ): EquipmentListResponse

    @GET("equipment/{id}")
    suspend fun getEquipmentDetail(
        @Path("id") productNumber: String
    ): EquipmentResponse

    @Multipart
    @PATCH("equipment/edit/{id}")
    suspend fun modifyEquipment(
        @Part("file") file: MultipartBody.Part,
        @PartMap equipment: HashMap<String, RequestBody>
    )

    @PATCH("equipment/repair/{id}")
    suspend fun changeEquipmentToRepairing(
        @Path("id") productNumber: String
    )

    @PATCH("equipment/repair/complete/{id}")
    suspend fun completeEquipmentRepair(
        @Path("id") productNumber: String
    )
}
