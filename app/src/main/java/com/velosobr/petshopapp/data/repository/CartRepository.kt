package com.velosobr.petshopapp.data.repository

import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun AddItem(item: ProductItem)
    suspend fun RemoveItem(id: String)
    suspend fun getProducts(): List<ProductItem>
}