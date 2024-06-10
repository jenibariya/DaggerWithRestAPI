package com.example.daggerwithrestapi.apiservice

import com.example.daggerwithrestapi.dataclass.ProductList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): Response<ProductList>
}