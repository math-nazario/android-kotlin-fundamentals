package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.dao.ProductsDAO
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.recyclerview.adapter.ProductListAdapter

class ProductListActivity : AppCompatActivity() {

    private val dao = ProductsDAO()
    private val adapter = ProductListAdapter(this, dao.getAll())
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
        adapter.update(dao.getAll())
    }

    private fun configFab() {
        val fabAdd = binding.fabAdd
        fabAdd.setOnClickListener {
            goToProductActivity()
        }
    }

    private fun configRecyclerView() {
        val recyclerView = binding.rvProducts
        recyclerView.adapter = adapter
    }

    private fun goToProductActivity() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }
}