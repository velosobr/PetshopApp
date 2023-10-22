package com.velosobr.petshopapp.framework.network.response

import com.velosobr.petshopapp.domain.model.ProductItem

data class ProductResponse(
  val id: Int,
  val description: String,
  val weight: String,
  val quantity: Int,
  val amount: String,
  val imageUrl: String
)

fun ProductResponse.toProductItemModel(): ProductItem {
  return ProductItem(
    id = this.id,
    description = this.description,
    weight = weight,
    quantity = quantity,
    amount = amount,
    imageUrl = imageUrl
  )
}