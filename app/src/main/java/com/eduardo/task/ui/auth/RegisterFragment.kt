package com.eduardo.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eduardo.task.util.showBottomSheet
import com.eduardo.task.R
import com.eduardo.task.databinding.FragmentRegisterBinding
import com.eduardo.task.util.initToolbar


class RegisterFragment : Fragment() {


    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }
    private fun initListener(){
        binding.btnRegister.setOnClickListener {
            validateData()
        }
    }
    private fun validateData(){
        val email = binding.digiteEmail.text.toString().trim()
        val senha = binding.digiteSenha.text.toString().trim()


        if (email.isNotBlank()){
            if (senha.isNotBlank()){
                Toast.makeText(requireContext(), "Tudo OK!", Toast.LENGTH_SHORT).show()
            } else {
                showBottomSheet(message = R.string.password_empty_register_fragment)
            }
        } else {
            showBottomSheet(message = R.string.email_empty_register_fragment)
        }
    }



}