package com.example.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.databinding.ActivityProductDetailsBinding
import com.example.orgs.extensions.formatBrazilianCurrency
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryToLoadProduct()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnDeleteProduct -> {

            }

            R.id.mnEditProduct -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryToLoadProduct() {
        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PRODUCT_KEY, Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(PRODUCT_KEY)
        }
        product?.let {
            fillFields(it)
        } ?: finish()
    }

    private fun fillFields(product: Product) {
        with(binding) {
            imgProductDetail.tryToLoadImage(product.image)
            tvNameDetail.text = product.name
            tvDescriptionDetail.text = product.description
            tvPriceDetail.text = product.value.formatBrazilianCurrency()
        }
    }
}