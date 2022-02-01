package com.example.appserv.interfaces

import com.example.appserv.entities.login.LoginRequest
import retrofit2.Call
import com.example.appserv.entities.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("usuario/obtenerUsuario")
    fun LoginUsuario(@Body user:LoginRequest): Call<LoginResponse>

}