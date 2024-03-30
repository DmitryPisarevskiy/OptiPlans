package com.example.optiplans.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optiplans.databinding.FragmentUnitBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Unit
import com.example.optiplans.entities.collapseItem
import com.example.optiplans.view.rv.RVUnitBalanceAdapter

class UnitFragment(val streamListener: IStreamClickListener) : Fragment() {
    private lateinit var binding: FragmentUnitBinding
    var currentUnit: Unit? = null
    private var showBalance = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            currentUnit = ModelExample.currentUnit
            tvUnitTag.text = currentUnit?.tag?:""
            tvUnitName.text = currentUnit?.name?:""
            currentUnit?.let {
                uvUnit.setColor(it.color)
                uvUnitCaption.setColor(it.color)
                rvUnitFeeds.layoutManager = LinearLayoutManager (activity)
                rvUnitFeeds.adapter = RVUnitBalanceAdapter(it, true, ModelExample.currentPeriodNum, streamListener)
                rvUnitProducts.layoutManager = LinearLayoutManager (activity)
                rvUnitProducts.adapter = RVUnitBalanceAdapter(it, false, ModelExample.currentPeriodNum, streamListener)
            }
            val spinnerData = Array<String>(ModelExample.periods.size+1) {""}
            var sum = 0
            for (i in ModelExample.periods.indices) {
                spinnerData[i] = (i+1).toString() + " (" + ModelExample.periods[i] + " дней)"
                sum+=ModelExample.periods[i]
            }
            spinnerData[ModelExample.periods.size] = "Всего " + "(" + sum.toString() + " дней)"
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, spinnerData)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sUnitPeriod.adapter = arrayAdapter
            sUnitPeriod.prompt = "Выберите период"
            sUnitPeriod.setSelection(ModelExample.currentPeriodNum)
            sUnitPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    ModelExample.currentPeriodNum = p2
                    currentUnit?.apply {
                        rvUnitProducts.adapter = RVUnitBalanceAdapter(this, isFeeds = false, ModelExample.currentPeriodNum, streamListener)
                        rvUnitFeeds.adapter = RVUnitBalanceAdapter(this, isFeeds = true, ModelExample.currentPeriodNum, streamListener)
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            ivUnitBalance.setOnClickListener {
                collapseItem(ivUnitBalance, llUnitBalance, !showBalance)
                showBalance=!showBalance
            }
            tvUnitBalance.setOnClickListener {
                collapseItem(ivUnitBalance, llUnitBalance, !showBalance)
                showBalance=!showBalance
            }
        }
    }
}