package com.example.appserv.entities.empleados

data class EmpleadoListar(
    val IdEmpleado: Int,
    val NumeroRegistro: String,
    val Nombre: String,
    val Apellidos: String,
    val TelefonoEmpleado: String,
    val CorreoEmpleado: String,
    val FechaIngreso: String
)
