package com.mpersand.domain.usecase.repair

import com.mpersand.domain.model.repair.request.RepairRequestModel
import com.mpersand.domain.repository.RepairRepository
import javax.inject.Inject

class AddRepairHistoryUseCase @Inject constructor(
    private val repository: RepairRepository
) {
    suspend operator fun invoke(body: RepairRequestModel) = kotlin.runCatching { repository.addRepairHistory(body) }
}