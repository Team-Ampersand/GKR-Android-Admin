package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.response.OrderResponse
import com.mpersand.data.dto.order.response.WaitListResponse

interface OrderDataSource {
    suspend fun getNoReturnStudents(): List<OrderResponse>

    suspend fun getWaitList(): List<WaitListResponse>

    suspend fun requestResult(body: OrderRequest)
}