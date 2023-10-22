package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import javax.inject.Inject

interface RemoveProductItemToCartUseCase {
    suspend fun invoke(id: Int): Int
}

class RemoveProductItemToCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository

) : RemoveProductItemToCartUseCase {
    override suspend fun invoke(id: Int) = cartRepository.removeItem(id)

}