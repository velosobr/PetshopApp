package com.velosobr.petshopapp.framework.network

import com.velosobr.petshopapp.framework.network.response.DataWrapperResponse
import retrofit2.http.GET

interface PetshopApiService {

    @GET("v3/039423ea-9e2b-423e-829e-7b5c789a9703")
    suspend fun getPetshopItems(): DataWrapperResponse
}