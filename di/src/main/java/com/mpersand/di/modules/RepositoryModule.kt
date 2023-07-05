package com.mpersand.di.modules

import com.mpersand.data.repository.AuthRepositoryImpl
import com.mpersand.data.repository.EquipmentRepositoryImpl
import com.mpersand.data.repository.OrderRepositoryImpl
import com.mpersand.data.repository.RepairRepositoryImpl
import com.mpersand.data.repository.ViolationRepositoryImpl
import com.mpersand.domain.repository.AuthRepository
import com.mpersand.domain.repository.EquipmentRepository
import com.mpersand.domain.repository.OrderRepository
import com.mpersand.domain.repository.RepairRepository
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
    fun bindsRepairRepository(
        repairRepositoryImpl: RepairRepositoryImpl
    ): RepairRepository

    @Binds
    fun bindsOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    fun bindsViolationRepository(
        violationRepositoryImpl: ViolationRepositoryImpl
    ): ViolationRepository
}