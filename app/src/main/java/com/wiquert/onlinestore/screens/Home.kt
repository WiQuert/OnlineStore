package com.wiquert.onlinestore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            ) {
                    ShowInterestingProducts()
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
fun ShowInterestingProducts(viewModel: MainViewModel = hiltViewModel()) {
    val interestingProduct = viewModel.interestingProducts.value
    LazyColumn(modifier = Modifier
        .fillMaxSize()
//        .fillMaxSize(0.8f)
        .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(interestingProduct) {item ->
            Card {
                Column(modifier = Modifier
                    .padding(5.dp)) {
                    Text(text = item.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.size(15.dp))
                    Text(text = item.description)
                    Spacer(Modifier.size(15.dp))
                    Text(text = "Total price: $${item.price}")
                       }
                }
        }
    }
}