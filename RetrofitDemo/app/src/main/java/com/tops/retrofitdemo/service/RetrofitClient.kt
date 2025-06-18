package com.tops.retrofitdemo.service

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "RetrofitClient"
class RetrofitClient {

    companion object {

        private var service: RetrofitService? = null

        fun getInstance(): RetrofitService = service ?: synchronized(this) {
            service ?: buildRetrofitService().also { service = it }
        }

        private fun buildRetrofitService(): RetrofitService {
            Log.i(TAG, "buildRetrofitService Call")
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }

    }
}