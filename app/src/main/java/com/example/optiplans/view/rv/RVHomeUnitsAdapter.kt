package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvHomeUnitsItemBinding
import com.example.optiplans.entities.Unit
import com.example.optiplans.view.IUnitClickListener

class RVHomeUnitsAdapter(private val units: List<Unit>, val unitListener: IUnitClickListener): RecyclerView.Adapter<RVHomeUnitsAdapter.UnitViewHolder>()  {
    class UnitViewHolder (val binding:RvHomeUnitsItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvHomeUnitsItemBinding.inflate(inflater, parent, false)
        return UnitViewHolder(binding)
    }

    override fun getItemCount(): Int = units.size

    override fun onBindViewHolder(holder: UnitViewHolder, position: Int) {
        val unit = units[position]
        val context = holder.itemView.context
        holder.binding.tvHomeUnitsItem.text = unit.name
        holder.binding.uvHomeUnitsUnit.setUnit(unit.color)
        holder.itemView.setOnClickListener {
            unitListener.onUnitClick(unit)
        }
    }
}