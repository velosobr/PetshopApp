package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPetshopProductUsecase {
    suspend fun invoke(): List<ProductItem>
}

class GetPetshopProductUsecaseImpl @Inject constructor(
    private val productItemsRepository: ProductItemsRepository
): GetPetshopProductUsecase {
    override suspend operator fun invoke(): List<ProductItem> {
        val itemList: List<ProductItem> = productItemsRepository.getProductItems()
        return itemList
    }

}
