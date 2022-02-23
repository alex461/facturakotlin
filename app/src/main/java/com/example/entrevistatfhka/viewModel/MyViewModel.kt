package com.example.entrevistatfhka.viewModel


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.data.repository.AppClassRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(private val repositorio: AppClassRepositorio):ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult :LiveData<Boolean> get() = _loginResult

    private val _menssageToast = Channel<String> ()
    val menssageToast = _menssageToast.receiveAsFlow()

    private val _cantidadDeProductos = MutableStateFlow(1)
    val cantidaDeProductos : StateFlow<Int> = _cantidadDeProductos


    private val _listProducts = MutableLiveData<ModelProducto>()
    val listProducts : LiveData<MutableList<ModelProducto>> = repositorio.listProductos

    private val _total = MutableStateFlow(0)
    val total : StateFlow<Int> = _total

    val todoLosRecibo: LiveData<List<ModelRecibo>> = repositorio.listaDeRecibo.asLiveData()



    fun insertarRecibo (modelRecibo: ModelRecibo) = viewModelScope.launch {
        repositorio.insert(modelRecibo)
    }


    //val listProducts: LiveData<MutableList<ModelProducto>> get() = repositorio.listProductos




    fun agregarProducto(producto: ModelProducto) = viewModelScope.launch {
        repositorio.addProductToList(producto)
    }


    fun totalPrecio () = flow<Boolean>{




     listProducts.value?.forEach {
//         _total += it.precio * it.cantidad

     }

        //emit(total)

    }



    fun onSubmitClicked(data1:String,data2 :String,data3:String){


        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {validateData(data1,data2,data3) }

            _loginResult.value = result

        }

    }


    fun validateProductList():Boolean{
       return listProducts.value!!.size!=0
    }



    private fun validateData(data1:String,data2 :String,data3:String):Boolean{

        return data1.isNotEmpty()&&data2.isNotEmpty()&&data3.isNotEmpty()

    }




    fun aumentarCantidadProducto(){

        if (_cantidadDeProductos.value<100)
        _cantidadDeProductos.value += 1
    }

    fun disminuirCantidadProducto(){

        if (_cantidadDeProductos.value>1)
        _cantidadDeProductos.value -= 1


    }

    fun restarCantidadProducto(){
        _cantidadDeProductos.value = 1

    }




}





