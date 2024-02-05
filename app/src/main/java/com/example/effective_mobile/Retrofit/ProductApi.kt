package com.example.doctor.Retrofit


import com.example.effective_mobile.Model.Product.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductApi {

    //Проверка токена
    @Headers("Content-Type: application/json")
    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun GetProduct(): Response<ProductModel>


}