package com.mpersand.data.repository

import com.mpersand.data.dto.user.response.asUserResponseModel
import com.mpersand.data.local.datasource.LocalDataSource
import com.mpersand.data.remote.datasource.user.UserDataSource
import com.mpersand.domain.model.user.response.UserResponseModel
import com.mpersand.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val localDataSource: LocalDataSource
) : UserRepository {
    override suspend fun getUser(): UserResponseModel = userDataSource.getUser().asUserResponseModel()
}