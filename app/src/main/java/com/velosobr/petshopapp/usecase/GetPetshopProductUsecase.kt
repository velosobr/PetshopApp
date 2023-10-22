package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPetShopProductUseCase {
    suspend fun invoke(): List<ProductItem>
}

class GetPetShopProductUseCaseImpl @Inject constructor(
    private val productItemsRepository: ProductItemsRepository
) : GetPetShopProductUseCase {
    override suspend operator fun invoke() =
        productItemsRepository.getProductItems()

}
