package com.velosobr.petshopapp.framework

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.velosobr.petshopapp.data.repository.CartRepository
import com.velosobr.petshopapp.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import okio.IOException
import java.lang.reflect.Type
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CartRepository {
    private val cartsKey = stringPreferencesKey(CARTS_KEY)
    private suspend fun initCartsDataStore() {
        dataStore.edit { preferences ->
            preferences[cartsKey] = Gson().toJson(listOf<ProductItem>())
        }
    }

    override suspend fun addItem(item: ProductItem): Int {
        val products = getProducts().toMutableList()
        products.add(item)

        dataStore.edit { preferences ->
            preferences[cartsKey] = Gson().toJson(products)
        }
        return products.size
    }

    override suspend fun removeItem(id: Int): Int {
        val products = getProducts().toMutableList().apply {
            val product = firstOrNull { it.id == id }
            if (product != null) {
                remove(product)
            }
        }

        dataStore.edit { preferences ->
            preferences[cartsKey] = Gson().toJson(products)
        }
        return products.size
    }

    override suspend fun getProducts(): List<ProductItem> {
        val preferences = dataStore.data.first()

//        { exception ->
//            if (exception is IOException) {
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }.map { preferences ->

        val localProducts = preferences[cartsKey]
        return if (localProducts.isNullOrEmpty()) {
            initCartsDataStore()
            emptyList()
        } else {
            val result: ArrayList<ProductItem> = jsonToProducts(localProducts)
            result.toList()
        }
    }


    private fun jsonToProducts(localProducts: String): ArrayList<ProductItem> {
        val type = object : TypeToken<ArrayList<ProductItem>>() {}.type
        return parseArray(
            json = localProducts, typeToken = type
        )
    }

    private inline fun <reified T> parseArray(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson(json, typeToken)
    }

    companion object {
        private const val CARTS_KEY = "carts_key"
    }

}
