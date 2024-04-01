package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvTableItemProductsBinding
import com.example.optiplans.entities.Unit

class RVTableProductsAdapter(val unit: Unit): RecyclerView.Adapter<RVTableProductsAdapter.TableProductsItemHolder>() {
    inner class TableProductsItemHolder(val binding: RvTableItemProductsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableProductsItemHolder {
        val inlater = LayoutInflater.from(parent.context)
        val binding = RvTableItemProductsBinding.inflate(inlater,parent,false)
        return TableProductsItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return unit.products.size
    }

    override fun onBindViewHolder(holder: TableProductsItemHolder, position: Int) {
        with (holder.binding) {
            svUnitBalanceItem.painting(unit.products.entries.elementAt(position).key.color)
            tvUnitBalanceItem.text = unit.products.entries.elementAt(position).key.name
        }
    }
}