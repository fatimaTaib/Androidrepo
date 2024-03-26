package com.baben.apps.appformation3.data.remote.repositories


import com.baben.apps.appformation3.data.remote.retrofit.UserApi
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository

/*class ApiLoginRepository : LoginRepository {

    override fun login(model: Login?): LoginRepository.LoginResult? {
        TODO("Not yet implemented")
    }

}*/
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiLoginRepository : LoginRepository {

    private val userApi: UserApi = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserApi::class.java)

    override fun login(model: Login?): LoginRepository.LoginResult? {
        val response = userApi.login(Login(model?.username.orEmpty(), model?.password.orEmpty()))
        if (response.isSuccessful) {
            // Save user data to SharedPreferences
            val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", true)
            editor.apply()

            return LoginRepository.LoginResult.SUCCESS
        } else {
            return LoginRepository.LoginResult.AUTH_ERROR
        }
    }
}

