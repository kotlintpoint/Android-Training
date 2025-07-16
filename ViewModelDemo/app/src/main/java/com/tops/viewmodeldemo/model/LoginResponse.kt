package com.tops.viewmodeldemo.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("username")
    val username: String
)