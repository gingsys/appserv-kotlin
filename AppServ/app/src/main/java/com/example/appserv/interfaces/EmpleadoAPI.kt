package com.example.appserv.interfaces

import com.example.appserv.entities.empleados.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface EmpleadoAPI {

    @GET("empleado/listarEmpleado")
    fun ListarEmpleado(): Call<EmpleadoListarResponse>

    @POST("empleado/insertarEmpleado")
    fun InsertarEmpleado(@Body empleado: EmpleadoEntity): Call<EmpleadoObtenerResponse>

    @POST("empleado/obtenerEmpleado")
    fun ObtenerEmpleado(@Body empleado: EmpleadoObtenerRequest): Call<EmpleadoObtenerResponse>

    @PUT("empleado/actualizarEmpleado")
    fun ActualizarEmpleado(@Body empleado: EmpleadoEntity): Call<EmpleadoObtenerResponse>

    @POST("empleado/EliminarEmpleado")
    fun EliminarEmpleado(@Body empleado: EmpleadoDeleteRequest): Call<EmpleadoDeleteResponse>
}