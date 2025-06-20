package com.tops.retrofitdemo.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.tops.retrofitdemo.databinding.ProductRowItemBinding
import com.tops.retrofitdemo.model.Product

private const val TAG = "ProductAdapter"
class ProductAdapter(private val products: List<Product>, private val listener: ProductClickListener): Adapter<ProductAdapter.ProductViewHolder>() {

    interface ProductClickListener {
        fun onProductEdit(position: Int)
        fun onProductDelete(position: Int)
    }

    class ProductViewHolder(val binding: ProductRowItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Log.i(TAG, products[position].title)
        holder.binding.tvTitle.setText("${products[position].title}")
        Picasso.get().load(products[position].thumbnail).into(holder.binding.imageView)
        holder.binding.btnEdit.setOnClickListener {
            listener.onProductEdit(position)
        }
        holder.binding.btnDelete.setOnClickListener {
            listener.onProductDelete(position)
        }
    }
}