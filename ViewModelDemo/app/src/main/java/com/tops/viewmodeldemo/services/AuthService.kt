package com.tops.viewmodeldemo.services

import com.tops.viewmodeldemo.model.LoginResponse
import com.tops.viewmodeldemo.model.LoginUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthService {

//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>

    @POST("auth/login")
    fun login(@Body user: LoginUser): Call<LoginResponse>
}