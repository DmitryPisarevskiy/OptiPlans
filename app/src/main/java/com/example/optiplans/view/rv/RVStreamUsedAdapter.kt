package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvStreamUsedItemBinding
import com.example.optiplans.entities.Stream
import com.example.optiplans.view.IUnitClickListener

class RVStreamUsedAdapter (private val stream: Stream, val periodNum: Int, val unitListener: IUnitClickListener): RecyclerView.Adapter<RVStreamUsedAdapter.StreamUsedViewHolder>() {
    class StreamUsedViewHolder (val binding: RvStreamUsedItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamUsedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvStreamUsedItemBinding.inflate(inflater, parent, false)
        return StreamUsedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var count = if (stream.sold) {1} else {0}
        stream.balance.entries.forEach {
            if (it.value>0) {count++}
        }
        return count
    }

    override fun onBindViewHolder(holder: StreamUsedViewHolder, position: Int) {
        val index = if (stream.sold) {position-1} else {position}
        if ((stream.sold) && (position==0)) {
            holder.binding.tvStreamUsedItem.text = "Продажи"
            holder.binding.tvStreamUsedValue.text = stream.sails[periodNum - 1].toString()
        } else {
            var i = -1
            var j = 0
            do {
                if (stream.balance.entries.elementAt(j).value > 0) {
                    i++
                }
                j++
            } while ((i < index) && (i <= itemCount - 1) && (j <= stream.balance.entries.size - 1))
            if (i == index) {
                holder.binding.tvStreamUsedItem.text =stream.balance.entries.elementAt(j-1).key.parentUnit.name
                holder.binding.tvStreamUsedValue.text =(stream.balance.entries.elementAt(j-1).key.activities[periodNum-1] * stream.balance.entries.elementAt(j-1).value).toString()
            }
        }
        holder.binding.svStreamUsed.painting(stream.color)
        if ((position==0) && (itemCount==1)) {
            holder.binding.root.removeView(holder.binding.flStreamUsedVertical)
        }
        if (position==0) {
            val set = ConstraintSet()
            set.clone(holder.binding.root)
            set.connect(holder.binding.flStreamUsedVertical.id, ConstraintSet.TOP, holder.binding.flStreamUsed.id,
                ConstraintSet.TOP)
            set.applyTo(holder.binding.root)
        } else if (position==itemCount-1) {
            val set = ConstraintSet()
            set.clone(holder.binding.root)
            set.connect(holder.binding.flStreamUsedVertical.id, ConstraintSet.BOTTOM, holder.binding.flStreamUsed.id,
                ConstraintSet.BOTTOM)
            set.applyTo(holder.binding.root)
        }
    }
}