package com.tops.onlinestorage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tops.onlinestorage.databinding.ProductRowItemBinding
import com.tops.onlinestorage.model.Product

class ProductAdapter(private val products: List<Product>) : Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ProductRowItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        android.R.layout.simple_list_item_2
        val binding = ProductRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.text1.setText("ID = ${product.id}")
        holder.binding.text2.setText("Name = ${product.name}")
    }
}