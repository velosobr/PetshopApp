package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import javax.inject.Inject

interface AddProductItemToCartUsecase {
    suspend fun invoke(item: ProductItem)
}

class AddProductItemToCartUsecaseImpl @Inject constructor(
    private val cartRepository: CartRepository

): AddProductItemToCartUsecase{
    override suspend fun invoke(item: ProductItem) {
        TODO("Not yet implemented")
    }

}