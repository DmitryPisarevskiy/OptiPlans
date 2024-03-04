package com.example.optiplans.view.rw.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RwTableItemBinding
import com.example.optiplans.entities.Model

class RWTableAdapter(val model: Model): RecyclerView.Adapter<RWTableAdapter.TableItemHolder>() {
    class TableItemHolder(val binding: RwTableItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RwTableItemBinding.inflate(inflater, parent,false)
        return  TableItemHolder(binding)
    }

    override fun getItemCount(): Int = model.units.size

    override fun onBindViewHolder(holder: TableItemHolder, position: Int) {
        val unit = model.units[position]
        holder.binding.tvTableUnitsItem.text = unit.name
        holder.binding.uvTableUnitsUnit.painting(unit.color)
    }
}