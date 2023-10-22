package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import javax.inject.Inject

interface AddProductItemToCartUseCase {
    suspend fun invoke(item: ProductItem): Int
}

class AddProductItemToCartUsecaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : AddProductItemToCartUseCase {
    override suspend fun invoke(item: ProductItem) =
        cartRepository.addItem(item)
}