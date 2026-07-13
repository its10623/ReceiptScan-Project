package com.example.cameraapp.data.di

import com.example.cameraapp.data.repository.CameraRepositoryImpl
import com.example.cameraapp.data.repository.ScanRepositoryImpl
import com.example.cameraapp.domain.repository.CameraRepository
import com.example.cameraapp.domain.repository.ScanRepository
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
