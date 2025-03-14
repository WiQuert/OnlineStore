package com.wiquert.onlinestore.di

import com.wiquert.onlinestore.retrofit.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).
            build()
    }

    @Provides
    @Singleton
    fun providesMainApi(retrofit: Retrofit) : MainApi {
        return retrofit.create(MainApi::class.java)
    }
    
}