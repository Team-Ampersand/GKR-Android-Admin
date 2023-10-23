package com.mpersand.domain.usecase.order

import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class PostExtensionUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(id: Int, response: String) = kotlin.runCatching {
        orderRepository.postExtension(id, response)
    }
}