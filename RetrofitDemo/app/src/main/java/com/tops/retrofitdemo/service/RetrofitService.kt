package com.tops.retrofitdemo.service

import com.tops.retrofitdemo.model.NewProduct
import com.tops.retrofitdemo.model.NewProductResponse
import com.tops.retrofitdemo.model.Product
import com.tops.retrofitdemo.model.ProductRoot
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RetrofitService {

    @GET("products?limit=10")
    fun listProducts():Call<ProductRoot>

    @POST("products/add")
    fun saveProduct(@Body product: NewProduct): Call<NewProductResponse>

    @PUT("products/{id}")
    fun updateProduct(@Path("id") groupId: Int?, @Body product: NewProduct): Call<NewProductResponse>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") groupId: Int): Call<Product>
}