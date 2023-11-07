package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.response.OrderApplicationListResponse
import com.mpersand.data.dto.order.response.OrderEquipmentListResponse
import com.mpersand.data.network.api.OrderApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderApi: OrderApi
): OrderDataSource {
    override suspend fun getSelfStateList(): OrderEquipmentListResponse = safeApiCall {
        orderApi.getSelfStateList()
    }

    override suspend fun getNowRentalList(): OrderApplicationListResponse = safeApiCall {
        orderApi.getNowRentalList()
    }

    override suspend fun getNoReturnList(): OrderApplicationListResponse = safeApiCall {
        orderApi.getNoReturnList()
    }

    override suspend fun getWaitList(): OrderApplicationListResponse = safeApiCall {
        orderApi.getWaitList()
    }

    override suspend fun getRentalRequestDetail(id: String) = safeApiCall {
        orderApi.getRentRequestDetail(id)
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