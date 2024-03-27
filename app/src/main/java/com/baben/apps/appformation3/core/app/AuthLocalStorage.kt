package com.baben.apps.appformation3.core.app

import android.content.Context

import android.content.SharedPreferences



class AuthLocalStorage(private val sharedPreferences: SharedPreferences) {

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains("token")
    }
}
