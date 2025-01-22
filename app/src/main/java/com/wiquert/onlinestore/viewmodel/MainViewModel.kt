package com.wiquert.onlinestore.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiquert.onlinestore.retrofit.MainApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainApi: MainApi) : ViewModel() {
    private val name = mutableStateOf("")
    private val description = mutableStateOf("")
    init {
        viewModelScope.launch {
        name.value = mainApi.getProduct().title
        description.value = mainApi.getProduct().description
        }
    }
}