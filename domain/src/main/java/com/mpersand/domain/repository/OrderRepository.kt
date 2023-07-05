package com.mpersand.domain.repository

import com.mpersand.domain.model.order.response.OrderResponseModel

interface OrderRepository {
    suspend fun getNoReturnStudents(): List<OrderResponseModel>
}