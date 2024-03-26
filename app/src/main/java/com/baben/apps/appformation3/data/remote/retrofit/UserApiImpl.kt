package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.models.Login
import retrofit2.Response
import retrofit2.Retrofit

class UserApiImpl(private val retrofit: Retrofit) : UserApi {
    private val service: UserApiService = retrofit.create(UserApiService::class.java)

    override  fun login(login: Login): Response<LoginResponseDto> {
        return service.login(login)
    }
}
