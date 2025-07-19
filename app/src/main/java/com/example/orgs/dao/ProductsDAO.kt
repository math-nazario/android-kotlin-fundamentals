package com.example.orgs.dao

import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductsDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun getAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                name = "Fuit salad",
                description = "Lettuce, tomato, banana",
                value = BigDecimal("29.99")
            )
        )
    }
}