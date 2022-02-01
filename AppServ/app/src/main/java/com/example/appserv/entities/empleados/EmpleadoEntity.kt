package com.example.appserv.entities.empleados

import java.util.*

data class EmpleadoEntity(
    val IdEmpleado: Int,
    val Nombre: String,
    val ApellidoPaterno: String,
    val ApellidoMaterno: String,
    val TipoDocumento: Int,
    val NumeroDocumento: String,
    val IdDistrito: Int,
    val DireccionEmpleado: String,
    val TelefonoEmpleado: String,
    val CorreoEmpleado: String,
    val EstadoRegistro: Int,
    val UsuarioCreacion: String?,
    val FechaCreacion: Date?,
    val UsuarioModificacion: String?,
    val FechaModificacion: Date?

    )
