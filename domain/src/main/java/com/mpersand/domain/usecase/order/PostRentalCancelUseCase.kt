package com.mpersand.domain.usecase.order

import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class PostRentalCancelUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        orderRepository.postRentalCancel(id)
    }
}