package com.example.appserv.entities.login

data class LoginResponse(
    val TipoMensaje: String,
    val RespuestaExito: String,
    val Resultado: ArrayList<LoginUser>
)
