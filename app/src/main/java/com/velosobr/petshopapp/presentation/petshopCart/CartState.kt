package com.velosobr.petshopapp.presentation.petshopCart

import com.velosobr.petshopapp.domain.model.ProductItem

sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<ProductItem>) : CartState()
    data class Share(val items: List<ProductItem>) : CartState()
    data class Error(val message: String) : CartState()
}