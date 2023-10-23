package com.mpersand.data.dto.order.response

import com.mpersand.domain.model.order.response.OrderEquipmentListResponseModel

data class OrderEquipmentListResponse(
    val orderEquipmentList: List<OrderListResponse>
)

fun OrderEquipmentListResponse.asOrderEquipmentListResponseModel() = OrderEquipmentListResponseModel(
    orderEquipmentList = orderEquipmentList.map { it.asOrderListResponseModel() }
)