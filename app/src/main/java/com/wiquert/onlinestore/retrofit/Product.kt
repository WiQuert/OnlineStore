package com.wiquert.onlinestore.retrofit

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val thumbnail: String,
    val images: List<String>,
    val warrantyInformation: String,
    val shippingInformation: String,
    val returnPolicy: String
)
