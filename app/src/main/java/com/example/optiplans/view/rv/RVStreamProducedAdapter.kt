package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvStreamProducedItemBinding
import com.example.optiplans.entities.Stream
import com.example.optiplans.view.IUnitClickListener

class RVStreamProducedAdapter (private val stream: Stream, val periodNum: Int, val unitListener: IUnitClickListener): RecyclerView.Adapter<RVStreamProducedAdapter.StreamPoducedViewHolder>() {
    class StreamPoducedViewHolder (val binding: RvStreamProducedItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamPoducedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvStreamProducedItemBinding.inflate(inflater, parent, false)
        return StreamPoducedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var count = if (stream.bought) {1} else {0}
        stream.balance.entries.forEach {
            if (it.value<0) {count++}
        }
        return count
    }

    override fun onBindViewHolder(holder: StreamPoducedViewHolder, position: Int) {
        val index = if (stream.bought) {position-1} else {position}
        if ((position == 0) && (stream.bought)) {
            holder.binding.tvStreamProducedItem.text = "Покупки"
            holder.binding.tvStreamProducedValue.text = stream.purchases[periodNum].toString()
        } else {
            var i = -1
            var j = 0
            do {
                if (stream.balance.entries.elementAt(j).value<0) {
                    i++
                }
                j++
            } while ((i<index) && (i<=itemCount-1) && (j<=stream.balance.entries.size-1))
            if (i==index) {
                holder.binding.tvStreamProducedItem.text = stream.balance.entries.elementAt(j-1).key.parentUnit.name
                holder.binding.tvStreamProducedValue.text = (-stream.balance.entries.elementAt(j-1).key.activities[periodNum]*stream.balance.entries.elementAt(j-1).value).toString()
            }
            holder.binding.root.setOnClickListener {
                unitListener.onUnitClick(stream.balance.entries.elementAt(j-1).key.parentUnit)
            }
        }
        holder.binding.svStreamProduced.painting(stream.color)
        if ((position==0) && (itemCount==1)) {
            holder.binding.root.removeView(holder.binding.flStreamProducedVertical)
        } else {
            if (position==0) {
                val set = ConstraintSet()
                set.clone(holder.binding.root)
                set.connect(holder.binding.flStreamProducedVertical.id, ConstraintSet.TOP, holder.binding.flStreamProduced.id,
                    ConstraintSet.TOP)
                set.applyTo(holder.binding.root)
            } else if (position==itemCount-1) {
                val set = ConstraintSet()
                set.clone(holder.binding.root)
                set.connect(holder.binding.flStreamProducedVertical.id, ConstraintSet.BOTTOM, holder.binding.flStreamProduced.id,
                    ConstraintSet.BOTTOM)
                set.applyTo(holder.binding.root)
            }
        }
    }
}