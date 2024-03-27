package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences

class AuthLocalStorage (val context : Context){
private val sharedPreferences : SharedPreferences=context.getSharedPreferences(
    "AuthLocalStorage", Context.MODE_PRIVATE
)
    fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit().putString("access_token", accessToken).apply()
    }
    fun getAccessToken() : String?
    {
        return sharedPreferences.getString("access_token",null)
    }
    fun isLoggedIn():Boolean
    {
        return sharedPreferences.contains("access_token")
    }


}