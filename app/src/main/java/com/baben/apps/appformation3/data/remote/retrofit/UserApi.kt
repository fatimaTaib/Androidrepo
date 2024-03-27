package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/*interface UserApi {

    //TODO :: add Login and SingUp services here

    // login
    // singUp
 } */  interface UserApi {
        @POST("auth/login")
       fun login(@Body login: Login): Response<LoginResponse>
    }


