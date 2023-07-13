package com.mpersand.domain.usecase.order

import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class RequestResultUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(body: OrderRequestModel) = kotlin.runCatching { orderRepository.requestResult(body) }
}