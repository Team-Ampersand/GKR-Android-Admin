package com.mpersand.data.repository

import com.mpersand.data.dto.order.response.asOrderApplicationListResponseModel
import com.mpersand.data.dto.order.response.asOrderDetailListResponseModel
import com.mpersand.data.dto.order.response.asOrderEquipmentListResponseModel
import com.mpersand.data.remote.datasource.order.OrderDataSource
import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel
import com.mpersand.domain.model.order.response.OrderEquipmentListResponseModel
import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDataSource: OrderDataSource
): OrderRepository {
    override suspend fun getSelfStateList(): OrderEquipmentListResponseModel =
        orderDataSource.getSelfStateList().asOrderEquipmentListResponseModel()

    override suspend fun getNowRentalList(): OrderApplicationListResponseModel =
        orderDataSource.getNowRentalList().asOrderApplicationListResponseModel()

    override suspend fun getNoReturnList(): OrderApplicationListResponseModel =
        orderDataSource.getNoReturnList().asOrderApplicationListResponseModel()

    override suspend fun getWaitList(): OrderApplicationListResponseModel =
        orderDataSource.getWaitList().asOrderApplicationListResponseModel()

    override suspend fun getRentalRequestDetail(id: String) =
        orderDataSource.getRentalRequestDetail(id).asOrderDetailListResponseModel()

    override suspend fun postRental(id: Int, response: String) =
        orderDataSource.postRental(id, response)

    override suspend fun postReturn(id: Int) =
        orderDataSource.postReturn(id)

    override suspend fun postExtension(id: Int, response: String) =
        orderDataSource.postExtension(id, response)

    override suspend fun postRentalCancel(id: Int) =
        orderDataSource.postRentalCancel(id)

    override suspend fun acceptRequest(id: Int) =
        orderDataSource.acceptRequest(id)

    override suspend fun rejectRequest(id: Int) =
        orderDataSource.rejectRequest(id)

}