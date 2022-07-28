package com.example.ejerciciomovil.Interface

import com.example.ejerciciomovil.Clases.AuthResponse
import com.example.ejerciciomovil.Clases.Login
import retrofit2.http.*

interface APIService {

    @POST("http://testandroid.macropay.com.mx")
    @FormUrlEncoded
    suspend fun getTokenAuth(@Field("email") email:String, @Field("password") password: String): AuthResponse
}
