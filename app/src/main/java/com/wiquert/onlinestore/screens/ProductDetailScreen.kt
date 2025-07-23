package com.wiquert.onlinestore.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.wiquert.onlinestore.viewmodel.MainViewModel


@Composable
fun ProductDetailScreen(productId: Int, navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    val product = viewModel.getProductById(productId)
    val isProductLoaded = viewModel.allProducts.value.isNotEmpty()

    Column(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Назад")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (!isProductLoaded) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }


        else if (product != null) {
            Text(text = product.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(model = product.thumbnail, contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Цена: $${product.price}")
        } else {
            Text("Продукт не найден")
        }
    }
}
