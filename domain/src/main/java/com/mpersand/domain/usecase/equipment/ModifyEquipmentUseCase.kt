package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ModifyEquipmentUseCase @Inject constructor(
    private val repository: EquipmentRepository
) {
    suspend operator fun invoke(file: MultipartBody.Part, equipment: HashMap<String, RequestBody>) =
        kotlin.runCatching { repository.modifyEquipment(file, equipment) }
}