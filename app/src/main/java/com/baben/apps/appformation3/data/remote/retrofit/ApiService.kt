package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun login(@Body loginRequest: Login): Call<LoginResponse>

    // Ajoutez d'autres méthodes pour les appels API supplémentaires

}
