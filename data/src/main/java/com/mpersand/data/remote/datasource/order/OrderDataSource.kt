package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.response.OrderResponse

interface OrderDataSource {
    suspend fun getNoReturnStudents(): List<OrderResponse>
}