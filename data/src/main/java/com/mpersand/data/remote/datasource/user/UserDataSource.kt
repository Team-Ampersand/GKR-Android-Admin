package com.mpersand.data.remote.datasource.user

import com.mpersand.data.dto.user.response.UserResponse

interface UserDataSource {
    suspend fun logout()

    suspend fun getUser(): UserResponse
}