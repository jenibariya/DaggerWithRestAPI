package com.example.daggerwithrestapi.listener

import com.example.daggerwithrestapi.dataclass.ProductList

interface OnProductClickListener {
    fun onProductClicked(product: ProductList.Product)
}
