package com.example.entrevistatfhka.data.repository

import android.view.View
import androidx.annotation.WorkerThread
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.data.repository.Dao.DaoRecibo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class AppClassRepositorio (private val daoRecibo: DaoRecibo){

    val listaDeRecibo: Flow<List<ModelRecibo>> = daoRecibo.getListRecibo()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(modelRecibo: ModelRecibo) {
       daoRecibo.insert(modelRecibo)

    }



    val listProductos = MutableLiveData<MutableList<ModelProducto>>().apply {
        value = mutableListOf()
    }


    fun addProductToList(producto: ModelProducto) {
        listProductos.value!!.add(producto)
        listProductos.value = listProductos.value
    }




}