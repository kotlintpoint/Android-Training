package com.example.helloandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.helloandroid.databinding.ProductRowItemBinding
import com.example.helloandroid.models.Product

class ProductAdapter(private val dataSet: ArrayList<Product>) : Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ProductRowItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.tvTitle.text = dataSet[position].title
        holder.binding.tvCategory.text = dataSet[position].category
        holder.binding.tvPrice.text = "${dataSet[position].price} Rs."
    }
}