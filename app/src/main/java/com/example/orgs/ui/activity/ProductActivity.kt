package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductsDAO
import com.example.orgs.databinding.ActivityProductBinding
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        confSaveButton()
        binding.imgProduct.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(R.layout.imagem_form)
                .setPositiveButton("Confirm") { _, _ -> }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
        }
    }

    private fun confSaveButton() {
        val btnSave = binding.btnSave
        val dao = ProductsDAO()
        btnSave.setOnClickListener {
            val product = registerProduct()
            dao.add(product)
            finish()
        }
    }

    private fun registerProduct(): Product {
        val name = binding.tieName.text.toString()
        val description = binding.tieDescription.text.toString()
        val priceString = binding.tiePrice.text.toString()
        val price = if (priceString.isBlank()) BigDecimal.ZERO else BigDecimal(priceString)

        return Product(
            name = name,
            description = description,
            value = price
        )
    }
}