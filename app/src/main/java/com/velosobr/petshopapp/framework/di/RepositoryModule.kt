package com.velosobr.petshopapp.framework.di

import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.framework.CartRepositoryImpl
import com.velosobr.petshopapp.framework.ProductItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindProductItemsRepository(repository: ProductItemsRepositoryImpl): ProductItemsRepository

    @Binds
    fun bindCartRepository(repository: CartRepositoryImpl): CartRepository

}