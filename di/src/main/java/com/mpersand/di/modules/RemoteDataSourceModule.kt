package com.mpersand.di.modules

import com.mpersand.data.remote.datasource.auth.AuthDataSource
import com.mpersand.data.remote.datasource.auth.AuthDataSourceImpl
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSourceImpl
import com.mpersand.data.remote.datasource.order.OrderDataSource
import com.mpersand.data.remote.datasource.order.OrderDataSourceImpl
import com.mpersand.data.remote.datasource.repair.RepairDataSource
import com.mpersand.data.remote.datasource.repair.RepairDataSourceImpl
import com.mpersand.data.remote.datasource.user.UserDataSource
import com.mpersand.data.remote.datasource.user.UserDataSourceImpl
import com.mpersand.data.remote.datasource.violation.ViolationDataSource
import com.mpersand.data.remote.datasource.violation.ViolationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    fun bindsAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    fun bindsEquipmentDataSource(
        equipmentDataSourceImpl: EquipmentDataSourceImpl
    ): EquipmentDataSource

    @Binds
    fun bindsRepairDataSource(
        repairDataSourceImpl: RepairDataSourceImpl
    ): RepairDataSource

    @Binds
    fun bindsOrderDataSource(
        orderDataSourceImpl: OrderDataSourceImpl
    ): OrderDataSource

    @Binds
    fun bindsViolationDataSource(
        violationDataSourceImpl: ViolationDataSourceImpl
    ): ViolationDataSource

    @Binds
    fun bindsUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource
}