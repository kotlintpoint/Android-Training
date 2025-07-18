package com.tops.viewmodeldemo.services

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.tops.viewmodeldemo.model.LoginResponse

class SharedPreferenceService(context: Context) {

    private val FILE_NAME = "File_Name"
    private val LOGIN_RESPONSE = "LOGIN_RESPONSE"
    private var sharedPref: SharedPreferences = context.getSharedPreferences(
        FILE_NAME, Context.MODE_PRIVATE)

    fun writeLoginResponse(data: LoginResponse) {
        val dataJson = Gson().toJson(data)
        with (sharedPref.edit()) {
            putString(LOGIN_RESPONSE, dataJson)
            apply()
        }
    }

    fun readLoginResponse(): LoginResponse? {
        val dataJson = sharedPref.getString(LOGIN_RESPONSE, null)
        if(dataJson == null){
            return dataJson
        }
        return Gson().fromJson(dataJson, LoginResponse::class.java)
    }
}