package com.example.helloandroid.models

// https://dummyjson.com/docs/products
data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val category: String,
    val price: Double
)
