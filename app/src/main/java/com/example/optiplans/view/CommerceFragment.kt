package com.example.optiplans.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.optiplans.R
import com.example.optiplans.databinding.FragmentCommerceBinding
import com.example.optiplans.entities.Model
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream

class CommerceFragment(val model: Model, var showPurchases:Boolean, val switchListener: ICommerceSwitchListener, val streamListener: IStreamClickListener): Fragment() {
    private var _binding: FragmentCommerceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommerceBinding.inflate(inflater, container, false)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            addTablePeriodRow(trCommercePeriodRow)
            model.streams.forEach {
                var stream: Stream? = null
                if ((showPurchases && it.bought) || (!showPurchases && it.sold)) {
                    stream = it
                }
                stream?.let {s->
                    if (showPurchases) {
                        tvCommerceSails.setTextColor(resources.getColor(R.color.light_blue))
                    } else {
                        tvCommercePurchases.setTextColor(resources.getColor(R.color.light_blue))
                    }
                    sCommerce.isChecked = !showPurchases
                    val trCommerceName = TableRow(context)
                    val tvCommerceName = TextView(context)
                    tvCommerceName.text = s.name
                    tvCommerceName.setTextAppearance(R.style.TableCaption)
                    trCommerceName.addView(tvCommerceName)
                    tlCommerce.addView(trCommerceName)
                    tvCommerceName.setOnClickListener {
                        streamListener.onStreamClick(s)
                    }
                    val trCommercePrice = TableRow(requireContext())
                    val tvCommercePrice = TextView(requireContext())
                    tvCommercePrice.text = getString(R.string.price)
                    tvCommercePrice.setTextAppearance(R.style.TableText)
                    trCommercePrice.addView(tvCommercePrice)
                    tlCommerce.addView(trCommercePrice)
                    val priceValues = if (showPurchases) {it.costs} else {it.prices}
                    addTableRow(trCommercePrice, priceValues, ModelExample)
                    val trCommerceMin = TableRow(requireContext())
                    val tvCommerceMin = TextView(requireContext())
                    tvCommerceMin.text = getString(R.string.min)
                    tvCommerceMin.setTextAppearance(R.style.TableText)
                    trCommerceMin.addView(tvCommerceMin)
                    tlCommerce.addView(trCommerceMin)
                    val minValues = if (showPurchases) {it.minBoundsPurchases} else {it.minBoundsSales}
                    addTableRow(trCommerceMin, minValues, ModelExample)
                    val trCommerceMax = TableRow(context)
                    val tvCommerceMax = TextView(context)
                    tvCommerceMax.text = getString(R.string.max)
                    tvCommerceMax.setTextAppearance(R.style.TableText)
                    trCommerceMax.addView(tvCommerceMax)
                    tlCommerce.addView(trCommerceMax)
                    val maxValues = if (showPurchases) {it.maxBoundsPurchases} else {it.maxBoundsSales}
                    addTableRow(trCommerceMax, maxValues, ModelExample)
                    val trCommerceSolution = TableRow(context)
                    val tvCommerceSolution = TextView(context)
                    tvCommerceSolution.text = getString(R.string.solution)
                    tvCommerceSolution.setTextAppearance(R.style.TableText)
                    trCommerceSolution.addView(tvCommerceSolution)
                    trCommerceSolution.setBackgroundColor(resources.getColor(R.color.light_gray))
                    tlCommerce.addView(trCommerceSolution)
                    val solValues = if (showPurchases) {it.purchases} else {it.sails}
                    addTableRow(trCommerceSolution, solValues, ModelExample)
                    val fl = FrameLayout(requireContext())
                    fl.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1)
                    fl.setBackgroundColor(resources.getColor(R.color.light_blue))
                    tlCommerce.addView(fl)
                }
            }
            sCommerce.setOnCheckedChangeListener {buttonView, isChecked ->
                switchListener.onCommerceSwitchClick(!isChecked)
            }
            tvCommerceSails.setOnClickListener {
                switchListener.onCommerceSwitchClick(false)
            }
            tvCommercePurchases.setOnClickListener {
                switchListener.onCommerceSwitchClick(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}