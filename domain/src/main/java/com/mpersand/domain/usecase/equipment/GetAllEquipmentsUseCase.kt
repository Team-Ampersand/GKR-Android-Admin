package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetAllEquipmentsUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { repository.getAllEquipments() }
}