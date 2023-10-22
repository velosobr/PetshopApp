package com.velosobr.petshopapp.framework.di

import com.velosobr.petshopapp.usecase.GetPetshopProductUsecase
import com.velosobr.petshopapp.usecase.GetPetshopProductUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetPetshopProductUsecase(useCase: GetPetshopProductUsecaseImpl): GetPetshopProductUsecase
}