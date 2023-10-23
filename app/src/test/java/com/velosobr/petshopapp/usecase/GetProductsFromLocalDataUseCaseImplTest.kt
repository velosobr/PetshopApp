package com.velosobr.petshopapp.usecase

import com.velosobr.petshopapp.ProductItemListFactory
import com.velosobr.petshopapp.data.repository.CartRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProductsFromLocalDataUseCaseImplTest {

    private lateinit var cartRepository: CartRepository
    private lateinit var getProductsFromLocalDataUseCase: GetProductsFromLocalDataUseCase
    private val factory = ProductItemListFactory()

    @Before
    fun setup() {
        cartRepository = mockk()
        getProductsFromLocalDataUseCase = GetProductsFromLocalDataUseCaseImpl(cartRepository)
    }

    @Test
    fun `invoke returns cart products`() = runBlocking {
        val cartProducts = factory.create()
        coEvery { cartRepository.getProducts() } returns cartProducts

        val result = getProductsFromLocalDataUseCase.invoke()

        assert(result == cartProducts)
    }

    @Test
    fun `invoke returns an empty list when no cart products are available`() = runBlocking {
        coEvery { cartRepository.getProducts() } returns emptyList()

        val result = getProductsFromLocalDataUseCase.invoke()

        assert(result.isEmpty())
    }
}