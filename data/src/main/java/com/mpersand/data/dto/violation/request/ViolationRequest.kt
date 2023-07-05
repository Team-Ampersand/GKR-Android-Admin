package com.mpersand.data.dto.violation.request

import java.util.UUID

data class ViolationRequest(
    val userId: UUID,
    val reason: String
)
