package com.velosobr.petshopapp.presentation.petshopHomeItems

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.velosobr.petshopapp.ProductItemListFactory
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.usecase.AddProductItemToCartUseCase
import com.velosobr.petshopapp.usecase.GetPetShopProductUseCase
import com.velosobr.petshopapp.usecase.GetProductsFromLocalDataUseCase
import com.velosobr.petshopapp.usecase.RemoveProductItemToCartUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeItemsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HomeItemsViewModel
    private val getPetShopProductUseCase = mockk<GetPetShopProductUseCase>()
    private val addProductItemToCartUseCase = mockk<AddProductItemToCartUseCase>(relaxed = true)
    private val removeProductItemToCartUseCase = mockk<RemoveProductItemToCartUseCase>()
    private val getProductsFromLocalDataUseCase = mockk<GetProductsFromLocalDataUseCase>()

    private val factory = ProductItemListFactory()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = HomeItemsViewModel(
            getPetShopProductUseCase,
            addProductItemToCartUseCase,
            removeProductItemToCartUseCase,
            getProductsFromLocalDataUseCase
        )
    }

    @Test
    fun `fetchPetShopProductItems success`() = runBlocking {
        Dispatchers.setMain(testDispatcher)

        val productsList = factory.create()

        coEvery { getPetShopProductUseCase.invoke() } returns productsList

        val observer = spyk<Observer<HomeItemsState>>()
        viewModel.state.observeForever(observer)

        viewModel.fetchPetShopProductItems()

        coVerify { getPetShopProductUseCase.invoke() }
        verify { observer.onChanged(HomeItemsState.Success(productsList)) }

        viewModel.state.removeObserver(observer)
        Dispatchers.resetMain()

    }

    @Test
    fun `getCartQuantityItems success`() = runBlocking {
        Dispatchers.setMain(testDispatcher)
        val cartItems = factory.create()

        coEvery { getProductsFromLocalDataUseCase.invoke() } returns cartItems

        val observer = spyk<Observer<HomeItemsState>>()
        viewModel.state.observeForever(observer)

        viewModel.getCartQuantityItems()

        coVerify { getProductsFromLocalDataUseCase.invoke() }
        verify { observer.onChanged(HomeItemsState.CartItemsQuantity(cartItems.size)) }

        viewModel.state.removeObserver(observer)
        Dispatchers.resetMain()

    }
}
