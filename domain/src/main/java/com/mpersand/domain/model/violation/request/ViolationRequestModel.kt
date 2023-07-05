package com.mpersand.domain.model.violation.request

import java.util.UUID

data class ViolationRequestModel(
    val userId: UUID,
    val reason: String
)
