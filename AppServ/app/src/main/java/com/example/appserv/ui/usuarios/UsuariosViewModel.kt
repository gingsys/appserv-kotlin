package com.example.appserv.ui.usuarios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsuariosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragments de Usuarios"
    }
    val text: LiveData<String> = _text
}