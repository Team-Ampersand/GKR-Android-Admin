package com.mpersand.domain.usecase.repair

import com.mpersand.domain.model.repair.request.RepairRequestModel
import com.mpersand.domain.repository.RepairRepository
import javax.inject.Inject

class ModifyRepairHistoryUseCase @Inject constructor(
    private val repository: RepairRepository
) {
    suspend operator fun invoke(body: RepairRequestModel) = repository.modifyRepairHistory(body)
}