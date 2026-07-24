package com.smoothsm.cameraapp.data.di

import com.smoothsm.cameraapp.domain.usecase.CameraUseCase
import com.smoothsm.cameraapp.domain.usecase.CameraUseCaseImpl
import com.smoothsm.cameraapp.domain.usecase.ScanUseCase
import com.smoothsm.cameraapp.domain.usecase.ScanUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindCameraUseCase(impl: CameraUseCaseImpl): CameraUseCase

    @Binds
    @Singleton
    abstract fun bindScanUseCase(impl: ScanUseCaseImpl): ScanUseCase
}
