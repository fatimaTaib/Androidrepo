package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.data.remote.repositories.ApiLoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideLoginApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    fun provideApiLoginRepository(loginApi: UserApi): ApiLoginRepository {
        return ApiLoginRepository(loginApi)
    }
}
