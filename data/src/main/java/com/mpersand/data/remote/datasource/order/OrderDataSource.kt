package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.response.OrderApplicationListResponse
import com.mpersand.data.dto.order.response.OrderDetailListResponse
import com.mpersand.data.dto.order.response.OrderListResponse
import com.mpersand.data.dto.order.response.OrderResponse
import com.mpersand.data.dto.order.response.WaitListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderDataSource {
    suspend fun getSelfStateList(): List<OrderListResponse>

    suspend fun getNowRentalList(): OrderApplicationListResponse

    suspend fun getNoReturnList(): OrderApplicationListResponse

    suspend fun getWaitList(): OrderApplicationListResponse

    suspend fun postRental(id: Int, response: String)

    suspend fun postReturn(id: Int)

    suspend fun postExtension(id: Int, response: String)

    suspend fun postRentalCancel(id: Int)

    suspend fun acceptRequest(id: Int)

    suspend fun rejectRequest(id: Int)
}