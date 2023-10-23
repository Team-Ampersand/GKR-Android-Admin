package com.mpersand.data.network.api

import com.mpersand.data.dto.order.response.OrderDetailListResponse
import com.mpersand.data.dto.order.response.OrderListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {

    @GET("order/state")
    suspend fun getSelfStateList(): List<OrderListResponse>

    @GET("order/now")
    suspend fun getNowRentalList(): List<OrderDetailListResponse>

    @GET("order/noreturn")
    suspend fun getNoReturnList(): List<OrderDetailListResponse>

    @GET("order/wait")
    suspend fun getWaitList(): List<OrderDetailListResponse>

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

//    @POST("order")
//    suspend fun requestResult(@Body body: OrderRequest)
}