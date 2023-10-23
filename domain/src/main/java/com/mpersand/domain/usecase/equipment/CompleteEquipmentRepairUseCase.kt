package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class CompleteEquipmentRepairUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(produceNumber: String) = kotlin.runCatching { repository.completeEquipmentRepair(produceNumber) }
}
