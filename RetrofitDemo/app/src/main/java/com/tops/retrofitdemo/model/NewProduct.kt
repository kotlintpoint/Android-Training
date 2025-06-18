package com.tops.retrofitdemo.model


import com.google.gson.annotations.SerializedName

data class NewProduct(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)

data class NewProductResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String
)