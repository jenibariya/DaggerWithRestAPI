package com.example.daggerwithrestapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.daggerwithrestapi.dataclass.ProductList
import com.example.daggerwithrestapi.databinding.ProductListItemViewBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProductAdapter(
    private val products: MutableList<ProductList.Product>,
    private val context: Context,
    private val listener: OnProductClickListener
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductListItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun removeAt(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(private val binding: ProductListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductList.Product) {
            itemView.setOnClickListener {
                listener.onProductClicked(product)
            }

            binding.executePendingBindings()

            binding.titleTextView.text = product.title

            val adapter = ImagePagerAdapter(product.images, context)
            binding.viewPager.adapter = adapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                binding.tabLayout.getTabAt(position)?.select()
            }.attach()
        }

    }

    interface OnProductClickListener {
        fun onProductClicked(product: ProductList.Product)
    }
}
