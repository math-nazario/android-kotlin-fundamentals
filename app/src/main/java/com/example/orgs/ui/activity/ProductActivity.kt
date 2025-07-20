package com.example.orgs.ui.activity

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.example.orgs.dao.ProductsDAO
import com.example.orgs.databinding.ActivityProductBinding
import com.example.orgs.databinding.ImageFormBinding
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        confSaveButton()

        val imageLoader = ImageLoader.Builder(this)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        binding.imgProduct.setOnClickListener {
            val bindingImage = ImageFormBinding.inflate(layoutInflater)
            bindingImage.btnLoadImage.setOnClickListener {
                val url = bindingImage.tieURL.text.toString()
                bindingImage.imgProductImageUploaded.load(url, imageLoader)
            }
            AlertDialog.Builder(this)
                .setView(bindingImage.root)
                .setPositiveButton("Confirm") { _, _ ->
                    url = bindingImage.tieURL.text.toString()
                    binding.imgProduct.load(url, imageLoader)
                }
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
            value = price,
            image = url
        )
    }
}