package com.mpersand.data.dto.violation.request

import com.google.gson.annotations.SerializedName
import com.mpersand.domain.model.violation.request.ViolationRequestModel

data class ViolationRequest(
    @SerializedName("email") val email: String,
    @SerializedName("violationReason") val violationReason: String
)

fun ViolationRequestModel.asViolationRequest() = ViolationRequest(
    email = email,
    violationReason = violationReason
)