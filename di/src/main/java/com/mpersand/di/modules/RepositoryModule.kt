package com.mpersand.di.modules

import com.mpersand.data.repository.AuthRepositoryImpl
import com.mpersand.data.repository.EquipmentRepositoryImpl
import com.mpersand.domain.repository.AuthRepository
import com.mpersand.domain.repository.EquipmentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsEquipmentRepository(
        equipmentRepositoryImpl: EquipmentRepositoryImpl
    ): EquipmentRepository
}