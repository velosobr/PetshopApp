package com.velosobr.petshopapp.data.repository

import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addItem(item: ProductItem): Int
    suspend fun removeItem(id: Int): Int
    suspend fun getProducts(): List<ProductItem>
}