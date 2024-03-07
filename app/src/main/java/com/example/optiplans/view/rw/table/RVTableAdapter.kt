package com.example.optiplans.view.rw.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvTableItemBinding
import com.example.optiplans.entities.Model
import com.example.optiplans.entities.Stream

class RVTableAdapter(val model: Model) : RecyclerView.Adapter<RVTableAdapter.TableItemHolder>() {
    class TableItemHolder(val binding: RvTableItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvTableItemBinding.inflate(inflater, parent, false)
        return TableItemHolder(binding)
    }

    override fun getItemCount(): Int = model.units.size

    override fun onBindViewHolder(holder: TableItemHolder, position: Int) {
        val unit = model.units[position]
        holder.binding.tvTableItemUnitTag.text = unit.tag
        holder.binding.tvTableItemUnitName.text = unit.name
        holder.binding.uvTableUnitsUnit.painting(unit.color)
        val feeds: Map<Stream, Array<Float>> = model.units[position].feeds
        val vFeed1:Stream = feeds.entries.elementAt(0).key
    }
}