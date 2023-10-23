package com.mpersand.domain.repository

import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.domain.model.order.response.OrderListResponseModel
import com.mpersand.domain.model.order.response.OrderResponseModel
import com.mpersand.domain.model.order.response.WaitListResponseModel

interface OrderRepository {
    suspend fun getSelfStateList(): List<OrderListResponseModel>

    suspend fun getNowRentalList(): List<OrderDetailListResponseModel>

    suspend fun getNoReturnList(): List<OrderDetailListResponseModel>

    suspend fun getWaitList(): List<OrderDetailListResponseModel>

    suspend fun postRental(id: Int, response: String)

    suspend fun postReturn(id: Int)

    suspend fun postExtension(id: Int, response: String)

    suspend fun postRentalCancel(id: Int)

    suspend fun acceptRequest(id: Int)

    suspend fun rejectRequest(id: Int)
}