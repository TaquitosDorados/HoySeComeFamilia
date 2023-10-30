package com.example.hoysecome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hoysecome.databinding.ActivityIngredientetxtBinding

class Ingredientes_RVAdapter(val ingredientes:List<String>):RecyclerView.Adapter<Ingredientes_RVAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ActivityIngredientetxtBinding.bind(view)
        fun bind(ingrediente: String){
            binding.txtTexto.text = ingrediente
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_ingredientetxt,parent,false))
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredientes[position])
    }
}