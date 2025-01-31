package com.wiquert.onlinestore.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiquert.onlinestore.retrofit.MainApi
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
        name.value = mainApi.getProduct().title
        description.value = mainApi.getProduct().description
        price.value = mainApi.getProduct().price.toString()
        }
    }
}