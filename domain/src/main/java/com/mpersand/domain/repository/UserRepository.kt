package com.mpersand.domain.repository

import com.mpersand.domain.model.user.response.UserResponseModel

interface UserRepository {
    suspend fun logout()

    suspend fun getUser(): UserResponseModel
}