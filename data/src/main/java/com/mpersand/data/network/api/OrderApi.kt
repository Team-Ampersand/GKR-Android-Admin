package com.mpersand.data.network.api

import com.mpersand.data.dto.order.response.OrderApplicationListResponse
import com.mpersand.data.dto.order.response.OrderDetailListResponse
import com.mpersand.data.dto.order.response.OrderEquipmentListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {

    @GET("order/state")
    suspend fun getSelfStateList(): OrderEquipmentListResponse

    @GET("order/now")
    suspend fun getNowRentalList(): OrderApplicationListResponse

    @GET("order/noreturn")
    suspend fun getNoReturnList(): OrderApplicationListResponse

    @GET("order/wait")
    suspend fun getWaitList(): OrderApplicationListResponse

    @GET("order/detail/{id}")
    suspend fun getRentRequestDetail(@Path("id") id: String): OrderDetailListResponse

    @POST("order/rental/{id}")
    suspend fun postRental(
        @Path("id") id: Int,
        @Body response: String
    )

    @POST("order/return/{id}")
    suspend fun postReturn(
        @Path("id") id: Int
    )

    @POST("order/extension/{id}")
    suspend fun postExtension(
        @Path("id") id: Int,
        @Body response: String
    )

    @POST("order/cancel/{id}")
    suspend fun postRentalCancel(
        @Path("id") id: Int
    )

    @PATCH("order/accept/{id}")
    suspend fun acceptRequest(
        @Path("id") id: Int
    )

    @PATCH("order/reject/{id}")
    suspend fun rejectRequest(
        @Path("id") id: Int
    )
}