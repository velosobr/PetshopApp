package com.velosobr.petshopapp.framework

import com.velosobr.petshopapp.framework.network.PetshopApiService
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import com.velosobr.petshopapp.framework.network.response.ProductResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductItemsRepositoryImplTest {

    private lateinit var petShopApiService: PetshopApiService
    private lateinit var productItemsRepository: ProductItemsRepositoryImpl

    @Before
    fun setup() {
        petShopApiService = mockk()
        productItemsRepository = ProductItemsRepositoryImpl(petShopApiService)
    }

    @Test
    fun `getProductItems returns a list of ProductItem`() = runBlocking {
        val petShopItemsResponse = DataWrapperResponse(
            productList = listOf(
                ProductResponse(
                    id = 1,
                    description = "Escova para pet",
                    weight = "500gr",
                    quantity = 1,
                    amount = "10,99",
                    imageUrl = "https://www.petlove.com.br/images/products/231062/product/Escova_Furminator_New_C%C3%A3es_Pelo_Longo_2316474_2_G.jpg?1627741260"
                ),
                ProductResponse(
                    id = 2,
                    description = "Tapete Higiênico Super Secão Citrus - 30 Unidades",
                    weight = "200gr",
                    quantity = 30,
                    amount = "9,99",
                    imageUrl = "https://www.petlove.com.br/images/products/260949/product/Tapete_Higi%C3%AAnico_Super_Sec%C3%A3o_Citrus_3104451_%281%29.jpg?1660325640"
                ),
            )
        )
        coEvery { petShopApiService.getPetshopItems() } returns petShopItemsResponse

        val result = productItemsRepository.getProductItems()

        assert(result.size == 2)
        assert(result[0].id == 1)
        assert(result[0].description == "Escova para pet")
        assert(result[0].amount == "10,99")
        assert(result[1].id == 2)
        assert(result[1].description == "Tapete Higiênico Super Secão Citrus - 30 Unidades")
        assert(result[1].amount == "9,99")
    }

    @Test
    fun `getProductItems returns an empty list when an exception is thrown`() = runBlocking {
        coEvery { petShopApiService.getPetshopItems() } throws Exception("An error occurred")

        val result = productItemsRepository.getProductItems()

        assert(result.isEmpty())
    }
}
