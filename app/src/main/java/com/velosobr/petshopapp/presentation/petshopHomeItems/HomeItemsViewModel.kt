package com.velosobr.petshopapp.presentation.petshopHomeItems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.usecase.AddProductItemToCartUseCase
import com.velosobr.petshopapp.usecase.GetPetShopProductUseCase
import com.velosobr.petshopapp.usecase.GetProductsFromLocalDataUseCase
import com.velosobr.petshopapp.usecase.RemoveProductItemToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeItemsViewModel @Inject constructor(
    private val getPetShopProductUseCase: GetPetShopProductUseCase,
    private val addProductItemToCartUseCase: AddProductItemToCartUseCase,
    private val removeProductItemToCartUseCase: RemoveProductItemToCartUseCase,
    private val getProductsFromLocalDataUseCase: GetProductsFromLocalDataUseCase
) : ViewModel() {

    private var _state = MutableLiveData<HomeItemsState>(HomeItemsState.Loading)
    val state: LiveData<HomeItemsState>
        get() = _state


    fun fetchPetShopProductItems() = viewModelScope.launch {
        runCatching {
            _state.value = HomeItemsState.Success(getPetShopProductUseCase.invoke())
        }.onFailure {
            _state.value = HomeItemsState.Error(it.message.orEmpty())
        }
    }

    private fun addItemToCart(itemProductItem: ProductItem) = viewModelScope.launch {
        runCatching {
            val count = addProductItemToCartUseCase.invoke(itemProductItem)
            _state.value = HomeItemsState.CartItemAdded(count)
        }.onFailure {
            _state.value = HomeItemsState.CartError(it.message.orEmpty())
        }
    }

    private fun removeItemToCart(id: Int) = viewModelScope.launch {
        runCatching {
            val count = removeProductItemToCartUseCase.invoke(id)
            _state.value = HomeItemsState.CartItemRemoved(count)
        }.onFailure {
            _state.value = HomeItemsState.CartError(it.message.orEmpty())

        }
    }

    fun getProductsFromLocalData() = viewModelScope.launch {
        getProductsFromLocalDataUseCase.invoke()
    }

    fun updateCart(productItem: ProductItem) {
        if (productItem.isAddedToCart) {
            addItemToCart(productItem)
        } else {
            removeItemToCart(productItem.id)
        }
    }
}