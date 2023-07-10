package com.mpersand.data.repository

import com.mpersand.data.local.datasource.LocalDataSource
import com.mpersand.data.remote.datasource.user.UserDataSource
import com.mpersand.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val localDataSource: LocalDataSource
) : UserRepository {
    override suspend fun logout() {
        userDataSource.logout()
        localDataSource.logout()
    }
}