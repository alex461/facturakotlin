package com.example.entrevistatfhka.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.entrevistatfhka.data.repository.AppClassRepositorio

class ViewModelFactory (private  val repositorio: AppClassRepositorio) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repositorio) as T
        }
        throw IllegalArgumentException("View Model Desconocido")}
}