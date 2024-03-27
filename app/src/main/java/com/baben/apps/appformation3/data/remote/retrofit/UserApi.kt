package com.baben.apps.appformation3.data.remote.retrofit
import retrofit2.Response

import com.baben.apps.appformation3.domain.models.Login
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login") // Endpoint for login
    fun login(@Body model: Login): Response<Any>



    //TODO :: add Login and SingUp services here

    // login
    // singUp
}