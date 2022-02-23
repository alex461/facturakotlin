package com.example.entrevistatfhka.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.databinding.Fragment1Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.viewModel.MyViewModel

class Fragment1 : BaseFragment<MyViewModel, Fragment1Binding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNuevoRecibo.setOnClickListener{
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }

        binding.buttonListaRecibo.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment6)
        }

        

    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= Fragment1Binding.inflate(inflater,container,false)

    override fun getViewModel() = MyViewModel::class.java



}