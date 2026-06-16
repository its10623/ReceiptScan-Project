package com.example.cameraapp.data.di

import com.example.cameraapp.domain.usecase.CameraUseCase
import com.example.cameraapp.domain.usecase.CameraUseCaseImpl
import com.example.cameraapp.domain.usecase.ReceiptUseCase
import com.example.cameraapp.domain.usecase.ReceiptUseCaseImpl
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
    abstract fun bindCameraUseCase(
        impl: CameraUseCaseImpl
    ): CameraUseCase

    @Binds
    @Singleton
    abstract fun bindReceiptUseCase(
        impl: ReceiptUseCaseImpl
    ): ReceiptUseCase
}
