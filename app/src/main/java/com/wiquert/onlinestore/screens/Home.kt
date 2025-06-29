package com.wiquert.onlinestore.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.wiquert.onlinestore.R
import com.wiquert.onlinestore.viewmodel.MainViewModel


@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        ) {
        Image(modifier = Modifier
            .size(235.dp,60.dp)
            .align(Alignment.CenterHorizontally),
            imageVector = ImageVector.vectorResource(R.drawable.logo_online_store),
            contentDescription = "logo"
        )
        Scaffold(
            topBar = {
                HomeSearchBar()
            },
        )
        { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                HeaderText(stringResource(id = R.string.mainscreen_interesting_products))
                ShowInterestingProducts(navController = navController)
            }
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
fun HeaderText(text: String) {
    Text(modifier = Modifier.fillMaxWidth()
        .padding(top = 12.dp, bottom = 4.dp),
        text = text,
        color = Color(0xFF326BCE),
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ShowInterestingProducts(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val interestingProduct = viewModel.interestingProducts.value
    LazyRow(modifier = Modifier
        .fillMaxSize()
        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(interestingProduct) {item ->
            Card(modifier = Modifier
                .clickable {
                    navController.navigate("product/${item.id}")
                }
                .width(LocalConfiguration.current.screenWidthDp.dp * 0.8f)
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.35f)
            ) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    Text(modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                        text = item.title,
                        maxLines = 2,
                        minLines = 2,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)
                    AsyncImage(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        model = item.thumbnail,
                        contentDescription = null,
                    )
                    Spacer(Modifier.size(15.dp))
                    Text(
                        text = item.description,
                        maxLines = 2,
                        minLines = 2,
                        overflow = TextOverflow.Ellipsis)
                    Spacer(Modifier.size(15.dp))
                    Text(
                        text = "Total price: $${item.price}",
                        fontWeight = FontWeight.SemiBold
                    )
                       }
                }
        }
    }
}