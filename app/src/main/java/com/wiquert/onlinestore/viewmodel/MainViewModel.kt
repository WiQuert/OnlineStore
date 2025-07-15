package com.wiquert.onlinestore.viewmodel

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

    // search products
    val searchQuery = mutableStateOf("")
    val searchResults = mutableStateOf<List<Product>>(emptyList())
    val isSearching = mutableStateOf(false)


    private val _products = mutableStateOf<List<Product>>(emptyList())
    private val _interestingProducts = mutableStateOf<List<Product>>(emptyList())
    private val allProducts: State<List<Product>> = _products
    val interestingProducts: State<List<Product>> = _interestingProducts
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val allProductsList : List<Product> = mainApi.getAllProducts().products
            _products.value = allProductsList
            _interestingProducts.value = allProductsList.shuffled().take(15)
        }
    }


    fun getProductById(id: Int): Product? {
        val products = allProducts.value
        val currentProduct = products.find { product -> product.id == id }
        return currentProduct
    }

    // searchBar
    fun searchProducts(query: String) {
        searchQuery.value = query
        if (query.isBlank()) {
            searchResults.value = emptyList()
        }

        viewModelScope.launch {
            isSearching.value = true
            try {
                val result = mainApi.getProductsInSearch(query)
                val filtered = result.products.filter {
                    it.title.contains(query, ignoreCase = true)
                }
                searchResults.value = filtered
            }
            catch (e: Exception) {
                searchResults.value = emptyList()
            }
            finally {
                isSearching.value = false
            }
        }
    }
}