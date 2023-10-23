package com.mpersand.data.dto.order.response

import com.mpersand.domain.model.order.response.OrderApplicationListResponseModel

data class OrderApplicationListResponse(
    val applicationList: List<OrderDetailListResponse>
)

fun OrderApplicationListResponse.asOrderApplicationListResponseModel() = OrderApplicationListResponseModel(
    applicationList = applicationList.map { it.asOrderDetailListResponseModel() }
)