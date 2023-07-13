package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetNotRentedEquipmentsUseCase @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { equipmentRepository.getNotRentedEquipments() }
}