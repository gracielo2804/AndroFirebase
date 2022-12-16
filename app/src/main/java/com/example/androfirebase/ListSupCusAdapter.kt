package com.example.androfirebase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androfirebase.databinding.LayoutListBinding

class ListSupCusAdapter: RecyclerView.Adapter<ListSupCusAdapter.ViewHolder>() {

    private var listData = ArrayList<SupCus>()
    fun refreshData(listDataKirim:ArrayList<SupCus>){

        listData.clear()
        listData.addAll(listDataKirim)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {
        private val binding = LayoutListBinding.bind(view)
        fun bind(data:SupCus){
            binding.txtNama.text=data.nama
            binding.txtCatatan.text=data.catatan
            binding.txtEmail.text=data.email
            binding.txtJenis.text=data.jenisBarang
            binding.txtNoHp.text=data.nohp


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position % 2 == 1) {
            holder.view.setBackgroundColor(Color.WHITE)
        } else {
            holder.view.setBackgroundColor(Color.parseColor("#EBF5FB"))
        }
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}