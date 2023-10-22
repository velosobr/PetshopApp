package com.velosobr.petshopapp.presentation.petshopHomeItems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.petshopapp.domain.model.ProductItem
import com.velosobr.petshopapp.usecase.GetPetshopProductUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeItemsViewModel @Inject constructor(
    private val getPetshopProductUsecase: GetPetshopProductUsecase
) : ViewModel() {

    private var _state = MutableLiveData<HomeItensState>(HomeItensState.Loading)
    val state: LiveData<HomeItensState>
        get() = _state

    fun fetchPetshopProductItems() = viewModelScope.launch {
        runCatching {
            _state.value = HomeItensState.Success(getPetshopProductUsecase.invoke())
        }.onFailure {
            _state.value = HomeItensState.Error(it.message.orEmpty())
        }
    }

    fun addItemToCart() = viewModelScope.launch {  }
    fun removeItemToCart() = viewModelScope.launch {  }
}