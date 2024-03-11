package com.example.optiplans.view.rw.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.R
import com.example.optiplans.databinding.RvTableItemBinding
import com.example.optiplans.entities.Model
import com.example.optiplans.entities.Stream
import com.example.optiplans.view.StreamView

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
        //val feeds: Map<Stream, Array<Float>> = model.units[position].feeds
        //val vFeed1:Stream = feeds.entries.elementAt(0).key
        val streamView1 = StreamView(holder.itemView.context)
        holder.binding.root.addView(streamView1)
        val set = ConstraintSet()
        set.clone(holder.binding.root)
        streamView1.id = R.id.unit
        set.connect(streamView1.id, ConstraintSet.LEFT, holder.binding.flTableItem.id,ConstraintSet.LEFT)
        set.connect(streamView1.id, ConstraintSet.BOTTOM, holder.binding.flTableItem.id,ConstraintSet.BOTTOM)
        set.applyTo(holder.binding.root)
    }
}