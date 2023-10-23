package com.velosobr.petshopapp.presentation.petshopItemDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductItemDetailsViewModel @Inject constructor(

) : ViewModel() {
    private var _state = MutableLiveData<DetailsState>(DetailsState.Error(""))
    val state: LiveData<DetailsState>
        get() = _state

}
