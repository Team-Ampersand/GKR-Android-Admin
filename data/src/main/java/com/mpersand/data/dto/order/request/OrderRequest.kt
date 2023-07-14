package com.mpersand.data.dto.order.request

import com.mpersand.domain.model.order.request.OrderRequestModel

data class OrderRequest(
    val equipmentId: String,
    val decision: String
)

fun OrderRequestModel.asOrderRequest() = OrderRequest(
    equipmentId = this.equipmentId,
    decision = this.decision
)
