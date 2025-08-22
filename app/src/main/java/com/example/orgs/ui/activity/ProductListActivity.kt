package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.ui.adapter.ProductListAdapter

class ProductListActivity : AppCompatActivity() {

    private val adapter = ProductListAdapter(this)
    private lateinit var binding: ActivityProductListBinding

    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(productDao.getAll())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_ordering, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnNameAsc -> {
                adapter.update(productDao.orderByNameAsc())
            }

            R.id.mnNameDesc -> {
                adapter.update(productDao.orderByNameDesc())
            }

            R.id.mnDescriptionAsc -> {
                adapter.update(productDao.orderByDescriptionAsc())
            }

            R.id.mnDescriptionDesc -> {
                adapter.update(productDao.orderByDescriptionDesc())
            }

            R.id.mnPriceAsc -> {
                adapter.update(productDao.orderByValueAsc())
            }

            R.id.mnPriceDesc -> {
                adapter.update(productDao.orderByValueDesc())
            }

            R.id.mnWithoutOrdering -> {
                adapter.update(productDao.getAll())
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun configFab() {
        val fabAdd = binding.fabAddProduct
        fabAdd.setOnClickListener {
            goToProductActivity()
        }
    }

    private fun configRecyclerView() {
        val recyclerView = binding.rvProducts
        recyclerView.adapter = adapter
        adapter.clickItem = {
            val intent = Intent(this, ProductDetailsActivity::class.java).apply {
                putExtra(PRODUCT_KEY_ID, it.id)
            }
            startActivity(intent)
        }
    }

    private fun goToProductActivity() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }
}