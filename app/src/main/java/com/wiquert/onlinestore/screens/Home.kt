package com.wiquert.onlinestore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiquert.onlinestore.R
import com.wiquert.onlinestore.retrofit.MainApi
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
        Scaffold (
            topBar = {
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

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)) {
                GetSingleProduct()
                }
            }
    }


@Composable
fun GetSingleProduct() {
//    val coroutineScope = rememberCoroutineScope()
//    val name = remember { mutableStateOf("") }
//    val description = remember { mutableStateOf("") }
////    LaunchedEffect(key1 = Unit) {
////        coroutineScope.launch() {
////            name.value = mainApi.getProduct().title
////            description.value = mainApi.getProduct().description
////        }
////   }
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize(0.5f)
        .padding(start = 5.dp, end = 5.dp, top = 15.dp)
        .background(color = Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "name.value", fontSize = 18.sp)
        Spacer(Modifier.size(15.dp))
        Text(text = "description.value")
    }
}