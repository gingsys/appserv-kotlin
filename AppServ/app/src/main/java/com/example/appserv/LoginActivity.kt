package com.example.appserv

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appserv.databinding.ActivityLoginBinding
import com.example.appserv.entities.login.LoginRequest
import com.example.appserv.entities.login.LoginResponse
import com.example.appserv.interfaces.LoginAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import android.content.SharedPreferences

class LoginActivity : AppCompatActivity() {

    private val url = "http://apiv2.pyrmultimediasac.com/"

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{obtenerUsuario()}

        val prefs = getSharedPreferences(
            "user", Context.MODE_PRIVATE
        )

        val usuario = prefs.getString("user.usuario","")
        val nombreEmpleado = prefs.getString("user.nombreEmpleado","")

        if (usuario != "" && nombreEmpleado != ""){
            val intent  = Intent(this@LoginActivity, MenuActivity::class.java)
            startActivity(intent)
        }

    }

    private fun obtenerRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun guardarUsuario(usuario:String, nombreEmpleado:String): SharedPreferences{
        val prefs = getSharedPreferences(
            "user", Context.MODE_PRIVATE
        )
        prefs.edit().putString("user.usuario",usuario).apply()
        prefs.edit().putString("user.nombreEmpleado",nombreEmpleado).apply()

        return prefs
    }

    private fun obtenerUsuario(){
        CoroutineScope(Dispatchers.IO).launch {
            val loginAPI = obtenerRetrofit().create(LoginAPI::class.java)
            val user = LoginRequest(LoginUsuario = binding.editTextUsuario.text.toString(),PassUsuario = binding.editTextPassword.text.toString())
            val call = loginAPI.LoginUsuario(user)
            call.enqueue(object : Callback<LoginResponse>{

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse?>){
                    try {
                        if (response.isSuccessful){
                            val loginResponse = response.body()
                            val lstUser = loginResponse?.Resultado
                            if (lstUser != null) {
                                if (lstUser.size > 0) {
                                    val prefs = guardarUsuario(lstUser[0].LoginUsuario, lstUser[0].NombreEmpleado)
                                    val intent  = Intent(this@LoginActivity, MenuActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    Toast.makeText(baseContext, "Bienvenido ${prefs.getString("user.nombreEmpleado","")}",Toast.LENGTH_LONG).show()
                                }else{
                                    Toast.makeText(baseContext, "Usuario o Contraseña incorrecta",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }catch (ex: Exception){
                        Toast.makeText(baseContext, ex.message,Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable){
                    Toast.makeText(this@LoginActivity, "Error: "+ t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}