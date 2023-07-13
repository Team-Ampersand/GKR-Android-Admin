package com.mpersand.domain.repository

import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.model.order.response.OrderResponseModel
import com.mpersand.domain.model.order.response.WaitListResponseModel

interface OrderRepository {
    suspend fun getNoReturnStudents(): List<OrderResponseModel>

    suspend fun getWaitList(): List<WaitListResponseModel>

    suspend fun requestResult(body: OrderRequestModel)
}