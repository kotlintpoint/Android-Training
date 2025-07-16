package com.tops.viewmodeldemo.model

data class User(val id: Int, val name: String, val email: String="")

data class LoginUser(val username: String, val password: String, val expiresInMins: Int = 30)
