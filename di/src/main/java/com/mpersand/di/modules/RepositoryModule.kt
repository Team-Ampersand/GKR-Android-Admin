package com.mpersand.di.modules

import com.mpersand.data.repository.AuthRepositoryImpl
import com.mpersand.data.repository.EquipmentRepositoryImpl
import com.mpersand.data.repository.OrderRepositoryImpl
import com.mpersand.data.repository.UserRepositoryImpl
import com.mpersand.data.repository.ViolationRepositoryImpl
import com.mpersand.domain.repository.AuthRepository
import com.mpersand.domain.repository.EquipmentRepository
import com.mpersand.domain.repository.OrderRepository
import com.mpersand.domain.repository.UserRepository
import com.mpersand.domain.repository.ViolationRepository
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

    @Binds
    fun bindsOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    fun bindsViolationRepository(
        violationRepositoryImpl: ViolationRepositoryImpl
    ): ViolationRepository

    @Binds
    fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
