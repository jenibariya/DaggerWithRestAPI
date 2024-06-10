package com.example.daggerwithrestapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggerwithrestapi.dataclass.ProductList
import com.example.daggerwithrestapi.apiservice.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ProductService) {

    private val _products = MutableLiveData<List<ProductList.Product>>()
    val products: LiveData<List<ProductList.Product>>
        get() = _products

    suspend fun getProducts() {
        val result = apiService.getProducts()
        if (result.isSuccessful && result.body() != null) {
            _products.postValue(result.body()!!.products)
        }
    }
}

