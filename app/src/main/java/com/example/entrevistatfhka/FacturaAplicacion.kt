package com.example.entrevistatfhka

import android.app.Application
import com.example.entrevistatfhka.data.repository.AppClassRepositorio
import com.example.entrevistatfhka.data.repository.database.AppDataBase
import com.example.entrevistatfhka.view.adapter.ProductoAdpater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FacturaAplicacion : Application(){


    val applicationScope = CoroutineScope(SupervisorJob())

    val dataBase by lazy { AppDataBase.getDatabase(this,applicationScope) }

    val repositorio by lazy { AppClassRepositorio(dataBase.daoRecibo()) }

  //  private val productoAdpater by lazy {  ProductoAdpater() }

}