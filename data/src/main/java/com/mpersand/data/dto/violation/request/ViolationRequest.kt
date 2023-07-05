package com.mpersand.data.dto.violation.request

import com.mpersand.domain.model.violation.request.ViolationRequestModel
import java.util.UUID

data class ViolationRequest(
    val userId: UUID,
    val reason: String
)

fun ViolationRequestModel.asViolationRequest() = ViolationRequest(
    userId = userId,
    reason = reason
)