package com.mpersand.data.repository

import com.mpersand.data.dto.order.request.asOrderRequest
import com.mpersand.data.dto.order.response.asOrderResponseModel
import com.mpersand.data.dto.order.response.asWaitListResponseModel
import com.mpersand.data.remote.datasource.order.OrderDataSource
import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.model.order.response.OrderResponseModel
import com.mpersand.domain.model.order.response.WaitListResponseModel
import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDataSource: OrderDataSource
): OrderRepository {
    override suspend fun getNoReturnStudents(): List<OrderResponseModel> =
        orderDataSource.getNoReturnStudents().map { it.asOrderResponseModel() }

    override suspend fun getWaitList(): List<WaitListResponseModel> =
        orderDataSource.getWaitList().map { it.asWaitListResponseModel() }

    override suspend fun requestResult(body: OrderRequestModel) =
        orderDataSource.requestResult(body.asOrderRequest())
}