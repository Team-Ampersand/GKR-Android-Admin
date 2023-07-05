package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetEquipmentDetailUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(productNumber: String) = kotlin.runCatching { repository.getEquipmentDetail(productNumber) }
}