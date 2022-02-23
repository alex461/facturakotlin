package com.example.entrevistatfhka.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.entrevistatfhka.FacturaAplicacion
import com.example.entrevistatfhka.data.SettingPreference
import com.example.entrevistatfhka.viewModel.ViewModelFactory

abstract class BaseFragment <VM: ViewModel,B: ViewBinding> : Fragment(){

    protected lateinit var settingPreferences:SettingPreference
    protected lateinit var binding: B
    protected lateinit var viewModel: VM



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingPreferences = SettingPreference(requireContext())

        binding = getFragmentBinding(inflater,container)
        val  factory = ViewModelFactory((requireActivity().application as FacturaAplicacion). repositorio)
        viewModel = ViewModelProvider(this,factory).get(getViewModel())
        return binding.root
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?) : B



}