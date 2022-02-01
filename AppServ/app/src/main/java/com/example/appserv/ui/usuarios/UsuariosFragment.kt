package com.example.appserv.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appserv.databinding.FragmentUsuariosBinding

class UsuariosFragment : Fragment() {

  private lateinit var usuariosViewModel: UsuariosViewModel
  private var _binding: FragmentUsuariosBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    usuariosViewModel =
      ViewModelProvider(this)[UsuariosViewModel::class.java]

    _binding = FragmentUsuariosBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textUsuarios
    usuariosViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}