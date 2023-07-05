package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.response.OrderResponse
import com.mpersand.data.network.api.OrderApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderApi: OrderApi
): OrderDataSource {
    override suspend fun getNoReturnStudentList(): List<OrderResponse> = safeApiCall { orderApi.getNoReturnStudents() }
}