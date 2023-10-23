package com.velosobr.petshopapp.presentation.petshopCart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.velosobr.petshopapp.ProductItemListFactory
import com.velosobr.petshopapp.usecase.GetProductsFromLocalDataUseCase
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CartViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: CartViewModel
    private val getProductsFromLocalDataUseCase =
        mockk<GetProductsFromLocalDataUseCase>(relaxed = true)
    private val factory = ProductItemListFactory()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = CartViewModel(getProductsFromLocalDataUseCase)

    }

    @Test
    fun `fetchCartProductItems success`() = runBlocking {
        Dispatchers.setMain(testDispatcher)

        val productsList = factory.create()
        coEvery { getProductsFromLocalDataUseCase.invoke() } returns productsList
        val observer = spyk<Observer<CartState>>()
        viewModel.state.observeForever(observer)
        viewModel.fetchCartProductItems()
        coVerify { getProductsFromLocalDataUseCase.invoke() }
        verify {
            observer.onChanged(CartState.Success(productsList))
        }
        viewModel.state.removeObserver(observer)
        Dispatchers.resetMain()


    }

    @Test
    fun `fetchCartProductItems error`() = runBlocking {
        Dispatchers.setMain(testDispatcher)

        val errorMessage = "An error occurred"

        coEvery { getProductsFromLocalDataUseCase.invoke() } throws Exception(errorMessage)

        val observer = spyk<Observer<CartState>>()
        viewModel.state.observeForever(observer)

        viewModel.fetchCartProductItems()

        coVerify { getProductsFromLocalDataUseCase.invoke() }
        verify { observer.onChanged(CartState.Error(errorMessage)) }

        viewModel.state.removeObserver(observer)
        Dispatchers.resetMain()

    }
}