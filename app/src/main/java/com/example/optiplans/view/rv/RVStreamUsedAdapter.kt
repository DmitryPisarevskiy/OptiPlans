package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvStreamUsedItemBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream
import com.example.optiplans.entities.getPeriodValue
import com.example.optiplans.view.ICommerceSwitchListener
import com.example.optiplans.view.IUnitClickListener

class RVStreamUsedAdapter (private val stream: Stream, var periodNum: Int, val unitListener: IUnitClickListener, val commerceListener:ICommerceSwitchListener): RecyclerView.Adapter<RVStreamUsedAdapter.StreamUsedViewHolder>() {
    class StreamUsedViewHolder (val binding: RvStreamUsedItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamUsedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvStreamUsedItemBinding.inflate(inflater, parent, false)
        return StreamUsedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var count = if (stream.sold) {1} else {0}
        count+=stream.spendingUnits.size
        return count
    }

    override fun onBindViewHolder(holder: StreamUsedViewHolder, position: Int) {
        with (holder.binding) {
            val index = if (stream.sold) {position-1} else {position}
            if ((stream.sold) && (position==0)) {
                tvStreamUsedItem.text = "Продажи"
                tvStreamUsedValue.text =
                    "%.1f".format(getPeriodValue(stream.sails[periodNum], ModelExample, periodNum))
                uvStreamUsed.visibility = View.GONE
                root.setOnClickListener {
                    commerceListener.onCommerceSwitchClick(false)
                }
            } else {
                tvStreamUsedItem.text =stream.spendingUnits.entries.elementAt(index).key.name
                tvStreamUsedValue.text =
                    "%.1f".format(getPeriodValue(stream.spendingUnits.entries.elementAt(index).value[periodNum],ModelExample, periodNum))
                root.setOnClickListener {
                    unitListener.onUnitClick(stream.spendingUnits.entries.elementAt(index).key)
                }
                uvStreamUsed.setColor(stream.spendingUnits.entries.elementAt(index).key.color)
            }
            svStreamUsed.painting(stream.color)
            if ((position==0) && (itemCount==1)) {
                root.removeView(holder.binding.flStreamUsedVertical)
            }
            if (position==0) {
                val set = ConstraintSet()
                set.clone(root)
                set.connect(flStreamUsedVertical.id, ConstraintSet.TOP, flStreamUsed.id,
                    ConstraintSet.TOP)
                set.applyTo(root)
            } else if (position==itemCount-1) {
                val set = ConstraintSet()
                set.clone(root)
                set.connect(flStreamUsedVertical.id, ConstraintSet.BOTTOM, flStreamUsed.id,
                    ConstraintSet.BOTTOM)
                set.applyTo(root)
            }
        }

    }
}