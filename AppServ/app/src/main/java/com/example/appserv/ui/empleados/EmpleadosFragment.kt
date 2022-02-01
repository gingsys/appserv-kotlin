package com.example.appserv.ui.empleados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appserv.adapter.EmpleadoAdapter
import com.example.appserv.databinding.FragmentEmpleadosBinding
import com.example.appserv.entities.empleados.EmpleadoListar
import com.example.appserv.entities.empleados.EmpleadoListarResponse
import com.example.appserv.interfaces.EmpleadoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

import android.app.Activity
import android.content.Intent


class EmpleadosFragment : Fragment() {

  private val url = "http://apiv2.pyrmultimediasac.com/"

  private var _binding: FragmentEmpleadosBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {



    val empleadoAPI = obtenerRetrofit().create(EmpleadoAPI::class.java)
    val call = empleadoAPI.ListarEmpleado()
    call.enqueue(object : Callback<EmpleadoListarResponse> {
      override fun onResponse(
        call: Call<EmpleadoListarResponse>,
        response: Response<EmpleadoListarResponse?>
      ) {
        try {
          if (response.isSuccessful) {
            val empleadoResponse = response.body()
            val lstEmpleados = empleadoResponse?.Resultado
            if (lstEmpleados != null) {
              if (lstEmpleados.size > 0) {
                initRecyclerView(lstEmpleados,object : EmpleadoAdapter.onItemClickListener{
                  override fun onItemClick(position: Int) {
                    val intent = Intent(context,CrudActivity::class.java)
                    intent.putExtra("IdEmpleado",lstEmpleados[position].IdEmpleado)
                    startActivity(intent)
                  }

                })
                Toast.makeText(context, "Datos obtenidos correctamente", Toast.LENGTH_SHORT).show()

              } else {
                Toast.makeText(context, "Error al obtener los datos", Toast.LENGTH_SHORT).show()
              }
            }
          }
        } catch (ex: Exception) {
          Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
        }
      }

      override fun onFailure(call: Call<EmpleadoListarResponse>, t: Throwable) {
        Toast.makeText(context, "Error en la aplicación", Toast.LENGTH_SHORT).show()
      }

    })

    _binding = FragmentEmpleadosBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun initRecyclerView(lstEmpleado: List<EmpleadoListar>,clickListener: EmpleadoAdapter.onItemClickListener){
    binding.rvEmpleados.layoutManager = LinearLayoutManager(context)
    binding.rvEmpleados.adapter = EmpleadoAdapter(lstEmpleado,clickListener)
  }

  private fun obtenerRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
}