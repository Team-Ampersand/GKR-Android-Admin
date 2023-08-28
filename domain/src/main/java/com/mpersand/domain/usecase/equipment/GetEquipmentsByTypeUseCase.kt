package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetEquipmentsByTypeUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(equipmentType: String) = kotlin.runCatching { repository.getEquipmentsByType(equipmentType) }
}