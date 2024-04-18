package com.baben.apps.appformation3.data.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {



    // get all categories
    // get all products
    // get products by category
   // @GET("products")
   // suspend fun getProducts(): Response<List<Product>>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>
}