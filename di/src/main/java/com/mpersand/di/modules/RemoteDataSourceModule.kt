package com.mpersand.di.modules

import com.mpersand.data.remote.datasource.auth.AuthDataSource
import com.mpersand.data.remote.datasource.auth.AuthDataSourceImpl
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSourceImpl
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
}