package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetEquipmentsByStateUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(equipmentStatus: String) = kotlin.runCatching { repository.getEquipmentsByState(equipmentStatus) }
}