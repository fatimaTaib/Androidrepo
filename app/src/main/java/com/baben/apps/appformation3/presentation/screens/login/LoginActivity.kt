package com.baben.apps.appformation3.presentation.screens.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import com.baben.apps.appformation3.core.bases.BaseActivities
import com.baben.apps.appformation3.data.remote.retrofit.ApiService
import com.baben.apps.appformation3.databinding.ActivityLoginBinding
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.models.LoginResponse
import com.baben.apps.appformation3.presentation.screens.home.HomeActivity
import com.baben.apps.appformation3.presentation.screens.signup.SignupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : BaseActivities() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActions()
        val authStorage = AuthLocalStorage(this)
        // Vérifiez si l'utilisateur est déjà connecté au démarrage de l'activité
        if (authStorage.isLogged()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
   /* private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.contains("token")
    }*/

    private fun setupActions() {
        binding.uiLoginButton.setOnClickListener(::onLoginButtonClicked)
        binding.uiSignupButton.setOnClickListener(::onSignupButtonClicked)
    }

    private fun onLoginButtonClicked(view: View?) {
       // TODO("implement real login action")
        startActivity(Intent(context, HomeActivity::class.java))
        finish()
    }

    private fun onSignupButtonClicked(view: View?) {
        startActivity(Intent(context, SignupActivity::class.java))
    }
    private fun login(username: String, password: String) {
        val apiService = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val loginCall = apiService.login(Login(username, password))
        loginCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    saveToken(token)
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    // Handle login error
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle login failure
            }
        })
    }

    private fun saveToken(token: String?) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }


}