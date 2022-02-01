package com.example.appserv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appserv.R
import com.example.appserv.entities.empleados.EmpleadoListar


class EmpleadoAdapter( val empleado: List<EmpleadoListar>, private var clickListener: onItemClickListener): RecyclerView.Adapter<EmpleadoViewHolder>(){

    interface  onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmpleadoViewHolder(layoutInflater.inflate(R.layout.lista_empleados, parent,false),clickListener)
    }

    override fun getItemCount(): Int = empleado.size


    override fun onBindViewHolder(holder: EmpleadoViewHolder, position: Int) {
        holder.render(empleado[position])
    }

}