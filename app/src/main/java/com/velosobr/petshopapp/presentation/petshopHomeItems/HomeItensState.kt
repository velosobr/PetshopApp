package com.velosobr.petshopapp.presentation.petshopHomeItems

import com.velosobr.petshopapp.domain.model.ProductItem

sealed class HomeItensState {
    object Loading : HomeItensState()
    data class Success(val items: List<ProductItem>) : HomeItensState()
    data class Error(val message: String) : HomeItensState()
}