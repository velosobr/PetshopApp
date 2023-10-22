package com.velosobr.petshopapp.data.repository

import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import kotlinx.coroutines.flow.Flow

interface ProductItemsRepository {
    suspend fun getProductItems(): List<ProductItem>
}