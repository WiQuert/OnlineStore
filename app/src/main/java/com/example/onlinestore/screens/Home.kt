package com.example.onlinestore.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlinestore.R
import com.example.onlinestore.retrofit.Product
import com.example.onlinestore.retrofit.Products
import com.example.onlinestore.retrofit.mainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
        Scaffold (
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                val searchText = remember {
                    mutableStateOf("")
                }

                val isActive = remember {
                    mutableStateOf(false)
                }

                SearchBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                    query = searchText.value,
                    onQueryChange ={ text ->
                        searchText.value = text

                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.search_text))
                    },
                    onSearch = { text ->
                        isActive.value = false
                    },
                    active = isActive.value,
                    onActiveChange = {
                        isActive.value = true
                    }
                ) {
                    Text("Test")
                    }
            }
        )
        { innerPadding ->

            Box(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
                GetSingleProduct()
                }
            }
    }


@Composable
fun GetSingleProduct() {
    val coroutineScope = rememberCoroutineScope()
    val testproduct = remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch() {
            testproduct.value = mainApi.getProduct().title
        }
   }
    Text(text = testproduct.value)
}