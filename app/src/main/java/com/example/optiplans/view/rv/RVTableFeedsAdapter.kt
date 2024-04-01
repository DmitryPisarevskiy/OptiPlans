package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvTableItemFeedsBinding
import com.example.optiplans.entities.Unit

class RVTableFeedsAdapter(val unit:Unit): RecyclerView.Adapter<RVTableFeedsAdapter.TableFeedsItemHolder>() {
    inner class TableFeedsItemHolder(val binding: RvTableItemFeedsBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableFeedsItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvTableItemFeedsBinding.inflate(inflater, parent, false)
        return TableFeedsItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return unit.feeds.size
    }

    override fun onBindViewHolder(holder: TableFeedsItemHolder, position: Int) {
        with (holder.binding) {
            svUnitBalanceItem.painting(unit.feeds.entries.elementAt(position).key.color)
            tvUnitBalanceItem.text = unit.feeds.entries.elementAt(position).key.name
        }
    }
}