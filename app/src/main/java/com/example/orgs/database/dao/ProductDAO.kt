package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
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

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getById(id: Long): Product?

    @Query("SELECT * FROM Product ORDER BY name ASC")
    fun orderByNameAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY name DESC")
    fun orderByNameDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description ASC")
    fun orderByDescriptionAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description DESC")
    fun orderByDescriptionDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY value ASC")
    fun orderByValueAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY value DESC")
    fun orderByValueDesc(): List<Product>
}