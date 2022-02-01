package com.example.appserv.entities.empleados

data class EmpleadoListarResponse(
    val TipoMensaje: String,
    val RespuestaExito: String,
    val Resultado: List<EmpleadoListar>
)
