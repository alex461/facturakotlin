package com.example.entrevistatfhka.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.databinding.Fragment3Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.viewModel.MyViewModel
import kotlinx.coroutines.launch


class Fragment3 : BaseFragment<MyViewModel, Fragment3Binding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        ArrayAdapter.createFromResource(requireContext(), R.array.identificador, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }


        binding.buttonSiguiente.setOnClickListener {

            viewModel.onSubmitClicked(binding.etRazonSocial.text.toString(),
                binding.spinner.selectedItem.toString(),
                  binding.etRif.text.toString())


        }



        viewModel.loginResult.observe(viewLifecycleOwner){

            success ->

            if (success){

                lifecycleScope.launch {
                    settingPreferences.guardaDatosIdentificador(
                        binding.etRazonSocial.text.toString(),
                        binding.spinner.selectedItem.toString(),
                        binding.etRif.text.toString().toInt())
                }

                findNavController().navigate(R.id.action_fragment3_to_fragment4)

            }else{
                with(binding) {
                    when {

                    etRazonSocial.text.isNullOrEmpty() -> { outlinedEmisor.error = "Debe ingresar el emisor" }

                    etRif.text.isNullOrEmpty() -> { outlinedRif.error = "Debe ingresar el RIF" }

                    etRif.text.isNullOrEmpty() -> { outlinedRif.error = "El RIF no puede ser cero" }

                    }

                }

            }




        }



    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= Fragment3Binding.inflate(inflater,container,false)


    override fun getViewModel() = MyViewModel::class.java

}