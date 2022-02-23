package com.example.entrevistatfhka.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.databinding.Fragment4Binding
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.view.adapter.ProductoAdpater
import com.example.entrevistatfhka.viewModel.MyViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class Fragment4 : BaseFragment<MyViewModel, Fragment4Binding>() {




    var cantidad = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var datosRecibo: ModelRecibo



        binding.recyclerViewProducto.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        }

        //---------------------- botones de cantidad de productos

        binding.imageButtonAddCantidad.setOnClickListener {

            viewModel.aumentarCantidadProducto()

        }


        binding.imageButtonRestarCantidad.setOnClickListener {

            viewModel.disminuirCantidadProducto()

        }


        lifecycleScope.launchWhenStarted {

            viewModel.cantidaDeProductos.collect { cantidadDeProductos ->

                binding.etCantidad.setText(cantidadDeProductos.toString())

            }

        }




        with(binding) {


            imageButtonAddProducto.setOnClickListener {

                viewModel.onSubmitClicked(
                    etCodigo.text.toString(),
                    etDescripcion.text.toString(),
                    etPrecio.text.toString()
                )

            }

        }


        //----------------------------------------------------------------------------


        settingPreferences.userPreferences.map { preferences ->

            datosRecibo = ModelRecibo(0, preferences.userName, preferences.razonSocialEmisor, 2.2, 100)

        }


        //--------------------------------------------------------------------------


        viewModel.loginResult.observe(viewLifecycleOwner) { success ->

            if (success) {

                with(binding) {

                    viewModel.agregarProducto(
                        ModelProducto(
                            etCodigo.text.toString().toInt(), etDescripcion.text.toString(),
                            etPrecio.text.toString().toDouble(), etCantidad.text.toString().toInt()
                        )
                    )

                    viewModel.restarCantidadProducto()

                    etCodigo.text!!.clear()
                    etDescripcion.text!!.clear()
                    etPrecio.text!!.clear()

                    binding.outlinedDescripcion.error = null
                    binding.outlinedPrecio.error = null
                    binding.outlinedCodigo.error = null

                }
            } else {

                with(binding) {
                    when {

                        etCodigo.text.isNullOrEmpty() -> {
                            outlinedCodigo.error = "Debe ingresar el código"
                        }

                        etDescripcion.text.isNullOrEmpty() -> {
                            outlinedDescripcion.error = "Debe ingresar la descripción"
                        }

                        etPrecio.text.isNullOrEmpty() -> {
                            outlinedPrecio.error = "Debe ingresar el precio"
                        }

                        etCantidad.text.isNullOrEmpty() ||
                                (etCantidad.text.toString().toInt() < 1)
                                || (binding.etCantidad.text.toString().toInt() > 100) -> {

                            Toast.makeText(
                                context,
                                "Sólo está permitido ingresar desde 1 hasta 100 productos por Recibo",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            }

        }


//---------------------------------------------------------------------

        viewModel.listProducts.observe(viewLifecycleOwner, { productos ->

            binding.recyclerViewProducto.adapter = ProductoAdpater(productos)

            cantidad = 0.0



            productos.forEach {

                cantidad += (it.precio * it.cantidad)

            }

            binding.tvSubtotal.text = cantidad.toString()


        })





        binding.buttonTotalizar.setOnClickListener {

        if (viewModel.validateProductList()){

                    viewModel.insertarRecibo(ModelRecibo(0,"alexander","ramirez",10.0,100))

                    findNavController().navigate(R.id.action_fragment4_to_fragment5)

        }else{

            Toast.makeText(
                context, "ingrese un producto", Toast.LENGTH_LONG).show() }
        }

    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = Fragment4Binding.inflate(inflater, container, false)

    override fun getViewModel() = MyViewModel::class.java


}