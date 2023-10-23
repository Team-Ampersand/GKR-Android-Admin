package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.response.OrderDetailListResponse
import com.mpersand.data.dto.order.response.OrderListResponse
import com.mpersand.data.dto.order.response.OrderResponse
import com.mpersand.data.dto.order.response.WaitListResponse
import com.mpersand.data.network.api.OrderApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderApi: OrderApi
): OrderDataSource {
    override suspend fun getSelfStateList(): List<OrderListResponse> = safeApiCall {
        orderApi.getSelfStateList()
    }

    override suspend fun getNowRentalList(): List<OrderDetailListResponse> = safeApiCall {
        orderApi.getNowRentalList()
    }

    override suspend fun getNoReturnList(): List<OrderDetailListResponse> = safeApiCall {
        orderApi.getNoReturnList()
    }

    override suspend fun getWaitList(): List<OrderDetailListResponse> = safeApiCall {
        orderApi.getWaitList()
    }

    override suspend fun postRental(id: Int, response: String) = safeApiCall {
        orderApi.postRental(id, response)
    }

    override suspend fun postReturn(id: Int) = safeApiCall {
        orderApi.postReturn(id)
    }

    override suspend fun postExtension(id: Int, response: String) = safeApiCall {
        orderApi.postExtension(id, response)
    }

    override suspend fun postRentalCancel(id: Int) = safeApiCall {
        orderApi.postRentalCancel(id)
    }

    override suspend fun acceptRequest(id: Int) = safeApiCall {
        orderApi.acceptRequest(id)
    }

    override suspend fun rejectRequest(id: Int) = safeApiCall {
        orderApi.rejectRequest(id)
    }

}