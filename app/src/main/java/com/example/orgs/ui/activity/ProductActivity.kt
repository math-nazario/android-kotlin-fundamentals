package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductBinding
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product
import com.example.orgs.ui.dialog.ImageDialog
import java.math.BigDecimal

class ProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var productId = 0L
    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Register Product"
        confSaveButton()

        binding.imgProduct.setOnClickListener {
            ImageDialog(this).show(url) { image ->
                url = image
                binding.imgProduct.tryToLoadImage(url)
            }
        }
        tryToLoadProduct()
    }

    private fun tryToLoadProduct() {
        productId = intent.getLongExtra(PRODUCT_KEY_ID, 0L)
    }

    override fun onResume() {
        super.onResume()
        productDao.getById(productId)?.let {
            fillFields(it)
        }
    }

    private fun fillFields(product: Product) {
        title = "Edit Product"
        url = product.image
        with(binding) {
            imgProduct.tryToLoadImage(product.image)
            tieNameProduct.setText(product.name)
            tieDescriptionProduct.setText(product.description)
            tiePriceProduct.setText(product.value.toPlainString())
        }
    }

    private fun confSaveButton() {
        val btnSave = binding.btnSaveProduct
        btnSave.setOnClickListener {
            val product = registerProduct()
            if (product != null) {
                productDao.add(product)
                finish()
            }
        }
    }

    private fun registerProduct(): Product? {
        val name = binding.tieNameProduct.text.toString()
        val description = binding.tieDescriptionProduct.text.toString()
        val priceString = binding.tiePriceProduct.text.toString()
        val price = if (priceString.isBlank()) BigDecimal.ZERO else BigDecimal(priceString)

        if (name.isBlank() || description.isBlank() || price.compareTo(BigDecimal.ZERO) == 0) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            return null
        }

        return Product(
            id = productId,
            name = name,
            description = description,
            value = price,
            image = url
        )
    }
}