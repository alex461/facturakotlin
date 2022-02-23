package com.example.entrevistatfhka.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.databinding.CardProductoBinding
import java.text.DecimalFormat

class ProductoAdpater (private val listaDeProductos : List<ModelProducto>):
    RecyclerView.Adapter<ProductoAdpater.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_producto, parent,
            false))

    override fun getItemCount()= listaDeProductos.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

     val  producto = listaDeProductos[position]

        holder.enlazarItem(producto)

    }


    class MyViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        var binding = CardProductoBinding.bind(itemView)
        var contexto= itemView.context


        fun enlazarItem(producto: ModelProducto) {

            val dec = DecimalFormat("#,###.##")

            binding.tvCodigo.text = String.format("Codigo : ${producto.codigo}")
            binding.tvCantidadPrecio.text = String.format("${producto.cantidad} x ${producto.precio}")
            binding.tvDescripcion.text = producto.descripcion
            binding.tvTotalItem.text = dec.format(producto.precio*producto.cantidad)


        }



    }

}