package com.wiquert.onlinestore.viewmodel

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
    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val allProductsList : List<Product> = mainApi.getAllProducts().products
            for (eachProduct in allProductsList) {
                name.value = eachProduct.title
                description.value = eachProduct.description
                price.value = eachProduct.price.toString()
            }
        }
    }
}