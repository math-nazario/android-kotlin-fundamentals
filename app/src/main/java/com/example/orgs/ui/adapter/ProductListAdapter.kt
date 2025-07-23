package com.example.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProductItemBinding
import com.example.orgs.extensions.formatBrazilianCurrency
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product

class ProductListAdapter(
    private val context: Context,
    products: List<Product>, var clickItem: (product: Product) -> Unit = {}
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    clickItem(product)
                }
            }
        }

        fun bind(product: Product) {
            this.product = product
            binding.txtNameItem.text = product.name
            binding.txtDescriptionItem.text = product.description
            binding.txtPriceItem.text = product.value.formatBrazilianCurrency()

            val visibilityImage = if (product.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.imgDefault.visibility = visibilityImage

            binding.imgDefault.tryToLoadImage(product.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    fun update(all: List<Product>) {
        this.products.clear()
        this.products.addAll(all)
        notifyDataSetChanged()
    }
}