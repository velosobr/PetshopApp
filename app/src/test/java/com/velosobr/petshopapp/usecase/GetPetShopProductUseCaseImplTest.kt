package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.ProductItemListFactory
import com.velosobr.petshopapp.data.repository.ProductItemsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPetShopProductUseCaseImplTest {

    private lateinit var productItemsRepository: ProductItemsRepository
    private lateinit var getPetShopProductUseCase: GetPetShopProductUseCase
    private val factory = ProductItemListFactory()

    @Before
    fun setup() {
        productItemsRepository = mockk()
        getPetShopProductUseCase = GetPetShopProductUseCaseImpl(productItemsRepository)
    }

    @Test
    fun `invoke returns product items`() = runBlocking {
        val productItems = factory.create()
        coEvery { productItemsRepository.getProductItems() } returns productItems

        val result = getPetShopProductUseCase.invoke()

        assert(result == productItems)
    }

    @Test
    fun `invoke returns an empty list when no products are available`() = runBlocking {
        coEvery { productItemsRepository.getProductItems() } returns emptyList()

        val result = getPetShopProductUseCase.invoke()

        assert(result.isEmpty())
    }
}