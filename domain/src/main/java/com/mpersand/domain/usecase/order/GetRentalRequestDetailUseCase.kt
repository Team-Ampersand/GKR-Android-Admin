package com.mpersand.domain.usecase.order

import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class GetRentalRequestDetailUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(id: String) = kotlin.runCatching { orderRepository.getRentalRequestDetail(id) }
}