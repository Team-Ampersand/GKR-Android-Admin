package com.mpersand.domain.repository

import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel
import com.mpersand.domain.model.order.response.OrderDetailListResponseModel
import com.mpersand.domain.model.order.response.OrderEquipmentListResponseModel
import com.mpersand.domain.model.order.response.OrderListResponseModel
import com.mpersand.domain.model.order.response.OrderResponseModel
import com.mpersand.domain.model.order.response.WaitListResponseModel

interface OrderRepository {
    suspend fun getSelfStateList(): OrderEquipmentListResponseModel

    suspend fun getNowRentalList(): OrderApplicationListResponseModel

    suspend fun getNoReturnList(): OrderApplicationListResponseModel

    suspend fun getWaitList(): OrderApplicationListResponseModel

    suspend fun postRental(id: Int, response: String)

    suspend fun postReturn(id: Int)

    suspend fun postExtension(id: Int, response: String)

    suspend fun postRentalCancel(id: Int)

    suspend fun acceptRequest(id: Int)

    suspend fun rejectRequest(id: Int)
}