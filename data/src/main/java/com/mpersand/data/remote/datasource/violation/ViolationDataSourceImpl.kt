package com.mpersand.data.remote.datasource.violation

import com.mpersand.data.dto.violation.request.ViolationRequest
import com.mpersand.data.network.api.ViolationApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class ViolationDataSourceImpl @Inject constructor(
    private val violationApi: ViolationApi
): ViolationDataSource {
    override suspend fun postViolationUser(violationRequest: ViolationRequest) = safeApiCall { violationApi.postViolationUser(violationRequest) }
}