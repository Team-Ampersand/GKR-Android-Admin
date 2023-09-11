package com.mpersand.data.dto.violation.request

import com.mpersand.domain.model.violation.request.ViolationRequestModel
import java.util.UUID

data class ViolationRequest(
    val email: String,
    val violationReason: String
)

fun ViolationRequestModel.asViolationRequest() = ViolationRequest(
    email = email,
    violationReason = violationReason
)