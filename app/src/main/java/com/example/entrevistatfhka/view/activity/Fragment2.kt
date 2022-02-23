package com.example.entrevistatfhka.view.activity

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.databinding.Fragment2Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.viewModel.MyViewModel
import kotlinx.coroutines.launch


class Fragment2 : BaseFragment<MyViewModel, Fragment2Binding>()  {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        binding.btnSiguiente.setOnClickListener {

            viewModel.onSubmitClicked(binding.etNombre.text.toString(),
                binding.etApellido.text.toString(),
                binding.etCedula.text.toString())



        }


        viewModel.loginResult.observe(viewLifecycleOwner){

            success ->
            if (success){

                lifecycleScope.launch {
                    settingPreferences.guardarDatosUsuario(
                        binding.etNombre.text.toString(),
                        binding.etApellido.text.toString(),
                        binding.etCedula.text.toString().toInt())
                }

                findNavController().navigate(R.id.action_fragment2_to_fragment3)

            }else{
                    with(binding){
                when {
                    etNombre.text.isNullOrEmpty() -> {tfNombre.error = "Debe ingresar un nombre" }

                    etApellido.text.isNullOrEmpty() -> { tfApellido.error = "Debe ingresar un apellido"}

                    etCedula.text.isNullOrEmpty() -> { tfCedula.error = "Debe ingresar la cédula"}

                    etCedula.text.isNullOrEmpty() -> { tfCedula.error = "La cédula no puede ser cero" }

                }
            }}

        }


    }







    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= Fragment2Binding.inflate(inflater,container,false)

    override fun getViewModel() = MyViewModel::class.java




}