package com.example.entrevistatfhka.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.databinding.CardProductoBinding
import java.text.DecimalFormat

class FacturaAdapter (private val listFactura : List<ModelRecibo>)
    :RecyclerView.Adapter<FacturaAdapter.MyViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_producto,
            parent,false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val  factura = listFactura[position]

        holder.enlazarItem(factura)

   }

    override fun getItemCount()= listFactura.size



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding = CardProductoBinding.bind(itemView)

        fun enlazarItem(factura: ModelRecibo) {

            val dec = DecimalFormat("#,###.##")
            binding.tvCodigo.text = String.format("Cliente: ${factura.cliente}")
            binding.tvCantidadPrecio.text = String.format("Emisor: ${factura.emisor}")
            binding.tvDescripcion.text = String.format("Cantidad de items: ${factura.cantidadItems}")
            binding.tvTotalItem.text = String.format("Monto total ${dec.format(factura.total)}")
            binding.tvCodigo.apply {
                textSize =18f
            }
            binding.tvTotalItem.apply {
                textSize = 16f
            }
                }
        }


    }
