package com.example.appserv.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appserv.databinding.ListaEmpleadosBinding
import com.example.appserv.entities.empleados.EmpleadoListar

class EmpleadoViewHolder(view: View, listener: EmpleadoAdapter.onItemClickListener): RecyclerView.ViewHolder(view) {

    val binding = ListaEmpleadosBinding.bind(view)

    fun render(empleado: EmpleadoListar){
        binding.tvNombre.text = empleado.Nombre
        binding.tvApellido.text = empleado.Apellidos
        binding.tvInicial.text = empleado.Nombre[0].toString() + empleado.Apellidos[0].toString()
        binding.tvCorreo.text = empleado.CorreoEmpleado
        binding.tvTelefono.text = empleado.TelefonoEmpleado
    }

    init {
        view.setOnClickListener{
            listener.onItemClick(bindingAdapterPosition)
        }
    }

}