package com.wiquert.onlinestore.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiquert.onlinestore.retrofit.MainApi
import com.wiquert.onlinestore.retrofit.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainApi: MainApi) : ViewModel() {

    private val _products = mutableStateOf<List<Product>>(emptyList())
    private val _interestingProducts = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products
    val interestingProducts: State<List<Product>> = _interestingProducts
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val allProductsList : List<Product> = mainApi.getAllProducts().products
            _products.value = allProductsList
            _interestingProducts.value = allProductsList.shuffled().take(15)
        }
    }
}