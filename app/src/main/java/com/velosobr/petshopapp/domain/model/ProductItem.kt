package com.velosobr.petshopapp.domain.model

data class ProductItem(
    val id: Int,
    val description: String,
    val weight: String,
    val quantity: Int,
    val amount: String,
    val imageUrl: String,
    val isAddedToCart: Boolean = false
)