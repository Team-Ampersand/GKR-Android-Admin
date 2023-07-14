package com.mpersand.data.network.api

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.response.OrderResponse
import com.mpersand.data.dto.order.response.WaitListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderApi {
    @GET("order/noreturn")
    suspend fun getNoReturnStudents(): List<OrderResponse>

    @GET("order/wait")
    suspend fun getWaitList(): List<WaitListResponse>

    @POST("order")
    suspend fun requestResult(@Body body: OrderRequest)
}