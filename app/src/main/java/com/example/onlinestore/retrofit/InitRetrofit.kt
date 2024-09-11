package com.example.onlinestore.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://dummyjson.com")
    .addConverterFactory(GsonConverterFactory.create()).
    build()

val mainApi = retrofit.create(MainAPI::class.java)



