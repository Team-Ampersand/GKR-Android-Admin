package com.mpersand.data.repository

import com.mpersand.data.dto.violation.request.asViolationRequest
import com.mpersand.data.remote.datasource.violation.ViolationDataSource
import com.mpersand.domain.model.violation.request.ViolationRequestModel
import com.mpersand.domain.repository.ViolationRepository
import javax.inject.Inject

class ViolationRepositoryImpl @Inject constructor(
    private val violationDataSource: ViolationDataSource
): ViolationRepository {
    override suspend fun postViolationUser(violationRequestModel: ViolationRequestModel) =
        violationDataSource.postViolationUser(violationRequestModel.asViolationRequest())
}