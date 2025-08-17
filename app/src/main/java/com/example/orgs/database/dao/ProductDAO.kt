package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orgs.model.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(product: Product)
}