package com.mpersand.domain.usecase.repair

import com.mpersand.domain.repository.RepairRepository
import javax.inject.Inject

class GetRepairHistoryUseCase @Inject constructor(
    private val repository: RepairRepository
) {
    suspend operator fun invoke(productNumber: String) = kotlin.runCatching { repository.getRepairHistory(productNumber) }
}