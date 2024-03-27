package com.baben.apps.appformation3.domain.models

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baben.apps.appformation3.data.remote.repositories.ApiLoginRepository

class LoginViewModel(private val apiRepository: ApiLoginRepository, private val context: Context) : ViewModel() {

    private val _usernameError = MutableLiveData<String>()
    val usernameError: LiveData<String> = _usernameError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _internetError = MutableLiveData<String>()
    val internetError: LiveData<String> = _internetError

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun validateCredentials(username: String, password: String) {
        if (username.isEmpty()) {
            _usernameError.value = "Username cannot be empty"
            return
        }
        if (password.isEmpty()) {
            _passwordError.value = "Password cannot be empty"
            return
        }
       // login(username, password)
    }

    fun checkInternetConnection() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) {
            _internetError.value = "No internet connection"
        }
    }

   /* private fun login(username: String, password: String) {
        apiRepository.login(Login(username, password), object : ApiLoginRepository.LoginCallback {
            override fun onSuccess() {
                _loginSuccess.value = true
            }

            override fun onFailure() {
                _loginSuccess.value = false
            }
        })
    }*/
}
