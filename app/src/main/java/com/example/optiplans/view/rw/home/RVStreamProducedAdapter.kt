package com.example.optiplans.view.rw.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.R
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
        var index = position
        if (stream.bought) {
            index--
            if (position==0) {
                holder.binding.tvStreamProducedItem.text = R.string.purchases.toString()
                holder.binding.tvStreamProducedValue.text = stream.purchases[periodNum-1].toString()
            }
        } else {
            var i = -1
            var j = 0
            do {
                if (stream.balance.entries.elementAt(i).value<0) {
                    i++
                }
                j++
            } while ((i<index) && (i<=itemCount-1) && (j<=stream.balance.entries.size-1))
            if (i==index) {
                holder.binding.tvStreamProducedItem.text = stream.balance.entries.elementAt(i).key.parentUnit.name
                holder.binding.tvStreamProducedValue.text = (stream.balance.entries.elementAt(i).key.activities[periodNum]*stream.balance.entries.elementAt(i).value).toString()
            }
        }

    }
}