package com.mpersand.domain.model.user.response

import java.util.UUID

data class UserResponseModel(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val profileUrl: String?,
    val role: String,
    val isRentalRestricted: Boolean
)