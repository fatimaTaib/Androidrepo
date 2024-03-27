package com.baben.apps.appformation3.domain.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository,private val authLocalStorage: AuthLocalStorage) : ViewModel() {


    private val _usernameLiveData = MutableLiveData<String>()
    private val _passwordLiveData = MutableLiveData<String>()
    val usernameLiveData: MutableLiveData<String>
        get() = _usernameLiveData
    val passwordLiveData : MutableLiveData<String>
        get() = _passwordLiveData

    //Login function
    fun login() {
      var  login: Login



    }


    }



