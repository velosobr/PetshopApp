package com.velosobr.petshopapp.presentation.petshopItemDetails

import com.velosobr.petshopapp.domain.model.ProductItem

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val items: List<ProductItem>) : DetailsState()
    data class Error(val message: String) : DetailsState()
}