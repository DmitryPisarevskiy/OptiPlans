package com.example.optiplans.view.rw.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvTableItemBinding
import com.example.optiplans.entities.Model
import com.example.optiplans.entities.Stream
import com.example.optiplans.view.StreamView
import com.google.android.material.transition.Hold

class RVTableAdapter(val model: Model) : RecyclerView.Adapter<RVTableAdapter.TableItemHolder>() {
    class TableItemHolder(val binding: RvTableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun drawStreams(s: Stream) {

        }
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
        holder.binding.uvTableUnitsUnit.setUnit(unit.color)
        for (i in model.units[position].feeds.entries.indices) {
            val stream = model.units[position].feeds.entries.elementAt(i).key
            val streamView = StreamView(holder.itemView.context)
            streamView.id = View.generateViewId()
            streamView.layoutParams = LayoutParams(50,50)
            streamView.painting(stream.color)
            holder.binding.root.addView(streamView)
            val textView = TextView(holder.itemView.context)
            textView.id = View.generateViewId()
            textView.text =stream.name
            holder.binding.root.addView(textView)
            val set = ConstraintSet()
            set.clone(holder.binding.root)
            set.connect(streamView.id, ConstraintSet.END, holder.binding.uvTableUnitsUnit.id,ConstraintSet.START)
            set.connect(streamView.id, ConstraintSet.TOP, holder.binding.uvTableUnitsUnit.id,ConstraintSet.TOP, 50*(i+1))
            set.connect(textView.id, ConstraintSet.END, streamView.id,ConstraintSet.START)
            set.connect(textView.id, ConstraintSet.TOP, holder.binding.uvTableUnitsUnit.id,ConstraintSet.TOP, 50*(i+1))
            set.applyTo(holder.binding.root)
        }
        for (i in model.units[position].products.entries.indices) {
            val stream = model.units[position].products.entries.elementAt(i).key
            val streamView = StreamView(holder.itemView.context)
            streamView.id = View.generateViewId()
            streamView.layoutParams = LayoutParams(50,50)
            streamView.painting(model.units[position].products.entries.elementAt(i).key.color)
            holder.binding.root.addView(streamView)
            val textView = TextView(holder.itemView.context)
            textView.id = View.generateViewId()
            textView.text =stream.name
            holder.binding.root.addView(textView)
            val set = ConstraintSet()
            set.clone(holder.binding.root)
            set.connect(streamView.id, ConstraintSet.START, holder.binding.uvTableUnitsUnit.id,ConstraintSet.END)
            set.connect(streamView.id, ConstraintSet.TOP, holder.binding.uvTableUnitsUnit.id,ConstraintSet.TOP, 50*(i+1))
            set.connect(textView.id, ConstraintSet.START, streamView.id,ConstraintSet.END)
            set.connect(textView.id, ConstraintSet.TOP, holder.binding.uvTableUnitsUnit.id,ConstraintSet.TOP, 50*(i+1))
            set.applyTo(holder.binding.root)
        }
    }
}