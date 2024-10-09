package com.example.onlinestore.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {
    @GET("product/1")
    suspend fun getProduct(): Product

    @GET("products/search")
    suspend fun getProductsInSearch(@Query("q") name: String): Products

    @GET("products")
    suspend fun getAllProducts(): Products
}