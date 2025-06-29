package com.wiquert.onlinestore.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.wiquert.onlinestore.viewmodel.MainViewModel


@Composable
fun ProductDetailScreen(productId: Int, viewModel: MainViewModel = hiltViewModel()) {
    val product = viewModel.getProductById(productId)

    if (product != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(model = product.thumbnail, contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Цена: $${product.price}")
        }
    } else {
        Text("Продукт не найден")
    }
}
