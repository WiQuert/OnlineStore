package com.wiquert.onlinestore.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("product/41")
    suspend fun getProduct(): Product

    @GET("products/search")
    suspend fun getProductsInSearch(
        @Query("q") name: String,
        @Query("limit") limit: Int = 0): Products

    @GET("products")
    suspend fun getAllProducts(@Query("limit") limit: Int = 0): Products
}