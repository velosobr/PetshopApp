package com.velosobr.petshopapp.presentation.petshopHomeItems

import com.velosobr.petshopapp.domain.model.ProductItem

sealed class HomeItemsState {
    object Loading : HomeItemsState()
    data class Success(val items: List<ProductItem>) : HomeItemsState()
    data class Error(val message: String) : HomeItemsState()
    data class CartError(val message: String) : HomeItemsState()
    data class CartItemAdded(val count: Int): HomeItemsState()
    data class CartItemRemoved(val count: Int): HomeItemsState()
    data class CartItemsQuantity(val count: Int): HomeItemsState()
}