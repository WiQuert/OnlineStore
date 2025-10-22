package com.wiquert.onlinestore.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
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
    val isSearchActive = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

     Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isSearchActive.value) {
            Image(
                modifier = Modifier
                    .size(235.dp, 60.dp)
                    .align(Alignment.CenterHorizontally),
                imageVector = ImageVector.vectorResource(R.drawable.logo_online_store),
                contentDescription = "logo"
            )
        }
        Scaffold(
            topBar = {
                    Box(modifier = Modifier.offset(y = (-25).dp)) {
                        HomeSearchBar(
                            navController = navController,
                            isActive = isSearchActive
                        )
                    }
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = innerPadding)
                    .verticalScroll(scrollState)
            ) {
                HeaderText(stringResource(id = R.string.mainscreen_interesting_products))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(LocalConfiguration.current.screenWidthDp.dp * 0.8f)
                        .height(LocalConfiguration.current.screenHeightDp.dp * 0.35f)
                ){
                    ShowInterestingProducts(navController = navController)
                }
                HeaderText("Slider")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(LocalConfiguration.current.screenWidthDp.dp * 0.8f)
                        .height(LocalConfiguration.current.screenHeightDp.dp * 0.35f)
                ) {
                    ProductSlider(navController = navController)
                }
            }
        }
     }
}



//SearchBar
@Composable

@ExperimentalMaterial3Api
fun HomeSearchBar(navController: NavController, viewModel: MainViewModel = hiltViewModel(), isActive: MutableState<Boolean>) {

    val searchText = viewModel.searchQuery


    val results = viewModel.searchResults.value
    val isLoading = viewModel.isSearching.value


        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
               .background(MaterialTheme.colorScheme.background),
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(12.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchText.value,
                    onQueryChange = { text ->
                        viewModel.searchProducts(text)
                    },
                    expanded = isActive.value,
                    onExpandedChange = { value ->
                        isActive.value = value
                    },
                    onSearch = {
                        isActive.value = false
                        viewModel.searchQuery.value = ""
                        viewModel.searchResults.value = emptyList()
                    },
                    placeholder = {
                        Text(
                            stringResource(R.string.mainscreen_search_text_hint))
                    }
                )
            },
            expanded = isActive.value,
            onExpandedChange = { value ->
                isActive.value = value
            },
        )
        {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                results.isEmpty() -> {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.mainscreen_search_text_nothing),
                    )
                }

                else -> {
                    LazyColumn {
                        items(results) { product ->
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        isActive.value = false
                                        viewModel.searchQuery.value = ""
                                        navController.navigate("product/${product.id}")
                                    }
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = product.title,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                }
            }
        }
    }



// section "this might be interesting"
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
                    .background(MaterialTheme.colorScheme.primary)
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


// section "slider"
@Composable
fun ProductSlider(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val productsSlider = listOf(96,114,88,78).mapNotNull { ids ->
        viewModel.getProductById(ids)
    }
    val sliderState = rememberPagerState(pageCount = { productsSlider.size })


    HorizontalPager(
        state = sliderState,
        modifier = Modifier
            .fillMaxWidth()
            ) { element ->
        val product = productsSlider[element]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("product/${product.id}")
                }
        ) {
            ProductImage(product.id)

                //slider indicators (dots)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)  // снизу по центру
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (indicator in 0 until productsSlider.size) {
                    val isSelected = sliderState.currentPage == indicator
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.Black else Color.Gray)
                    )
                }
            }
        }
        }
}


// Section "Our advantages"
@Composable
fun OurAdvantages() {

}


//Headers
@Composable
fun HeaderText(text: String) {
    Spacer(modifier = Modifier.height(5.dp))
    Text(modifier = Modifier.fillMaxWidth()
        .padding(top = 12.dp, bottom = 12.dp),
        text = text,
        color = Color(0xFF326BCE),
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(5.dp))
}


@Composable
fun ProductImage(productId: Int) {
    val imageRes = getImageResourceByProductId(productId)
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Product Image",
        modifier = Modifier
            .fillMaxWidth(),
    )
}


fun getImageResourceByProductId(productId: Int): Int {
    return when (productId) {
        96 -> R.drawable.slider_1
        114 -> R.drawable.slider_2
        88 -> R.drawable.slider_3
        78 -> R.drawable.slider_4
        else -> R.drawable.slider_1
    }
}