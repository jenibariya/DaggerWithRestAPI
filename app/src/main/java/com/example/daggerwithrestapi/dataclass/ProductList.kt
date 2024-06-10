package com.example.daggerwithrestapi.dataclass


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ProductList(
    @SerializedName("limit")
    @Expose
    val limit: Long,
    @SerializedName("products")
    @Expose
    val products: List<Product>,
    @SerializedName("skip")
    @Expose
    val skip: Long,
    @SerializedName("total")
    @Expose
    val total: Long
) {
    data class Product(
        @SerializedName("brand")
        @Expose
        val brand: String,
        @SerializedName("category")
        @Expose
        val category: String,
        @SerializedName("description")
        @Expose
        val description: String,
        @SerializedName("discountPercentage")
        @Expose
        val discountPercentage: Double,
        @SerializedName("id")
        @Expose
        val id: Long,
        @SerializedName("images")
        @Expose
        val images: List<String>,
        @SerializedName("price")
        @Expose
        val price: Double,
        @SerializedName("rating")
        @Expose
        val rating: Double,
        @SerializedName("stock")
        @Expose
        val stock: Int,
        @SerializedName("thumbnail")
        @Expose
        val thumbnail: String,
        @SerializedName("title")
        @Expose
        val title: String
    )
}