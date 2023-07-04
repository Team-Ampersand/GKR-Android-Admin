package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetEquipmentsByFilterUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(name: String) = kotlin.runCatching { repository.getEquipmentsByFilter(name) }
}