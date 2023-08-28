package com.mpersand.data.remote.datasource.user

import com.mpersand.data.dto.user.response.UserResponse
import com.mpersand.data.network.api.UserApi
import com.mpersand.data.remote.util.safeApiCall
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
): UserDataSource {
    override suspend fun getUser(): UserResponse = safeApiCall { userApi.getUser() }
}