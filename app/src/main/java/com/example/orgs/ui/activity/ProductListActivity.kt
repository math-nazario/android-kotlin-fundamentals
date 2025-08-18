package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.ui.adapter.ProductListAdapter

class ProductListActivity : AppCompatActivity() {

    private val adapter = ProductListAdapter(this)
    private lateinit var binding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instance(this)
        val productDao = db.productDao()
        adapter.update(productDao.getAll())
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
                putExtra(PRODUCT_KEY, it)
            }
            startActivity(intent)
        }

        adapter.whenClickDelete = {}

        adapter.whenClickEdit = {}
    }

    private fun goToProductActivity() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }
}