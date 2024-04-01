package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvUnitFeedItemBinding
import com.example.optiplans.databinding.RvUnitProductItemBinding
import com.example.optiplans.entities.Unit
import com.example.optiplans.view.IStreamClickListener

class RVUnitBalanceAdapter(val unit: Unit, val isFeeds: Boolean, val periodNum: Int, val streamListener: IStreamClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class UnitFeedItemHolder(val binding: RvUnitFeedItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with (binding) {
                val data = unit.feeds
                svUnitBalanceItem.painting(data.entries.elementAt(position).key.color)
                tvUnitBalanceItem.text = data.entries.elementAt(position).key.name
                tvUnitBalanceValue.text = data.entries.elementAt(position).value[periodNum].toString()
                root.setOnClickListener {
                    streamListener.onStreamClick(unit.feeds.entries.elementAt(position).key)
                }
            }
        }
    }

    inner class UnitProductItemHolder(val binding: RvUnitProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with (binding) {
                val data = unit.products
                svUnitBalanceItem.painting(data.entries.elementAt(position).key.color)
                tvUnitBalanceItem.text = data.entries.elementAt(position).key.name
                tvUnitBalanceValue.text = (-1*data.entries.elementAt(position).value[periodNum]).toString()
                root.setOnClickListener {
                    streamListener.onStreamClick(unit.products.entries.elementAt(position).key)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (isFeeds) {
            val binding = RvUnitFeedItemBinding.inflate(inflater, parent, false)
            UnitFeedItemHolder(binding)
        } else {
            val binding = RvUnitProductItemBinding.inflate(inflater, parent, false)
            UnitProductItemHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return if (isFeeds) {unit.feeds.size} else {unit.products.size}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isFeeds) {
            (holder as UnitFeedItemHolder).bind(position)
        } else {
            (holder as UnitProductItemHolder).bind(position)
        }
    }
}