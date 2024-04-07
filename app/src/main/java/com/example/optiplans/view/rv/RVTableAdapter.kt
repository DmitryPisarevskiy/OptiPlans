package com.example.optiplans.view.rv

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.R
import com.example.optiplans.databinding.RvTableItemBinding
import com.example.optiplans.entities.Model
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.view.IUnitClickListener
import com.example.optiplans.view.addItemTablePeriodRow
import com.example.optiplans.view.addItemTableRow

class RVTableAdapter(val model: Model, val unitListener: IUnitClickListener) : RecyclerView.Adapter<RVTableAdapter.TableItemHolder>() {
    class TableItemHolder(val binding: RvTableItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvTableItemBinding.inflate(inflater, parent, false)
        return TableItemHolder(binding)
    }

    override fun getItemCount(): Int = model.units.size

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TableItemHolder, position: Int) {
        with (holder.binding) {
            val unit = model.units[position]
            tvTableItemUnitTag.text = unit.tag
            tvTableItemUnitName.text = unit.name
            uvTableUnitsUnit.setColor(unit.color)
            rvTableFeeds.layoutManager = LinearLayoutManager(root.context)
            rvTableProducts.layoutManager = LinearLayoutManager(root.context)
            rvTableFeeds.adapter = RVTableFeedsAdapter(model.units[position])
            rvTableProducts.adapter = RVTableProductsAdapter(model.units[position])
            root.setOnClickListener {
                unitListener.onUnitClick(model.units[position])
            }
            addItemTablePeriodRow(trTableItemCapsPeriod)
            unit.capacities.forEach {
                val tv = TextView(root.context)
                val tr = TableRow(root.context)
                tv.text = it.name
                tv.setTextAppearance(R.style.ItemText)
                tr.addView(tv)
                addItemTableRow(tr,it.activities, ModelExample)
                tlTableItemCaps.addView(tr)
            }
        }
    }
}