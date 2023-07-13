package com.mpersand.data.dto.user.response

import com.mpersand.domain.model.user.response.UserResponseModel
import java.util.UUID

data class UserResponse(
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

fun UserResponse.asUserResponseModel() = UserResponseModel(
    id = id,
    email = email,
    name = name,
    grade = grade,
    classNum = classNum,
    number = number,
    profileUrl = profileUrl,
    role = role,
    isRentalRestricted = isRentalRestricted
)