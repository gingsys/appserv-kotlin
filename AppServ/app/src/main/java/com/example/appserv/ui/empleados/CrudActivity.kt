package com.example.appserv.ui.empleados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appserv.databinding.DetalleFragmentBinding

class CrudActivity : AppCompatActivity() {

    private lateinit var binding: DetalleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}