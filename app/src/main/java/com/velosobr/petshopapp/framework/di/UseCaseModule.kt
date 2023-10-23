package com.velosobr.petshopapp.framework.di

import com.velosobr.petshopapp.usecase.AddProductItemToCartUseCase
import com.velosobr.petshopapp.usecase.AddProductItemToCartUsecaseImpl
import com.velosobr.petshopapp.usecase.GetPetShopProductUseCase
import com.velosobr.petshopapp.usecase.GetPetShopProductUseCaseImpl
import com.velosobr.petshopapp.usecase.GetProductsFromLocalDataUseCase
import com.velosobr.petshopapp.usecase.GetProductsFromLocalDataUseCaseImpl
import com.velosobr.petshopapp.usecase.RemoveProductItemToCartUseCase
import com.velosobr.petshopapp.usecase.RemoveProductItemToCartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetPetShopProductUseCase(useCase: GetPetShopProductUseCaseImpl): GetPetShopProductUseCase

    @Binds
    fun bindAddProductItemToCartUseCase(useCase: AddProductItemToCartUsecaseImpl): AddProductItemToCartUseCase

    @Binds
    fun bindRemoveProductItemToCartUseCase(useCase: RemoveProductItemToCartUseCaseImpl): RemoveProductItemToCartUseCase

    @Binds
    fun bindGetProductsFromLocalDataUseCase(useCase: GetProductsFromLocalDataUseCaseImpl): GetProductsFromLocalDataUseCase

}