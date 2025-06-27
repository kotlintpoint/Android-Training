package com.tops.retrofitdemo.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewProduct(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)

@Parcelize
data class NewProductResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String
): Parcelable