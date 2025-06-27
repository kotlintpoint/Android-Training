package com.tops.retrofitdemo.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductRoot(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)

@Parcelize
data class Product(
    @SerializedName("availabilityStatus")
    val availabilityStatus: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("description")
    var description: String,
    @SerializedName("dimensions")
    val dimensions: Dimensions? = null,
    @SerializedName("discountPercentage")
    val discountPercentage: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<String>? = null,
    @SerializedName("meta")
    val meta: Meta? = null,
    @SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("returnPolicy")
    val returnPolicy: String? = null,
    @SerializedName("reviews")
    val reviews: List<Review>? = null,
    @SerializedName("shippingInformation")
    val shippingInformation: String? = null,
    @SerializedName("sku")
    val sku: String? = null,
    @SerializedName("stock")
    val stock: Int? = null,
    @SerializedName("tags")
    val tags: List<String>? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    var title: String,
    @SerializedName("warrantyInformation")
    val warrantyInformation: String? = null,
    @SerializedName("weight")
    val weight: Int? = null
): Parcelable

@Parcelize
data class Review(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String,
    @SerializedName("reviewerName")
    val reviewerName: String
): Parcelable

@Parcelize
data class Meta(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("updatedAt")
    val updatedAt: String
): Parcelable

@Parcelize
data class Dimensions(
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("height")
    val height: Double,
    @SerializedName("width")
    val width: Double
): Parcelable