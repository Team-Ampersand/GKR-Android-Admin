package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class EquipmentFilterUseCase @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) {
    suspend operator fun invoke(name: String) = kotlin.runCatching { equipmentRepository.getEquipmentsByFilter(name) }
}