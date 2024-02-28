package com.example.optiplans.view.rw.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RwHomeUnitsItemBinding
import com.example.optiplans.entities.Unit

class RWHomeUnitsAdapter(private val units: List<Unit>): RecyclerView.Adapter<RWHomeUnitsAdapter.UnitViewHolder>()  {
    class UnitViewHolder (val binding:RwHomeUnitsItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RwHomeUnitsItemBinding.inflate(inflater, parent, false)
        return UnitViewHolder(binding)
    }

    override fun getItemCount(): Int = units.size

    override fun onBindViewHolder(holder: UnitViewHolder, position: Int) {
        val unit = units[position]
        val context = holder.itemView.context
        holder.binding.tvHomeUnitsItem.text = unit.name
    }

}