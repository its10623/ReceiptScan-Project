package com.smoothsm.cameraapp.data.di

import com.smoothsm.cameraapp.data.repository.CameraRepositoryImpl
import com.smoothsm.cameraapp.data.repository.ScanRepositoryImpl
import com.smoothsm.cameraapp.domain.repository.CameraRepository
import com.smoothsm.cameraapp.domain.repository.ScanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCameraRepository(impl: CameraRepositoryImpl): CameraRepository

    @Binds
    @Singleton
    abstract fun bindScanRepository(impl: ScanRepositoryImpl): ScanRepository
}
