package com.mpersand.domain.repository

import com.mpersand.domain.model.violation.request.ViolationRequestModel

interface ViolationRepository {
    suspend fun postViolationUser(violationRequestModel: ViolationRequestModel)
}