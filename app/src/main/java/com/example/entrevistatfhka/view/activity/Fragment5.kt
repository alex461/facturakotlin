package com.example.entrevistatfhka.view.activity

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.databinding.Fragment5Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.utils.showHide
import com.example.entrevistatfhka.view.adapter.ProductoAdpater
import com.example.entrevistatfhka.viewModel.MyViewModel
import kotlinx.coroutines.*


class Fragment5 :  BaseFragment<MyViewModel, Fragment5Binding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        settingPreferences.userPreferences.asLiveData().observe(viewLifecycleOwner){

            preference ->
            runBlocking {

                launch {
                        binding.tvRazonSocial.text = preference.razonSocialEmisor
                        binding.tvRazonSocial.showHide() }

                launch {
                    binding.tvRif.text = String.format("${preference.tipoRif} ${preference.numeroDeRif}")
                    binding.tvRif.showHide() }

                launch {
                    binding.tvCliente.text = String.format("${preference.userName} ${preference.apellido}")
                    binding.tvCliente.showHide() }

                launch {
                    binding.tvCedula.text = preference.numeroDeRif.toString()
                    binding.tvCedula.showHide() }
            }


        }

        binding.lottieAnimationView.showHide()
        binding.tvTitle.showHide()
        binding.recyclerViewTotal.showHide()
        binding.buttonFinalizar.showHide()


        binding.recyclerViewTotal.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)

        }

        viewModel.listProducts.observe(viewLifecycleOwner,{ productos ->

            binding.recyclerViewTotal.adapter = ProductoAdpater(productos)


        })





        binding.buttonFinalizar.setOnClickListener {

            findNavController().navigate(R.id.action_fragment5_to_fragment1)


        }


    }




    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= Fragment5Binding.inflate(inflater,container,false)

    override fun getViewModel() = MyViewModel::class.java


}