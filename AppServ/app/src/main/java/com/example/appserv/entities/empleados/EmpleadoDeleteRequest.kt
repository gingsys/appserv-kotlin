package com.example.appserv.entities.empleados

import java.util.*

data class EmpleadoDeleteRequest(
    val UsuarioModificacion: String,
    val FechaModificacion: Date,
    val IdEmpleado: Int
)
