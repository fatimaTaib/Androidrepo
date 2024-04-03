package com.baben.apps.appformation3.core.app

import android.content.Context


import android.content.SharedPreferences

class AuthLocalStorage(context: Context) {

    /*  private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

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
    }*/

      companion object {
            const val TOKEN_KEY: String = "tokenKey"
            const val SHARD_PREFERENCE_KEY: String = "sharedPreferencesFileKey"
            const val DEFAULT_TOKEN_VALUE : String = ""
        }

        private val sharedPreferences = context.getSharedPreferences(SHARD_PREFERENCE_KEY,Context.MODE_PRIVATE)
        private val editor = sharedPreferences.edit()

        fun saveToken(token: String) {
            editor.putString(TOKEN_KEY,token).apply()
        }

        fun getToken(): String {
            return sharedPreferences.getString(TOKEN_KEY,DEFAULT_TOKEN_VALUE) ?: DEFAULT_TOKEN_VALUE
        }

        fun clearToken() {
            editor.remove(TOKEN_KEY).apply()
        }

        fun isLogged(): Boolean {
            return getToken() != DEFAULT_TOKEN_VALUE
        }



   /* val Sharedpreferencekey: Any? = null
    companion object{
        const  val tokencle="com.baben.apps.appformation3.core.app.tst_token"
        const val SharedPreferencescle="com.baben.apps.appformation3.core.app.sharedkey"
        const val Defaulttokenvalue=DEFAULT_VALUE_NULL
    }
*/
}

