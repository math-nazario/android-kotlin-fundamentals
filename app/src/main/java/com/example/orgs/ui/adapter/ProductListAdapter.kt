package com.example.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.databinding.ProductItemBinding
import com.example.orgs.extensions.formatBrazilianCurrency
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product

class ProductListAdapter(
    private val context: Context,
    products: List<Product> = emptyList(),
    var clickItem: (product: Product) -> Unit = {},
    var whenClickDelete: (product: Product) -> Unit = {},
    var whenClickEdit: (product: Product) -> Unit = {}
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(
        private val binding: ProductItemBinding,
        private val whenClickDelete: (product: Product) -> Unit,
        private val whenClickEdit: (product: Product) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    clickItem(product)
                }
            }

            itemView.setOnLongClickListener {
                if (::product.isInitialized) {
                    showPopupMenu(it)
                }
                true
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

        private fun showPopupMenu(view: View) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.menu_product_details, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.mnDeleteProduct -> {
                        whenClickDelete(product)
                        true
                    }

                    R.id.mnEditProduct -> {
                        whenClickEdit(product)
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, whenClickDelete, whenClickEdit)
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