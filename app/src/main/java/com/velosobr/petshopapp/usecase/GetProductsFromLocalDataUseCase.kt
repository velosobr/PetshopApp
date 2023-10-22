package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetProductsFromLocalDataUseCase {
    suspend fun invoke(): List<ProductItem>
}

class GetProductsFromLocalDataUsecaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : GetProductsFromLocalDataUseCase {
    override suspend fun invoke() =
        cartRepository.getProducts()
}