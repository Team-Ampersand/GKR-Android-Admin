package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.response.OrderApplicationListResponse
import com.mpersand.data.dto.order.response.OrderDetailListResponse
import com.mpersand.data.dto.order.response.OrderEquipmentListResponse

interface OrderDataSource {
    suspend fun getSelfStateList(): OrderEquipmentListResponse

    suspend fun getNowRentalList(): OrderApplicationListResponse

    suspend fun getNoReturnList(): OrderApplicationListResponse

    suspend fun getWaitList(): OrderApplicationListResponse

    suspend fun getRentalRequestDetail(id: String): OrderDetailListResponse

    suspend fun postRental(id: Int, response: String)

    suspend fun postReturn(id: Int)

    suspend fun postExtension(id: Int, response: String)

    suspend fun postRentalCancel(id: Int)

    suspend fun acceptRequest(id: Int)

    suspend fun rejectRequest(id: Int)
}