package com.baben.apps.appformation3.presentation.screens.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.core.networking.NetworkErrorException


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState> = _loginState

    fun login(username: String, password: String) {
        if (validateUsernameAndPassword(username, password)) {
            if (isInternetAvailable()) {
                try {
                    val result = loginRepository.login(Login(username, password))
                    when (result) {
                        LoginRepository.LoginResult.SUCCESS -> {
                            saveTokenInLocalStorage("token")

                            _loginState.value = LoginViewState.Success
                        }
                        LoginRepository.LoginResult.AUTH_ERROR -> {
                            _loginState.value = LoginViewState.Error("Authentication error")
                        }
                        LoginRepository.LoginResult.GENERAL_ERROR -> {
                            _loginState.value = LoginViewState.Error("General error")
                        }

                        else -> {}
                    }
                } catch (e: NetworkErrorException) {
                    _loginState.value = LoginViewState.Error("Network error")
                }
            } else {
                _loginState.value = LoginViewState.Error("No internet connection")
            }
        } else {
            _loginState.value = LoginViewState.Error("Invalid username or password")
        }
    }

    private fun validateUsernameAndPassword(username: String, password: String): Boolean {

        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun isInternetAvailable(): Boolean {
        return false
    }


   /* private fun saveTokenInLocalStorage(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }*/
   private fun saveTokenInLocalStorage(token: String) {
      }

    //une classe scellée (sealed class) en Kotlin qui représente les différents états possibles de l'opération de login
    sealed class LoginViewState {
        object Loading : LoginViewState()
        object Success : LoginViewState()
        data class Error(val message: String) : LoginViewState()
    }
}
//

