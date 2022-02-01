package com.example.appserv.entities.empleados

data class EmpleadoObtenerResponse(
    val TipoMensaje: String,
    val RespuestaExito:String,
    val Resultado: List<EmpleadoEntity>
)
