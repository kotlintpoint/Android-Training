package com.tops.viewmodeldemo.model


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("coin")
    val coin: String,
    @SerializedName("network")
    val network: String,
    @SerializedName("wallet")
    val wallet: String
)