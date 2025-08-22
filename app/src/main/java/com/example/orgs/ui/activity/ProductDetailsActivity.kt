package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductDetailsBinding
import com.example.orgs.extensions.formatBrazilianCurrency
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private var productId: Long = 0L
    private var product: Product? = null
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryToLoadProduct()
    }

    override fun onResume() {
        super.onResume()
        productSearch()
    }

    private fun productSearch() {
        product = productDao.getById(productId)
        product?.let {
            fillFields(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnDeleteProduct -> {
                product?.let { productDao.delete(it) }
                finish()
            }

            R.id.mnEditProduct -> {
                Intent(this, ProductActivity::class.java).apply {
                    putExtra(PRODUCT_KEY_ID, productId)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryToLoadProduct() {
        productId = intent.getLongExtra(PRODUCT_KEY_ID, 0L)
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