package com.wiquert.onlinestore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wiquert.onlinestore.viewmodel.MainViewModel


@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
        Scaffold (
            topBar = {
                HomeSearchBar()
            }
        )
        { innerPadding ->
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .background(color = Color.Red)) {
                    ShowAllProducts()
                }
        }

}


@Composable
@ExperimentalMaterial3Api
fun HomeSearchBar() {

    val searchText = remember {
        mutableStateOf("")
    }

    val isActive = remember {
        mutableStateOf(false)
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText.value,
                onQueryChange = { text ->
                    searchText.value = text
                },
                expanded = isActive.value,
                onExpandedChange = { value ->
                    isActive.value = value
                },
                onSearch = {
                    isActive.value = false
                },
                placeholder = {
                    Text("Type your search here")
                }
            )
        },
        expanded = isActive.value,
        onExpandedChange = { value ->
            isActive.value = value
        },
    )
    {
        Text("Coming soon")
    }
}


@Composable
fun ShowAllProducts(viewModel: MainViewModel = hiltViewModel()) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize(0.8f)
        .padding(start = 5.dp, end = 5.dp, top = 15.dp)
    ) {
        items(count = 20) {
            Text(text = viewModel.name.value, fontSize = 18.sp)
            Spacer(Modifier.size(15.dp))
            Text(text = viewModel.description.value)
            Spacer(Modifier.size(15.dp))
            Text(text = "Total price: ${viewModel.price.value} USD")
        }
    }
}