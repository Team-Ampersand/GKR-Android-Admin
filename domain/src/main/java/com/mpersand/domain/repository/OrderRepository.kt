package com.mpersand.domain.repository

import com.mpersand.domain.model.order.response.OrderResponseModel

interface OrderRepository {
    suspend fun getNoReturnStudents(): List<OrderResponseModel>

    suspend fun getWaitList(): List<OrderResponseModel>
}