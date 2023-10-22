package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import javax.inject.Inject

interface RemoveProductItemToCartUsecase {
    suspend fun invoke(id: String)
}

class RemoveProductItemToCartUsecaseImpl @Inject constructor(
    private val cartRepository: CartRepository

):RemoveProductItemToCartUsecase{
    override suspend fun invoke(id: String) {
        TODO("Not yet implemented")
    }

}