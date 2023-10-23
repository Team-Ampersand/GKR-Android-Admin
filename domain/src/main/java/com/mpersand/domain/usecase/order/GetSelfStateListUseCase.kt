package com.mpersand.domain.usecase.order

import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class GetSelfStateListUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        orderRepository.getSelfStateList()
    }
}