package com.velosobr.petshopapp.framework

import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.framework.network.PetshopApiService
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import com.velosobr.petshopapp.framework.network.response.toProductItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject

/**
 * Depende de uma abstração e não de uma implementação
 */
class ProductItemsRepositoryImpl @Inject constructor(
    private val petShopApiService: PetshopApiService
) : ProductItemsRepository {
    override suspend fun getProductItems(): List<ProductItem> =
        try {
            petShopApiService
                .getPetshopItems()
                .productList
                .map {
                    it.toProductItemModel()
                }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf<ProductItem>()
        }
}
