package com.mpersand.domain.repository

interface UserRepository {
    suspend fun logout()
}