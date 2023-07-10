package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.model.equipment.request.EquipmentRequestModel
import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class ModifyEquipmentUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(productNumber: String, body: EquipmentRequestModel) =
        kotlin.runCatching { repository.modifyEquipment(productNumber, body) }
}