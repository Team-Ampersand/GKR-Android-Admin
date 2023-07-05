package com.mpersand.data.remote.datasource.violation

import com.mpersand.data.dto.violation.request.ViolationRequest

interface ViolationDataSource {
    suspend fun postViolationUser(violationRequest: ViolationRequest)
}