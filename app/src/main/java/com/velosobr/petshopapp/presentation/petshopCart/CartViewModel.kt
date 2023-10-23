package com.velosobr.petshopapp.presentation.petshopCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.presentation.petshopHomeItems.HomeItemsState
import com.velosobr.petshopapp.usecase.AddProductItemToCartUseCase
import com.velosobr.petshopapp.usecase.GetPetShopProductUseCase
import com.velosobr.petshopapp.usecase.GetProductsFromLocalDataUseCase
import com.velosobr.petshopapp.usecase.RemoveProductItemToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductsFromLocalDataUseCase: GetProductsFromLocalDataUseCase
) : ViewModel() {

    private var _state = MutableLiveData<CartState>(CartState.Loading)
    val state: LiveData<CartState>
        get() = _state

    fun fetchCartProductItems() = viewModelScope.launch {
        runCatching {
            _state.value = CartState.Success( getProductsFromLocalDataUseCase.invoke())
        }.onFailure {
            _state.value = CartState.Error(it.message.orEmpty())
        }
    }
}