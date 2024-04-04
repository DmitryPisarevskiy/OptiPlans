package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvStreamProducedItemBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.QuantityMeasure
import com.example.optiplans.entities.Stream
import com.example.optiplans.entities.getPeriodValue
import com.example.optiplans.view.ICommerceSwitchListener
import com.example.optiplans.view.IUnitClickListener

class RVStreamProducedAdapter (
    private val stream: Stream,
    val periodNum: Int,
    val measure: QuantityMeasure,
    val unitListener: IUnitClickListener,
    val commerceListener:ICommerceSwitchListener
): RecyclerView.Adapter<RVStreamProducedAdapter.StreamPoducedViewHolder>() {
    class StreamPoducedViewHolder (val binding: RvStreamProducedItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamPoducedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvStreamProducedItemBinding.inflate(inflater, parent, false)
        return StreamPoducedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var count = if (stream.bought) {1} else {0}
        count+=stream.producingUnits.size
        return count
    }

    override fun onBindViewHolder(holder: StreamPoducedViewHolder, position: Int) {
        with (holder.binding) {
            val index = if (stream.bought) {position-1} else {position}
            if ((position == 0) && (stream.bought)) {
                tvStreamProducedItem.text = "Покупки"
                tvStreamProducedValue.text =
                    "%.1f".format(getPeriodValue(stream.purchases[periodNum], ModelExample, periodNum))
                uvStreamProduced.visibility = View.GONE
                root.setOnClickListener {
                    commerceListener.onCommerceSwitchClick(true)
                }
            } else {
                tvStreamProducedItem.text = stream.producingUnits.entries.elementAt(index).key.name
                tvStreamProducedValue.text =
                    "%.1f".format(getPeriodValue(stream.producingUnits.entries.elementAt(index).value[periodNum],ModelExample, periodNum))
                root.setOnClickListener {
                    unitListener.onUnitClick(stream.producingUnits.entries.elementAt(index).key)
                }
                uvStreamProduced.setColor(stream.producingUnits.entries.elementAt(index).key.color)
            }
            svStreamProduced.painting(stream.color)
            if ((position==0) && (itemCount==1)) {
                root.removeView(holder.binding.flStreamProducedVertical)
            } else {
                if (position==0) {
                    val set = ConstraintSet()
                    set.clone(root)
                    set.connect(flStreamProducedVertical.id, ConstraintSet.TOP, flStreamProduced.id,
                        ConstraintSet.TOP)
                    set.applyTo(root)
                } else if (position==itemCount-1) {
                    val set = ConstraintSet()
                    set.clone(root)
                    set.connect(flStreamProducedVertical.id, ConstraintSet.BOTTOM, flStreamProduced.id,
                        ConstraintSet.BOTTOM)
                    set.applyTo(root)
                }
            }
        }
    }
}