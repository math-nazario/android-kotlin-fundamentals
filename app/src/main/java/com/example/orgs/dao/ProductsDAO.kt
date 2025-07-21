package com.example.orgs.dao

import com.example.orgs.model.Product

class ProductsDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun getAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}