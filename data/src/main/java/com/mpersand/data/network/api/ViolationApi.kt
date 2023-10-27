package com.mpersand.data.network.api

import com.mpersand.data.dto.violation.request.ViolationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ViolationApi {
    @POST("violation")
    suspend fun postViolationUser(
        @Body body: ViolationRequest
    )
}