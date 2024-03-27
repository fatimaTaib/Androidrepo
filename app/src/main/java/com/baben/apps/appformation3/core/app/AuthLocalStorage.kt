package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences

class AuthLocalStorage (val context : Context){
     val token="access_token"
private val sharedPreferences : SharedPreferences=context.getSharedPreferences(
    "AuthLocalStorage", Context.MODE_PRIVATE
)
    fun saveAccessToken(accessToken: String?):Boolean {
        if (accessToken!=null)
        {
            sharedPreferences.edit().putString(token, accessToken).apply()
            return true
        }
        return false
    }
    fun getAccessToken() : String?
    {
        return sharedPreferences.getString(token,null)
    }
    fun isLoggedIn():Boolean
    {
        return sharedPreferences.contains(token)
    }


}